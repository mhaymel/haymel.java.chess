/***************************************************
 * (c) Markus Heumel
 *
 * @date:	07.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.util.Require.nonNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.CurrentMove;
import com.haymel.chess.engine.search.IterativeSearch;
import com.haymel.chess.engine.search.NodeStatistics;
import com.haymel.chess.engine.search.SearchExecutor;
import com.haymel.chess.engine.search.Variant;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.result.Infos;

public class UciEngine extends com.haymel.chess.uci.Engine {

	private Game game;
	private SearchExecutor executor;
	private final AtomicLong nodeCount = new AtomicLong(0);
	private final AtomicLong nodesPerSecond = new AtomicLong(0);
	
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
		game = createStartpos();
		makeMoves(moves);
	}

	@Override
	public void positionFen(String fen, Moves moves) {
		game = new Game();
		new GameFromFEN(game, fen).execute();
		makeMoves(moves);
	}

	private void makeMoves(Moves moves) {
		UciMoveMaker uciMoveMaker = new UciMoveMaker(game);
		for (String move: moves.value())
			uciMoveMaker.move(move);
	}
	
	private static Game createStartpos() {
		Game game = new Game();
		new StartposCreator(game).execute();
		return game;
	}
	
	@Override
	public void go(int wtimeInMilliSeconds, int btimeInMilliSeconds, int wincInMilliSeconds, int bincInMilliSeconds) {
		stop();

		resetStatistic();
		
		IterativeSearch search = new IterativeSearch(game, currentMoveConsumer(), depthConsumer(), bestMoveConsumer2());
		executor = new SearchExecutor(search, bestMoveConsumer(), nodeStatisticConsumer());
		executor.go(wtimeInMilliSeconds, btimeInMilliSeconds);
	}
	
	private void resetStatistic() {
		nodeCount.set(0);
		nodesPerSecond.set(0);
	}

	private Consumer<BestMove> bestMoveConsumer2() {
		return (bm) -> {
			info(
				new Infos()
					.scorecp(bm.value())
					.depth(bm.depth())
					.seldepth(bm.selDepth())
					.nodes(nodeCount.get())
					.nps(nodesPerSecond.get())
					.pv(movesFrom(bm.variant())));
		};
	}

	private Moves movesFrom(Variant variant) {
		return new MovesFromVariant(variant).value();
	}
	
	private Consumer<Move> bestMoveConsumer() {
		return (move) -> {
			if (move == null)
				bestmove("0000");
			else
				bestmove(asString(move));
		};
	}
	
	private Consumer<NodeStatistics> nodeStatisticConsumer() {
		return (s) -> nodeStatistics(s);
	}
	
	private void nodeStatistics(NodeStatistics s) {
		nodeCount.set(s.nodeCount());
		nodesPerSecond.set(s.nodesPerSecond());
		info(new Infos().nps(s.nodesPerSecond()).nodes(s.nodeCount()));
	}

	private Consumer<CurrentMove> currentMoveConsumer() {
		return (cm) -> currentMove(cm); 
	}
	
	private void currentMove(CurrentMove currentMove) {
		info(
			new Infos()
				.currmovenumber(currentMove.current())
				.currmove(asString(currentMove.move())));
	}
	
	private static String asString(Move move) {
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
