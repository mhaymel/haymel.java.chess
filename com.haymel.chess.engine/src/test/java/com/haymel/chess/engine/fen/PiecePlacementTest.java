/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	10.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
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

import java.util.List;

import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.fen.PiecePlacement;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class PiecePlacementTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new PiecePlacement(null);
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new PiecePlacement("");
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithTooLessRanksThrowsException() {
		new PiecePlacement("8/8/8/8/8/8/8");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithTooManyRanksThrowsException() {
		new PiecePlacement("8/8/8/8/8/8/8/8/8");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithIllegalDigitThrowsException() {
		new PiecePlacement("8/8/8/9/8/8/8/8");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithIllegalCharacterThrowsException() {
		new PiecePlacement("8/8/8/Xnbqkbnr/8/8/8/8");
	}

	@Test
	public void testStartPosition() {
		List<Piece> pieces = new PiecePlacement("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR").value();
		assertThat(pieces.size(), is(32));
		assertThat(contains(pieces, BlackPawn, a7), is(true));
		assertThat(contains(pieces, BlackPawn, b7), is(true));
		assertThat(contains(pieces, BlackPawn, c7), is(true));
		assertThat(contains(pieces, BlackPawn, d7), is(true));
		assertThat(contains(pieces, BlackPawn, e7), is(true));
		assertThat(contains(pieces, BlackPawn, f7), is(true));
		assertThat(contains(pieces, BlackPawn, g7), is(true));
		assertThat(contains(pieces, BlackPawn, h7), is(true));
		assertThat(contains(pieces, BlackRook, a8), is(true));
		assertThat(contains(pieces, BlackKnight, b8), is(true));
		assertThat(contains(pieces, BlackBishop, c8), is(true));
		assertThat(contains(pieces, BlackQueen, d8), is(true));
		assertThat(contains(pieces, BlackKing, e8), is(true));
		assertThat(contains(pieces, BlackBishop, f8), is(true));
		assertThat(contains(pieces, BlackKnight, g8), is(true));
		assertThat(contains(pieces, BlackRook, h8), is(true));

		assertThat(contains(pieces, WhitePawn, a2), is(true));
		assertThat(contains(pieces, WhitePawn, b2), is(true));
		assertThat(contains(pieces, WhitePawn, c2), is(true));
		assertThat(contains(pieces, WhitePawn, d2), is(true));
		assertThat(contains(pieces, WhitePawn, e2), is(true));
		assertThat(contains(pieces, WhitePawn, f2), is(true));
		assertThat(contains(pieces, WhitePawn, g2), is(true));
		assertThat(contains(pieces, WhitePawn, h2), is(true));
		assertThat(contains(pieces, WhiteRook, a1), is(true));
		assertThat(contains(pieces, WhiteKnight, b1), is(true));
		assertThat(contains(pieces, WhiteBishop, c1), is(true));
		assertThat(contains(pieces, WhiteQueen, d1), is(true));
		assertThat(contains(pieces, WhiteKing, e1), is(true));
		assertThat(contains(pieces, WhiteBishop, f1), is(true));
		assertThat(contains(pieces, WhiteKnight, g1), is(true));
		assertThat(contains(pieces, WhiteRook, h1), is(true));
	}

	@Test
	public void testKiwipete() {
		List<Piece> pieces = new PiecePlacement("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R").value();
		assertThat(pieces.size(), is(32));
		assertThat(contains(pieces, BlackRook, a8), is(true));
		assertThat(contains(pieces, BlackKing, e8), is(true));
		assertThat(contains(pieces, BlackRook, h8), is(true));
		
		assertThat(contains(pieces, BlackPawn, a7), is(true));
		assertThat(contains(pieces, BlackPawn, c7), is(true));
		assertThat(contains(pieces, BlackPawn, d7), is(true));
		assertThat(contains(pieces, BlackQueen, e7), is(true));
		assertThat(contains(pieces, BlackPawn, f7), is(true));
		assertThat(contains(pieces, BlackBishop, g7), is(true));

		assertThat(contains(pieces, BlackBishop, a6), is(true));
		assertThat(contains(pieces, BlackKnight, b6), is(true));
		assertThat(contains(pieces, BlackPawn, e6), is(true));
		assertThat(contains(pieces, BlackKnight, f6), is(true));
		assertThat(contains(pieces, BlackPawn, g6), is(true));
		
		assertThat(contains(pieces, WhitePawn, d5), is(true));
		assertThat(contains(pieces, WhiteKnight, e5), is(true));

		assertThat(contains(pieces, BlackPawn, b4), is(true));
		assertThat(contains(pieces, WhitePawn, e4), is(true));

		assertThat(contains(pieces, WhiteKnight, c3), is(true));
		assertThat(contains(pieces, WhiteQueen, f3), is(true));

		assertThat(contains(pieces, WhitePawn, a2), is(true));
		assertThat(contains(pieces, WhitePawn, b2), is(true));
		assertThat(contains(pieces, WhitePawn, c2), is(true));
		assertThat(contains(pieces, WhiteBishop, d2), is(true));
		assertThat(contains(pieces, WhiteBishop, e2), is(true));
		assertThat(contains(pieces, WhitePawn, f2), is(true));
		assertThat(contains(pieces, WhitePawn, b2), is(true));
		assertThat(contains(pieces, WhitePawn, c2), is(true));

		assertThat(contains(pieces, WhiteRook, a1), is(true));
		assertThat(contains(pieces, WhiteKing, e1), is(true));
		assertThat(contains(pieces, WhiteRook, h1), is(true));
	}
	
	private boolean contains(List<Piece> pieces, PieceType type, Field field) {
		for (Piece piece : pieces)
			if (piece.type() == type && piece.field() == field)
				return true;
		
		return false;
	}
}
