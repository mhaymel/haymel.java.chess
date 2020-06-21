/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchInfoImpl.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.StringFromMove;

public class SearchAlphaBetaAvoidStalemateTest {

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "3k4/8/3KP3/8/8/8/8/8 w - - 5 6";
		Game game = new GameFromFEN(fen).value();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(6);
		assertThat(asString(bestMove.move()), not("e6e7"));
	}
	
	private static String asString(int move) {
		return new StringFromMove(move).value();
	}
	
}
