/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	10.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;

public class SearchAlphaBetaQuietSearchTest {

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "rnbqkbnr/1pppppp1/7p/8/p1BPPB2/5N2/PPP2PPP/RN1QK2R b KQkq - 1 5 ";
		Game game = new GameFromFEN(fen).value();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove bestMove = search.execute(1);

		assertThat(bestMove.value(), is(120));
	}
	
}
