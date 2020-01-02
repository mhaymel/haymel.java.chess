/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h6;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.moves.black.castling.F8Attacked.f8Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
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

public class F8AttackedTest {

	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnE8() {
		whitePawn(e7);
		assertThat(f8Attacked(board), is(true));
	}

	@Test
	public void testWhitePwanOnF7() {
		whitePawn(f7);
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnG7() {
		whitePawn(g7);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackPwanOnE7() {
		blackPawn(e7);
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnF7() {
		blackPawn(f7);
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnG7() {
		blackPawn(g7);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnD7() {
		whiteKnight(d7);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnE6() {
		whiteKnight(e6);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnG6() {
		whiteKnight(g6);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnH7() {
		whiteKnight(h7);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnD7() {
		blackKnight(d7);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnE6() {
		blackKnight(e6);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnG6() {
		blackKnight(g6);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnH7() {
		blackKnight(h7);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteRookOnF1() {
		whiteRook(f1);
		assertThat(f8Attacked(board), is(true));
	}

	@Test
	public void testWhiteQueenF1() {
		whiteQueen(f1);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteBishopF1() {
		whiteBishop(f1);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteRookOnF1() {
		whiteRook(f1);
		blackRook(f7);
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testWhiteBishopA3() {
		whiteBishop(a3);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenA3() {
		whiteQueen(a3);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookA3() {
		whiteRook(a3);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenA3() {
		whiteQueen(a3);
		blackBishop(b4);
		assertThat(f8Attacked(board), is(false));
	}

	@Test
	public void testWhiteBishopH6() {
		whiteBishop(h6);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenH6() {
		whiteQueen(h6);
		assertThat(f8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookH6() {
		whiteRook(h6);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenH6() {
		whiteQueen(h6);
		blackBishop(g7);
		assertThat(f8Attacked(board), is(false));
	}
	
	@Test
	public void whiteKingOnG7AttacksF8() {
		whiteKing(g7);
		assertThat(f8Attacked(board), is(true));
	}

	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}
	
	private Piece whiteKing(Field f) {
		return piece(f, WhiteKing);
	}

	private Piece whiteQueen(Field f) {
		return piece(f, WhiteQueen);
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
