/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.TestHelper;
import com.haymel.chess.engine.moves.Move;

public class MakeWhiteCaptureMoveTest2 {

	@Test
	public void makeAndUndo() {
		Game game = TestHelper.fromFen("r6k/8/8/8/8/8/8/R6K w - - 13 5");
		Move move = TestHelper.find("a1a8", game);
		
		makeMove(move, game);
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(5));
		
		undoMove(game);
		game.assertVerify();
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
	}

}
