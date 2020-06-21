/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeWhitePawnDoubleStepMoveTest {

	@Test
	public void pawnDoubleStepMove() {
		Game game = fromFen("7k/8/8/8/8/8/4P3/7K w - - 37 30");
		int move = find("e2e4", game);
		
		makeMove(move, game);
		assertThat(game.piece(Move.to(move)).type(), is(WhitePawn));
		assertThat(game.piece(e7), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(e3));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(Move.from(move)).type(), is(WhitePawn));
		assertThat(game.piece(e4), is(nullValue()));
		assertThat(game.halfMoveClock(), is(37));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2DoubleStepMoveEnPassantResetted() {
		Game game = fromFen("7k/8/8/4p3/8/8/4P3/7K w - e6 37 30");
		int move = find("e2e4", game);
		
		makeMove(move, game);
		assertThat(game.piece(Move.to(move)).type(), is(WhitePawn));
		assertThat(game.piece(e7), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(e3));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(Move.from(move)).type(), is(WhitePawn));
		assertThat(game.piece(e4), is(nullValue()));
		assertThat(game.halfMoveClock(), is(37));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(e6));
		assertThat(game.activeColor(), is(white));
	}

}
