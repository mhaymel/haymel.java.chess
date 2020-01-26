/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static java.lang.String.format;
import static org.junit.Assert.*;

import java.util.function.Consumer;

import org.junit.Test;

import com.haymel.chess.engine.Engine;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.uci.result.Infos;

public class SearchAlphaBetaTest {

//	@Test
//	public void testWhiteStarts() {
//		Game game = new GameStartPos().startPos();
//		Search search = new Search(game);
//		Move move = search.execute(4);
//	}
//
	@Test
	public void testWhiteStarts1() {
		Engine engine = new Engine();
//		for(;;) {
			SearchAlphaBeta search = new SearchAlphaBeta(engine.game(), currentMoveConsumer());
			Move move = search.execute(1).move();
			System.out.println(asString(move));
			engine.move(asString(move));
//		}
	}
	
	@Test
	public void test1() {
		Engine engine = new Engine();
		String[] moves = "e2e4 e7e6 d2d4 d8f6 g1f3".split(" ");
		for (String move : moves)
			engine.move(move);
		
		SearchAlphaBeta search = new SearchAlphaBeta(engine.game(), currentMoveConsumer());
		Move move = search.execute(1).move();
		String moveAsString = move.from().toString()+ move.to().toString();
		System.out.println(moveAsString);
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
