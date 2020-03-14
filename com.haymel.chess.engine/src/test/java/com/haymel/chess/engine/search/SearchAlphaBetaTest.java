/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static java.lang.String.format;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
		new StartposCreator(game).execute();
	}
	
	@Test
	public void mateInOne() {
		String fen = "6k1/5ppp/8/8/8/8/8/3R2K1 w - - 0 1";
		new GameFromFEN(game, fen).execute();
		
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		BestMove bestMove = search.execute(2);
		System.out.println("play: " + asString(bestMove.move()));
	}
	
//	@Test
//	public void testWhiteStarts() {
//		Game game = new GameStartPos().startPos();
//		Search search = new Search(game);
//		Move move = search.execute(4);
//	}
//
	@Test
	public void testWhiteStarts1() {
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		Move move = search.execute(8).move();
		System.out.println("play: " + asString(move));
		new MakeMove(game).makeMove(move);
	}
	
//	@Test
//	public void test1() {
//		String[] moves = "e2e4 e7e6 d2d4 d8f6 g1f3".split(" ");
//		for (String move : moves)
//			uciMoveMaker.move(move);
//		
//		SearchAlphaBeta search = new SearchAlphaBeta(game, currentMoveConsumer());
//		Move move = search.execute(1).move();
//		String moveAsString = move.from().toString()+ move.to().toString();
//		System.out.println(moveAsString);
//	}
	
	
	private Consumer<Nodes> nodeStatisticsConsumer() {
		return (ns) -> System.out.println("nodes: " + ns.count() + ", nps: " + ns.nps());
	}

	private IntConsumer depthConsumer() {
		return (int depth) -> System.out.println("depth: " + depth);
	}

	private Consumer<BestMove> bestMoveConsumer() {
		return (bm) -> System.out.println("bestmove: " + asString(bm.move()));
	}

	private Consumer<AnalyzedMove> currentMoveConsumer() {
		return (cm) -> currentMove(cm); 
	}
	
	private void currentMove(AnalyzedMove move) {
		System.out.println(format("%s: %s/%s", asString(move.move()), move.moveNumber(), move.numberOfPossibleMoves()));
	}

	private static String asString(Move move) {
		return move.from().toString() + move.to().toString();
	}
	
}
