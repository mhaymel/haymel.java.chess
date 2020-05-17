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

public class SearchAlphaBetaItIsBlacksTurnButBlackIsAlreadyStalemateTest {

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
		String fen = "7k/4N3/8/5NN1/8/8/8/6K1 b - - 0 73";
		Game game = new GameFromFEN(fen).value();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.value(), is(0));
		assertThat(bestMove.variant(), is(nullValue()));
	}

}
