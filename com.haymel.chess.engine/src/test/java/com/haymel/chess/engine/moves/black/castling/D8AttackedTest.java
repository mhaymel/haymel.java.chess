/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.moves.black.castling.D8Attacked.d8Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class D8AttackedTest {

	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testWhitePawnOnC7() {
		whitePawn(c7);
		assertThat(d8Attacked(board), is(true));
	}

	@Test
	public void testWhitePawnOnD7() {
		whitePawn(d7);
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testWhitePawnOnE7() {
		whitePawn(e7);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackPawnOnC7() {
		blackPawn(c7);
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testBlackPawnOnD7() {
		blackPawn(d7);
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testWhiteKnightOnB7() {
		whiteKnight(b7);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnC6() {
		whiteKnight(c6);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnE6() {
		whiteKnight(e6);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnF7() {
		whiteKnight(f7);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnB7() {
		blackKnight(b7);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnC6() {
		blackKnight(c6);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnE6() {
		blackKnight(e6);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnF7() {
		blackKnight(f7);
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testWhiteRookOnD1() {
		whiteRook(d1);
		assertThat(d8Attacked(board), is(true));
	}

	@Test
	public void testWhiteQueenD1() {
		whiteQueen(d1);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteBishopD1() {
		whiteBishop(d1);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteRookOnD1() {
		whiteRook(d1);
		blackRook(d2);
		assertThat(d8Attacked(board), is(false));
	}

	@Test
	public void testWhiteBishopA5() {
		whiteBishop(a5);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenA5() {
		whiteQueen(a5);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookA5() {
		whiteRook(a5);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenA5() {
		whiteQueen(a5);
		blackBishop(b6);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteBishopH4() {
		whiteBishop(h4);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenH4() {
		whiteQueen(h4);
		assertThat(d8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookH4() {
		whiteRook(h4);
		assertThat(d8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenH4() {
		whiteQueen(h4);
		blackBishop(g5);
		assertThat(d8Attacked(board), is(false));
	}
	
	private Piece whiteQueen(Field f) {
		return piece(f, WhiteQueen);
	}
	
	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}

	private Piece blackRook(Field f) {
		return piece(f, BlackRook);
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
