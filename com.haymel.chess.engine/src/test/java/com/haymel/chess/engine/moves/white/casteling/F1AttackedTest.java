/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	26.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.casteling;

import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.moves.white.casteling.F1Attacked.f1Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
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

public class F1AttackedTest {

	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnE2() {
		blackPawn(e2);
		assertThat(f1Attacked(board), is(true));
	}

	@Test
	public void testBlackPwanOnF2() {
		blackPawn(f2);
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnG2() {
		blackPawn(g2);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testWhitePwanOnE2() {
		whitePawn(e2);
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnF2() {
		whitePawn(f2);
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnG2() {
		whitePawn(g2);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnD2() {
		blackKnight(d2);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnE3() {
		blackKnight(e3);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnG3() {
		blackKnight(g3);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnH2() {
		blackKnight(h2);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnD2() {
		whiteKnight(d2);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnE3() {
		whiteKnight(e3);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnG3() {
		whiteKnight(g3);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnH2() {
		whiteKnight(h2);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackRookOnF8() {
		blackRook(f8);
		assertThat(f1Attacked(board), is(true));
	}

	@Test
	public void testBlackQueenF8() {
		blackQueen(f8);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackBishopF8() {
		blackBishop(f8);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackRookOnF8() {
		blackRook(f8);
		whiteRook(f7);
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testBlackBishopA6() {
		blackBishop(a6);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenA6() {
		blackQueen(a6);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackRookA6() {
		blackRook(a6);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenA6() {
		blackQueen(a6);
		whiteBishop(b5);
		assertThat(f1Attacked(board), is(false));
	}

	@Test
	public void testBlackBishopH3() {
		blackBishop(h3);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenH3() {
		blackQueen(h3);
		assertThat(f1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackRookH3() {
		blackRook(h3);
		assertThat(f1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenH3() {
		blackQueen(h3);
		whiteBishop(g2);
		assertThat(f1Attacked(board), is(false));
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
		Piece p = new Piece(t);
		p.field(f);
		board.place(p);
		return p;
	}
	
}
