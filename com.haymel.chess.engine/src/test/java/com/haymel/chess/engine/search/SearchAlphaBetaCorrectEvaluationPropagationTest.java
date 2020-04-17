/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;

public class SearchAlphaBetaCorrectEvaluationPropagationTest {

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "rnb1kbnr/ppp2ppp/8/4p3/3q4/2N5/PPP2PPP/R1BQKBNR w KQkq - 0 5";
		Game game = new GameFromFEN(fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(5);

		assertThat(bestMove.value(), is(-100));
	}
	
}
