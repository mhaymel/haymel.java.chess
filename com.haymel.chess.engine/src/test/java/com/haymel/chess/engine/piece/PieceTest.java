/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	20.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.Piece.border;
import static com.haymel.chess.engine.piece.Piece.free;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PieceTest {

	@Test
	public void testBorder() {
		assertThat(border.free(), is(false));
		assertThat(border.white(), is(false));
		assertThat(border.black(), is(false));
		assertThat(border.field(), is(removed));
	}

	@Test
	public void testFree() {
		assertThat(free.free(), is(true));
		assertThat(free.white(), is(false));
		assertThat(free.black(), is(false));
		assertThat(free.field(), is(removed));
	}
	
	@Test
	public void testWhitePawn() {
		Piece piece = new Piece(WhitePawn);

		assertThat(piece.type(), is(WhitePawn));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));

		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
	}

	@Test
	public void testWhiteRook() {
		Piece piece = new Piece(WhiteRook);
		
		assertThat(piece.type(), is(WhiteRook));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(true));
	}
	
	@Test
	public void testWhiteKnight() {
		Piece piece = new Piece(WhiteKnight);

		assertThat(piece.type(), is(WhiteKnight));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));

		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
	}

	@Test
	public void testWhiteBishop() {
		Piece piece = new Piece(WhiteBishop);

		assertThat(piece.type(), is(WhiteBishop));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));

		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
	}

	@Test
	public void testWhiteWhiteQueen() {
		Piece piece = new Piece(WhiteQueen);

		assertThat(piece.type(), is(WhiteQueen));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));

		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
	}

}
