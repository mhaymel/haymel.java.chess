/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;

public class MakeBlackQueenSideCastlingMoveTest {

	@Test
	public void makeAndUndo() {
		Game game = fromFen("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 13 10");
		int move = find("e8c8", game);
		
		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.halfMoveClock(), is(14));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.piece(e8), is(nullValue()));
		assertThat(game.piece(a8), is(nullValue()));
		assertThat(game.piece(c8).type(), is(BlackKing));
		assertThat(game.piece(d8).type(), is(BlackRook));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.piece(c8), is(nullValue()));
		assertThat(game.piece(d8), is(nullValue()));
		assertThat(game.piece(e8).type(), is(BlackKing));
		assertThat(game.piece(a8).type(), is(BlackRook));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Game game = fromFen("r3k2r/8/8/8/4P3/8/8/R3K2R b KQkq e3 13 10");
		int move = find("e8c8", game);
		
		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.halfMoveClock(), is(14));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.piece(e8), is(nullValue()));
		assertThat(game.piece(a8), is(nullValue()));
		assertThat(game.piece(c8).type(), is(BlackKing));
		assertThat(game.piece(d8).type(), is(BlackRook));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.piece(c8), is(nullValue()));
		assertThat(game.piece(d8), is(nullValue()));
		assertThat(game.piece(e8).type(), is(BlackKing));
		assertThat(game.piece(a8).type(), is(BlackRook));
		assertThat(game.enPassant(), is(e3));
	}
	
}
