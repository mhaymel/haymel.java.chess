/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;

public class SearchAlphaBetaItIsWhitesTurnButBlackWouldBeStalemateTest {

	private static final String fen = "7k/4N3/8/5NN1/8/8/8/6K1 w - - 0 73";

	@Test
	public void test2() {
		test(2);
	}

	@Test
	public void test3() {
		test(3);
	}
	
	private void test(int depth) {
		Game game = new Game();
		new GameFromFEN(game, fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.variant(), is(notNullValue()));

		System.out.println("---------------------------");
		new MakeMove(game).makeMove(bestMove.move());
		bestMove = search.execute(0);
		assertThat(bestMove.variant(), is(notNullValue()));
	}

}
