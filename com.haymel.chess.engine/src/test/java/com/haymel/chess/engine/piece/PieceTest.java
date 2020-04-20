/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	20.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.Piece.border;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
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
		assertThat(border.white(), is(false));
		assertThat(border.black(), is(false));
		assertThat(border.field(), is(removed));
	}

	@Test
	public void testWhitePawn() {
		Piece piece = new Piece(WhitePawn, removed);

		assertThat(piece.type(), is(WhitePawn));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testWhiteRook() {
		Piece piece = new Piece(WhiteRook, removed);
		
		assertThat(piece.type(), is(WhiteRook));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(true));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));

		piece.field(a1);
		piece.setMoved(false);
		assertThat(piece.field(), is(a1));
		assertThat(piece.moved(),is(false));
		
		piece.field(h1);
		piece.setMoved(false);
		assertThat(piece.field(), is(h1));
		assertThat(piece.moved(),is(false));
	}
	
	@Test
	public void testWhiteKnight() {
		Piece piece = new Piece(WhiteKnight, removed);

		assertThat(piece.type(), is(WhiteKnight));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testWhiteBishop() {
		Piece piece = new Piece(WhiteBishop, removed);

		assertThat(piece.type(), is(WhiteBishop));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testWhiteQueen() {
		Piece piece = new Piece(WhiteQueen, removed);

		assertThat(piece.type(), is(WhiteQueen));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testWhiteKing() {
		Piece piece = new Piece(WhiteKing, removed);

		assertThat(piece.type(), is(WhiteKing));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(true));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(true));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
		
		piece.field(e1);
		piece.setMoved(false);
		assertThat(piece.field(), is(e1));
		assertThat(piece.moved(),is(false));
	}
	
	@Test
	public void testBlackPawn() {
		Piece piece = new Piece(BlackPawn, removed);

		assertThat(piece.type(), is(BlackPawn));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(true));
		assertThat(piece.moved(),is(true));
	}
	
	@Test
	public void testBlackRook() {
		Piece piece = new Piece(BlackRook, removed);
		
		assertThat(piece.type(), is(BlackRook));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(true));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));

		piece.field(a8);
		piece.setMoved(false);
		assertThat(piece.field(), is(a8));
		assertThat(piece.moved(),is(false));
		
		piece.field(h8);
		piece.setMoved(false);
		assertThat(piece.field(), is(h8));
		assertThat(piece.moved(),is(false));
	}
	
	@Test
	public void testBlackKnight() {
		Piece piece = new Piece(BlackKnight, removed);

		assertThat(piece.type(), is(BlackKnight));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(true));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testBlackBishop() {
		Piece piece = new Piece(BlackBishop, removed);

		assertThat(piece.type(), is(BlackBishop));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(true));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testBlackQueen() {
		Piece piece = new Piece(BlackQueen, removed);

		assertThat(piece.type(), is(BlackQueen));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(false));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(true));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
	}

	@Test
	public void testBlackKing() {
		Piece piece = new Piece(BlackKing, removed);

		assertThat(piece.type(), is(BlackKing));
		assertThat(piece.black(), is(true));
		assertThat(piece.white(), is(false));
		assertThat(piece.blackKing(), is(true));
		assertThat(piece.whiteKing(), is(false));
		assertThat(piece.blackRook(), is(false));
		assertThat(piece.whiteRook(), is(false));
		assertThat(piece.blackQueen(), is(false));
		assertThat(piece.blackKnight(), is(false));
		assertThat(piece.blackBishop(), is(false));
		assertThat(piece.blackPawn(), is(false));
		assertThat(piece.moved(),is(true));
		
		piece.field(e8);
		piece.setMoved(false);
		assertThat(piece.field(), is(e8));
		assertThat(piece.moved(),is(false));
	}

}
