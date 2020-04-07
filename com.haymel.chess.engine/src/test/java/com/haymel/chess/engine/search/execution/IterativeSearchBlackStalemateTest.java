/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.search.result.Result;

public class IterativeSearchBlackStalemateTest {

	private static final String fen = "4k3/4P3/3K4/8/8/8/8/8 w - - 3 1";
	
	@Test
	public void test() {
		Game game = new Game();
		new GameFromFEN(game, fen).execute();
		
		IterativeSearch search = new IterativeSearch(game, sysoutSearchInfo);
		Result result = search.execute(10_000, 10_000);
		
		assertThat(result.value(), is(0));
//		assertThat(result.moves().length, is(1));
//		assertThat(asString(result.moves()[0]), is("d6e6"));
	}

	private static String asString(Move move) {
		return move.from().toString() + move.to().toString();
	}
	
}
