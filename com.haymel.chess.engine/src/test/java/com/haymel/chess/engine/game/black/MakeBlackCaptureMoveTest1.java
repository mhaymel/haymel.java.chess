/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeBlackCaptureMoveTest1 {

	@Test
	public void makeAndUndo() {
		Game game = fromFen("7k/8/8/8/8/8/2P5/n6K b - - 13 5");

		int move = find("a1c2", game);
		assertThat(Move.type(move), is(capture));
		
		makeMove(move, game);
		assertThat(game.piece(c2).type(), is(BlackKnight));
		assertThat(game.piece(a1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(6));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.piece(a1).type(), is(BlackKnight));
		assertThat(game.piece(c2).type(), is(WhitePawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Game game = fromFen("7k/8/8/8/7P/8/2P5/n6K b - h3 13 5");

		int move = find("a1c2", game);
		assertThat(Move.type(move), is(capture));
		
		makeMove(move, game);
		assertThat(game.piece(c2).type(), is(BlackKnight));
		assertThat(game.piece(a1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(6));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.piece(a1).type(), is(BlackKnight));
		assertThat(game.piece(c2).type(), is(WhitePawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(h3));
	}

}
