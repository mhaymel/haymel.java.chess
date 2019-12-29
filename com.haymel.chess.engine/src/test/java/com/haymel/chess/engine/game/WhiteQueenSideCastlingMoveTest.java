/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class WhiteQueenSideCastlingMoveTest {

	private Game game;
	private Piece king;
	private Piece rook;
	
	@Before
	public void setup() {
		game = new Game();

		king = new Piece(WhiteKing);
		king.field(e1);
		king.setMoved(false);
		game.addWhite(king);
		game.place(king);
		
		rook = new Piece(WhiteRook);
		rook.field(a1);
		rook.setMoved(false);
		game.addWhite(rook);
		game.place(rook);
	}
	
	@Test
	public void makeAndUndo() {
		Move e1c1 = new Move(e1, c1, queensideCastling);
		
		game.makeMove(e1c1);
		assertThat(king.field(), is(c1));
		assertThat(king.moved(), is(true));
		assertThat(game.piece(c1), is(king));
		assertThat(game.piece(e1).free(), is(true));

		assertThat(rook.field(), is(d1));
		assertThat(rook.moved(), is(true));
		assertThat(game.piece(d1), is(rook));
		assertThat(game.piece(a1).free(), is(true));
		
		assertThat(game.halfMoveClock(), is(1));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		
		game.undoMove();
		assertThat(king.field(), is(e1));
		assertThat(king.moved(), is(false));
		assertThat(game.piece(e1), is(king));
		assertThat(game.piece(d1).free(), is(true));
		assertThat(game.piece(c1).free(), is(true));
		assertThat(game.piece(a1).whiteRook(), is(true));
		assertThat(game.piece(a1).moved(), is(false));
		assertThat(game.piece(a1), is(rook));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		game.enPassant(c3);

		Move e1c1 = new Move(e1, c1, queensideCastling);
		game.makeMove(e1c1);
		assertThat(game.enPassant(), is(removed));
		
		game.undoMove();
		assertThat(game.enPassant(), is(c3));
	}
	
}
