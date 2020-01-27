/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;

public class SearchTest {

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
//		for(;;) {
		SearchImpl search = new SearchImpl(game);
			Move move = search.execute(4);
			String moveAsString = move.from().toString()+ move.to().toString();
			System.out.println(moveAsString);
			new MakeMove(game).makeMove(move);
//		}
	}
	
//	@Test
//	public void test1() {
//		String[] moves = "e2e4 e7e6 d2d4 d8f6 g1f3".split(" ");
//		for (String move : moves)
//			uciMoveMaker.move(move);
//		
//		SearchImpl search = new SearchImpl(game);
//		Move move = search.execute(1);
//		String moveAsString = move.from().toString()+ move.to().toString();
//		System.out.println(moveAsString);
//	}
	
}
