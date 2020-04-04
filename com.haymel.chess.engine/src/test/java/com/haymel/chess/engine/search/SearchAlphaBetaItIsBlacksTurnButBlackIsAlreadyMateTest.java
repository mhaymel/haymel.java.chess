/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchAlphaBeta.MAX_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;

public class SearchAlphaBetaItIsBlacksTurnButBlackIsAlreadyMateTest {

	private static final String fen = "3R2k1/5ppp/8/8/8/8/8/6K1 w - - 0 73 ";

	@Test
	public void test() {
		test(0);
		test(1);
		test(2);
		test(3);
		test(4);
		test(5);
		test(100);
	}

	private void test(int depth) {
		Game game = new Game();
		new GameFromFEN(game, fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.value(), is(MAX_VALUE));
		assertThat(bestMove.variant(), is(nullValue()));
	}

}
