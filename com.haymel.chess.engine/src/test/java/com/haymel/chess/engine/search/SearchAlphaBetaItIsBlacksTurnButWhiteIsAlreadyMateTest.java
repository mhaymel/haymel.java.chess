/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchAlphaBeta.MIN_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;

public class SearchAlphaBetaItIsBlacksTurnButWhiteIsAlreadyMateTest {

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
		String fen = "3k4/8/8/8/8/8/5PPP/3r2K1 b - - 0 73";
		Game game = new GameFromFEN(fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.value(), is(MIN_VALUE));
		assertThat(bestMove.variant(), is(nullValue()));
	}

}
