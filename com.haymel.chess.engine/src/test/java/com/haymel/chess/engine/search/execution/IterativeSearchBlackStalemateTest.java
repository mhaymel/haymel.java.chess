/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.search.BestMove;

public class IterativeSearchBlackStalemateTest {

	private static final String fen = "4k3/4P3/3K4/8/8/8/8/8 w - - 3 1";
	
	@Test
	public void test() {
		Game game = new GameFromFEN(fen).value();
		
		IterativeSearch search = new IterativeSearch(game);
		BestMove bestMove = search.execute(10_000, 10_000);
		
		assertThat(bestMove.value(), is(0));
		assertThat(asString(bestMove.move()), is("d6e6"));
	}

	private static String asString(int move) {
		return Field.fieldAsString(Move.from(move)) + fieldAsString(Move.to(move));
	}
	
}
