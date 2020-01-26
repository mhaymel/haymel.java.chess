/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import org.junit.Test;

import com.haymel.chess.engine.Engine;
import com.haymel.chess.engine.moves.Move;

public class SearchTest {

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
		SearchImpl search = new SearchImpl(engine.game());
			Move move = search.execute(4);
			String moveAsString = move.from().toString()+ move.to().toString();
			System.out.println(moveAsString);
			engine.move(moveAsString);
//		}
	}
	
	@Test
	public void test1() {
		Engine engine = new Engine();
		String[] moves = "e2e4 e7e6 d2d4 d8f6 g1f3".split(" ");
		for (String move : moves)
			engine.move(move);
		
		SearchImpl search = new SearchImpl(engine.game());
		Move move = search.execute(1);
		String moveAsString = move.from().toString()+ move.to().toString();
		System.out.println(moveAsString);
	}
	
}
