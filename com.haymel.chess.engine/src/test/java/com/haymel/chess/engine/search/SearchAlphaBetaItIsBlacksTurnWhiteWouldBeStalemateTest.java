/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchInfoImpl.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;

public class SearchAlphaBetaItIsBlacksTurnWhiteWouldBeStalemateTest {

	@Test
	public void test() {
		test(2);
		test(3);
		test(4);
		test(5);
		test(6);
		test(7);
	}

	private void test(int depth) {
		String fen = "4k3/8/8/8/6n1/4n3/4n3/7K b - - 0 73";		
		Game game = new GameFromFEN(fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.variant(), is(notNullValue()));
		
		new MakeMove(game).makeMove(bestMove.move());
		
		bestMove = search.execute(0);
		assertThat(bestMove.variant(), is(notNullValue()));
	}

}
