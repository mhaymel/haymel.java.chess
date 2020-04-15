/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Board.newBoard;
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

import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class F8AttackedTest {

	private Piece[] board;
	
	@Before
	public void setup() {
		board = newBoard();
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

	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whiteKing(int field) {
		return piece(field, WhiteKing);
	}

	private Piece whiteQueen(int field) {
		return piece(field, WhiteQueen);
	}
	
	private Piece blackRook(int field) {
		return piece(field, BlackRook);
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
