/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;

public class SearchAlphaBetaItIsWhitesTurnButWhiteIsAlreadyStalemateTest {


	@Test
	public void test0() {
		test(0);
	}
	
	@Test
	public void test1() {
		test(1);
	}
	
	@Test
	public void test2() {
		test(2);
	}

	@Test
	public void test3() {
		test(3);
	}

	@Test
	public void test4() {
		test(4);
	}

	@Test
	public void test5() {
		test(5);
	}

	@Test
	public void test100() {
		test(100);
	}

	private void test(int depth) {
		String fen = "3k4/8/8/8/5nn1/8/4n3/7K w - - 0 73";
		Game game = new GameFromFEN(fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.value(), is(0));
		assertThat(bestMove.variant(), is(nullValue()));
	}

}
