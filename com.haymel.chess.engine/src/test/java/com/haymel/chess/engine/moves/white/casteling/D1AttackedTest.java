/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	26.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.casteling;

import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b3;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.moves.white.castling.D1Attacked.d1Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class D1AttackedTest {

	private Board board;
	private Piece[] pieces;

	@Before
	public void setup() {
		board = new Board();
		pieces = board.pieces;
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testBlackPawnOnC2() {
		blackPawn(c2);
		assertThat(d1Attacked(pieces), is(true));
	}

	@Test
	public void testBlackPawnOnD2() {
		blackPawn(d2);
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testBlackPawnOnE2() {
		blackPawn(e2);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testWhitePawnOnC2() {
		whitePawn(c2);
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testWhitePawnOnD2() {
		whitePawn(d2);
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testWhitePawnOnE2() {
		whitePawn(e2);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testBlackKnightOnB2() {
		blackKnight(b2);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackKnightOnC3() {
		blackKnight(c3);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackKnightOnE3() {
		blackKnight(e3);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackKnightOnF2() {
		blackKnight(f2);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testWhiteKnightOnB2() {
		whiteKnight(b2);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhiteKnightOnC3() {
		whiteKnight(c3);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhiteKnightOnE3() {
		whiteKnight(e3);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhiteKnightOnF2() {
		whiteKnight(f2);
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testBlackRookOnD8() {
		blackRook(d8);
		assertThat(d1Attacked(pieces), is(true));
	}

	@Test
	public void testBlackQueenD8() {
		blackQueen(d8);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackBishopD8() {
		blackBishop(d8);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackRookOnD8() {
		blackRook(d8);
		whiteRook(d7);
		assertThat(d1Attacked(pieces), is(false));
	}

	@Test
	public void testBlackBishopA4() {
		blackBishop(a4);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackQueenA4() {
		blackQueen(a4);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackRookA4() {
		blackRook(a4);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenA4() {
		blackQueen(a4);
		whiteBishop(b3);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testBlackBishopH5() {
		blackBishop(h5);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackQueenH5() {
		blackQueen(h5);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	@Test
	public void testBlackRookH5() {
		blackRook(h5);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenH5() {
		blackQueen(h5);
		whiteBishop(g4);
		assertThat(d1Attacked(pieces), is(false));
	}
	
	@Test
	public void blackKingOnC2AttacksD1() {
		blackKing(c2);
		assertThat(d1Attacked(pieces), is(true));
	}
	
	private Piece blackKing(Field f) {
		return piece(f, BlackKing);
	}

	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}

	private Piece blackRook(Field f) {
		return piece(f, BlackRook);
	}

	private Piece blackQueen(Field f) {
		return piece(f, BlackQueen);
	}
	
	private Piece blackBishop(Field f) {
		return piece(f, BlackBishop);
	}
	
	private Piece blackKnight(Field f) {
		return piece(f, BlackKnight);
	}
	
	private Piece whitePawn(Field f) {
		return piece(f, WhitePawn);
	}
	
	private Piece whiteBishop(Field f) {
		return piece(f, WhiteBishop);
	}
	
	private Piece whiteKnight(Field f) {
		return piece(f, WhiteKnight);
	}
	
	private Piece whiteRook(Field f) {
		return piece(f, WhiteRook);
	}
	
	private Piece piece(Field f, PieceType t) {
		Piece p = new Piece(t, f);
		board.place(p);
		return p;
	}
	
}
