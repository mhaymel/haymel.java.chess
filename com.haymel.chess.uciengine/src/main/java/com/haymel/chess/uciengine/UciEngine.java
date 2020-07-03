/***************************************************
 * (c) Markus Heumel
 *
 * @date:	07.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.chess.engine.fen.GameFromFEN.gameFromInitialFen;
import static com.haymel.util.Require.nonNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMoveFromString;
import com.haymel.chess.engine.moves.StringFromMove;
import com.haymel.chess.engine.search.AnalyzedMove;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.Nodes;
import com.haymel.chess.engine.search.SearchInfo;
import com.haymel.chess.engine.search.SearchInfoImpl;
import com.haymel.chess.engine.search.execution.IterativeSearch;
import com.haymel.chess.engine.search.execution.SearchExecutor;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.result.Infos;

public class UciEngine extends com.haymel.chess.uci.Engine {

	private Game game;
	private SearchExecutor executor;
	
	public UciEngine(InputStream in, PrintStream out) {
		super(nonNull(in, "in"), nonNull(out, "out"));
	}
	
	@Override
	public void uci() {
		idauthor("Markus Heumel");
		idname("haymel");
		uciok();
	}
	
	@Override
	public void isReady() {
		readyok();
	}
	
	@Override
	public void positionStart(Moves moves) { 
		game = gameFromInitialFen();
		makeMoves(moves);
		game = new Game(game.position());
	}

	@Override
	public void positionFen(String fen, Moves moves) {
		game = new GameFromFEN(fen).value();
		makeMoves(moves);
	}

	private void makeMoves(Moves moves) {
		MakeMoveFromString moveMaker = new MakeMoveFromString(game);

		for (String move: moves.value())
			moveMaker.move(move);
	}
	
	@Override
	public void go(int wtimeInMilliSeconds, int btimeInMilliSeconds, int wincInMilliSeconds, int bincInMilliSeconds) {
		stop();

		SearchInfo info = new SearchInfoImpl(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		IterativeSearch search = new IterativeSearch(game, info);
		executor = new SearchExecutor(search, searchFinished());
		executor.go(wtimeInMilliSeconds, btimeInMilliSeconds);
	}
	
	private Consumer<Nodes> nodeStatisticsConsumer() {
		return (ns) -> info(new Infos().nps(ns.nps()).nodes(ns.count()));
	}

	private Consumer<BestMove> bestMoveConsumer() {
		int activeColor = game.activeColor();
		
		return (bm) -> { 
			info(new InfosFromBestMove(activeColor, bm).value()); 
		};
	}
	
	private Consumer<BestMove> searchFinished() {
		return (bestMove) -> {
			if (bestMove.variant() != null)
				bestmove(asString(bestMove.move()));
			else if (bestMove.value() == 0)
				info(new Infos().string("stalemate"));
			else 
				info(new Infos().string("mate"));
		};
	}
	
	private Consumer<AnalyzedMove> currentMoveConsumer() {
		return (cm) -> currentMove(cm); 
	}
	
	private void currentMove(AnalyzedMove currentMove) {
		info(
			new Infos()
				.currmovenumber(currentMove.moveNumber())
				.currmove(asString(currentMove.move())));
	}
	
	private static String asString(int move) {
		return new StringFromMove(move).value();
	}

	private IntConsumer depthConsumer() {
		return (depth) -> depth(depth);
	}
	
	private void depth(int depth) {
		info(new Infos().depth(depth));
	}

	@Override
	public synchronized void stop() {
		if (executor == null)
			return;
		
		executor.stop();
		executor = null;
	}
	
	public static void main(String[] args) throws InterruptedException {
		UciEngine engine = new UciEngine(System.in, System.out);
		engine.start();
		Thread.sleep(Long.MAX_VALUE);
	}
	
}
