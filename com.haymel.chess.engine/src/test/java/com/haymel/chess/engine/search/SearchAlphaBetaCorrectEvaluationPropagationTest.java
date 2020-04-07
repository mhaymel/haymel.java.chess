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

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.StartposCreator;

public class SearchAlphaBetaCorrectEvaluationPropagationTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
		new StartposCreator(game).execute();
	}

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "rnb1kbnr/ppp2ppp/8/4p3/3q4/2N5/PPP2PPP/R1BQKBNR w KQkq - 0 5";
		new GameFromFEN(game, fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(5);

		assertThat(bestMove.value(), is(-100));
	}
	
}
