/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.casteling;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.moves.white.castling.E1Attacked.e1Attacked;
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

import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class E1AttackedTest {

	private Piece[] board;
	
	@Before
	public void setup() {
		board = newBoard();
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnD2() {
		blackPawn(d2);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPwanOnE2() {
		blackPawn(e2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnf2() {
		blackPawn(f2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testWhitePwanOnD2() {
		whitePawn(d2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnE2() {
		whitePawn(e2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnf2() {
		whitePawn(f2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackRookOnA1() {
		blackRook(a1);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenOnA1() {
		blackQueen(a1);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPieceBlocksBlackQueenOnA1() {
		blackQueen(a1);
		blackBishop(b1);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePieceBlocksBlackQueenOnA1() {
		blackQueen(a1);
		whiteBishop(b1);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackRookOnH1() {
		blackRook(h1);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenOnH1() {
		blackQueen(h1);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPieceBlocksBlackQueenOnH1() {
		blackQueen(h1);
		blackBishop(g1);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePieceBlocksBlackQueenOnH1() {
		blackQueen(h1);
		whiteBishop(g1);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnC2() {
		blackKnight(c2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnD3() {
		blackKnight(d3);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnF3() {
		blackKnight(f3);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnG2() {
		blackKnight(g2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnC2() {
		whiteKnight(c2);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnD3() {
		whiteKnight(d3);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnF3() {
		whiteKnight(f3);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnG2() {
		whiteKnight(g2);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackRookOnE8() {
		blackRook(e8);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackQueenE8() {
		blackQueen(e8);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackBishopE8() {
		blackBishop(e8);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackRookOnE8() {
		blackRook(e8);
		whiteRook(e7);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackBishopA5() {
		blackBishop(a5);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenA5() {
		blackQueen(a5);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackRookA5() {
		blackRook(a5);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenA5() {
		blackQueen(a5);
		whiteBishop(b4);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackBishopH4() {
		blackBishop(h4);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenH4() {
		blackQueen(h4);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackRookH4() {
		blackRook(h4);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhitePieceBlocksBlackQueenH4() {
		blackQueen(h4);
		whiteBishop(g3);
		assertThat(e1Attacked(board), is(false));
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}

	private Piece blackRook(int field) {
		return piece(field, BlackRook);
	}

	private Piece blackQueen(int field) {
		return piece(field, BlackQueen);
	}
	
	private Piece blackBishop(int field) {
		return piece(field, BlackBishop);
	}
	
	private Piece blackKnight(int field) {
		return piece(field, BlackKnight);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece whiteBishop(int field) {
		return piece(field, WhiteBishop);
	}
	
	private Piece whiteKnight(int field) {
		return piece(field, WhiteKnight);
	}
	
	private Piece whiteRook(int field) {
		return piece(field, WhiteRook);
	}
	
	private Piece piece(int field, PieceType t) {
		Piece p = new Piece(t, field);
		board[p.field()] = p;
		return p;
	}
	
}
