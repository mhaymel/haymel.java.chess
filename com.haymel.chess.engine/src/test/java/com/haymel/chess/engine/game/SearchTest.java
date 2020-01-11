/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static org.junit.Assert.*;

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
			Search search = new Search(engine.game());
			Move move = search.execute(1);
			String moveAsString = move.from().toString()+ move.to().toString();
			System.out.println(moveAsString);
			engine.move(moveAsString);
		}
//	}
}
