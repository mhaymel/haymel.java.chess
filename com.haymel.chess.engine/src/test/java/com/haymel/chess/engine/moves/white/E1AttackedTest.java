/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.moves.white.E1Attacked.e1Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class E1AttackedTest {

	private Board board;
	private Piece king = new Piece(WhiteKing);
	
	@Before
	public void setup() {
		board = new Board();
	}
	
	@Test
	public void testOnlyWhiteKing() {
		king(e1);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnD2() {
		king(e1);
		blackPawn(d2);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPwanOnE2() {
		king(e1);
		blackPawn(e2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnf2() {
		king(e1);
		blackPawn(f2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testWhitePwanOnD2() {
		king(e1);
		whitePawn(d2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnE2() {
		king(e1);
		whitePawn(e2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnf2() {
		king(e1);
		whitePawn(f2);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testBlackRookOnA1() {
		king(e1);
		blackRook(a1);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenOnA1() {
		king(e1);
		blackQueen(a1);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPieceBlocksBlackQueenOnA1() {
		king(e1);
		blackQueen(a1);
		blackBishop(b1);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePieceBlocksBlackQueenOnA1() {
		king(e1);
		blackQueen(a1);
		whiteBishop(b1);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackRookOnH1() {
		king(e1);
		blackRook(h1);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackQueenOnH1() {
		king(e1);
		blackQueen(h1);
		assertThat(e1Attacked(board), is(true));
	}

	@Test
	public void testBlackPieceBlocksBlackQueenOnH1() {
		king(e1);
		blackQueen(h1);
		blackBishop(g1);
		assertThat(e1Attacked(board), is(false));
	}

	@Test
	public void testWhitePieceBlocksBlackQueenOnH1() {
		king(e1);
		blackQueen(h1);
		whiteBishop(g1);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnC2() {
		king(e1);
		blackKnight(c2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnD3() {
		king(e1);
		blackKnight(d3);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnF3() {
		king(e1);
		blackKnight(f3);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnG2() {
		king(e1);
		blackKnight(g2);
		assertThat(e1Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnC2() {
		king(e1);
		whiteKnight(c2);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnD3() {
		king(e1);
		whiteKnight(d3);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnF3() {
		king(e1);
		whiteKnight(f3);
		assertThat(e1Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnG2() {
		king(e1);
		whiteKnight(g2);
		assertThat(e1Attacked(board), is(false));
	}
	
	private Piece king(Field f) {
		king.field(f);
		board.place(king);
		king.setMoved(true);
		return king;
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
	
	private Piece piece(Field f, PieceType t) {
		Piece p = new Piece(t);
		p.field(f);
		board.place(p);
		return p;
	}
	
}
