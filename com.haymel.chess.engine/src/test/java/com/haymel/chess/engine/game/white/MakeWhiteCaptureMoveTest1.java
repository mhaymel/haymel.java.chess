/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeWhiteCaptureMoveTest1 {

	@Test
	public void makeAndUndo() {
		Game game = fromFen("7k/8/8/8/8/8/2p5/N6K w - - 13 5");

		int move = find("a1c2", game);
		assertThat(Move.type(move), is(capture));
		
		makeMove(move, game);
		assertThat(game.piece(c2).type(), is(WhiteKnight));
		assertThat(game.piece(a1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.piece(a1).type(), is(WhiteKnight));
		assertThat(game.piece(c2).type(), is(BlackPawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Game game = fromFen("7k/8/8/p7/8/8/2p5/N6K w - a6 13 5");

		int move = find("a1c2", game);
		assertThat(Move.type(move), is(capture));
		
		makeMove(move, game);
		assertThat(game.piece(c2).type(), is(WhiteKnight));
		assertThat(game.piece(a1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.piece(a1).type(), is(WhiteKnight));
		assertThat(game.piece(c2).type(), is(BlackPawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(a6));
	}
	
}
