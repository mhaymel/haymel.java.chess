/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.moves.black.castling.E8Attacked.e8Attacked;
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
import com.haymel.chess.engine.piece.Piece;

public class E8AttackedTest {

	private Piece[] board;
	
	@Before
	public void setup() {
		board = Board.newBoard();
	}
	
	@Test
	public void testEmptyBoard() {
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnD7() {
		whitePawn(d7);
		assertThat(e8Attacked(board), is(true));
	}

	@Test
	public void testWhitePwanOnE7() {
		whitePawn(e7);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testWhitePwanOnf7() {
		whitePawn(f7);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackPwanOnD7() {
		blackPawn(d7);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testBlackwanOnE7() {
		blackPawn(e7);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testBlackPwanOnF7() {
		blackPawn(f7);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testWhiteRookOnA8() {
		whiteRook(a8);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenOnA8() {
		whiteQueen(a8);
		assertThat(e8Attacked(board), is(true));
	}

	@Test
	public void testWhitePieceBlocksWhiteQueenOnA8() {
		whiteQueen(a8);
		whiteBishop(b8);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testBlackPieceBlocksWhiteWhiteQueenOnA8() {
		whiteQueen(a8);
		blackBishop(b8);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testWhiteRookOnH8() {
		whiteRook(h8);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenOnH8() {
		whiteQueen(h8);
		assertThat(e8Attacked(board), is(true));
	}

	@Test
	public void testWhitePieceBlocksWhiteQueenOnH8() {
		whiteQueen(h8);
		whiteBishop(g8);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testBlackPieceBlocksWhiteQueenOnH8() {
		whiteQueen(h8);
		blackBishop(g8);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteKnightOnC7() {
		whiteKnight(c7);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnD6() {
		whiteKnight(d6);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnF6() {
		whiteKnight(f6);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteKnightOnG7() {
		whiteKnight(g7);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testBlackKnightOnC7() {
		blackKnight(c7);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnD6() {
		blackKnight(d6);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnF6() {
		blackKnight(f6);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackKnightOnG7() {
		blackKnight(g7);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testWhiteRookOnE1() {
		whiteRook(e1);
		assertThat(e8Attacked(board), is(true));
	}

	@Test
	public void testWhiteQueenE1() {
		whiteQueen(e1);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteBishopE1() {
		whiteBishop(e1);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteRookOnE1() {
		whiteRook(e1);
		blackRook(e7);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testBlackBishopA4() {
		whiteBishop(a4);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenA4() {
		whiteQueen(a4);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookA4() {
		whiteRook(a4);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenA4() {
		whiteQueen(a4);
		blackBishop(b5);
		assertThat(e8Attacked(board), is(false));
	}

	@Test
	public void testWhiteBishopH5() {
		whiteBishop(h5);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteQueenH5() {
		whiteQueen(h5);
		assertThat(e8Attacked(board), is(true));
	}
	
	@Test
	public void testWhiteRookH5() {
		blackRook(h5);
		assertThat(e8Attacked(board), is(false));
	}
	
	@Test
	public void testBlackPieceBlocksWhiteQueenH5() {
		whiteQueen(h5);
		blackBishop(g6);
		assertThat(e8Attacked(board), is(false));
	}
	
	private Piece whiteQueen(int field) {
		return piece(field, WhiteQueen);
	}

	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
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
	
	private Piece piece(int field, int type) {
		Piece p = new Piece(type, field);
		board[p.field()] = p;
		return p;
	}
	
}
