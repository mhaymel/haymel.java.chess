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

import org.junit.Before;
import org.junit.Test;

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
	
//	@Test
//	public void testWhiteStarts() {
//		Game game = new GameStartPos().startPos();
//		Search search = new Search(game);
//		Move move = search.execute(4);
//	}
//
	@Test
	public void testWhiteStarts1() {
		for(int i = 0; i < 10; i++) {
			SearchAlphaBeta2 search = new SearchAlphaBeta2(game, currentMoveConsumer(), bestMoveConsumer());
			Move move = search.execute(5).move();
			System.out.println(asString(move));
			new MakeMove(game).makeMove(move);
		}
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
	
	
	private Consumer<BestMove> bestMoveConsumer() {
		return (bm) -> System.out.println(asString(bm.move()));
	}

	private Consumer<CurrentMove> currentMoveConsumer() {
		return (cm) -> currentMove(cm); 
	}
	
	private void currentMove(CurrentMove move) {
		System.out.println(format("%s: %s/%s", asString(move.move()), move.current(), move.count()));
	}

	private static String asString(Move move) {
		return move.from().toString() + move.to().toString();
	}
	
}
