/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.TestHelper;

public class MakeBlackCaptureMoveTest2 {

	@Test
	public void makeAndUndo() {
		Game game = TestHelper.fromFen("r6k/8/8/8/8/8/8/R6K b - - 13 5");
		int move = TestHelper.find("a8a1", game);
		
		makeMove(move, game);
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(6));
		
		undoMove(game);
		game.assertVerify();
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
	}

}
