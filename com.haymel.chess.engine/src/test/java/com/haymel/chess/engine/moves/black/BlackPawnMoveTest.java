/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h6;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class BlackPawnMoveTest {

	private Moves moves;
	private Piece[] board;
	private BlackPawnMoves pawnMoves;
	
	@Before
	public void setup() {
		moves = new Moves();
		board = newBoard();
		pawnMoves = new BlackPawnMoves(board);
	}
	
	@Test
	public void testA7() {
		pawnMoves.generate(blackPawn(a7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
 		assertThat(result.contains(new Move(a7, a6, pawn)), is(true));
		assertThat(result.contains(new Move(a7, a5, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testB7() {
		pawnMoves.generate(blackPawn(b7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(b7, b6, pawn)), is(true));
		assertThat(result.contains(new Move(b7, b5, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testC7() {
		pawnMoves.generate(blackPawn(c7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(c7, c6, pawn)), is(true));
		assertThat(result.contains(new Move(c7, c5, pawnDoubleStep)), is(true));
	}

	@Test
	public void testD7() {
		pawnMoves.generate(blackPawn(d7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(d7, d6, pawn)), is(true));
		assertThat(result.contains(new Move(d7, d5, pawnDoubleStep)), is(true));
	}

	@Test
	public void testE7() {
		pawnMoves.generate(blackPawn(e7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
 		assertThat(result.contains(new Move(e7, e6, pawn)), is(true));
		assertThat(result.contains(new Move(e7, e5, pawnDoubleStep)), is(true));
	}

	@Test
	public void testF7() {
		pawnMoves.generate(blackPawn(f7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(f7, f6, pawn)), is(true));
		assertThat(result.contains(new Move(f7, f5, pawnDoubleStep)), is(true));
	}

	@Test
	public void testG7() {
		pawnMoves.generate(blackPawn(g7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(g7, g6, pawn)), is(true));
		assertThat(result.contains(new Move(g7, g5, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testH7() {
		pawnMoves.generate(blackPawn(h7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(h7, h6, pawn)), is(true));
		assertThat(result.contains(new Move(h7, h5, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testE6() {
		pawnMoves.generate(blackPawn(e6), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e6, e5, pawn)), is(true));
	}
	
	@Test
	public void testE6MoveBlockedByBlackPiece() {
		piece(e6, BlackBishop);
		pawnMoves.generate(blackPawn(e7), removed, moves);
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE6MoveBlockedByWhitePiece() {
		piece(e6, WhiteBishop);
		pawnMoves.generate(blackPawn(e7), removed, moves);
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE7DoubleMovedBlockedByBlackPiece() {
		piece(e5, BlackBishop);
		pawnMoves.generate(blackPawn(e7), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Move> result = movesAsSet();
 		assertThat(result.contains(new Move(e7, e6, pawn)), is(true));
	}

	@Test
	public void testE2DoubleMovedBlockedByWhitePiece() {
		piece(e5, WhiteBishop);
		
		pawnMoves.generate(blackPawn(e7), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e7, e6, pawn)), is(true));
	}
	
	@Test
	public void testE7CaptureD6AndF6() {
		piece(d6, WhiteBishop);
		piece(f6, WhiteBishop);
		
		pawnMoves.generate(blackPawn(e7), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e7, e6, pawn)), is(true));
		assertThat(result.contains(new Move(e7, e5, pawnDoubleStep)), is(true));
		assertThat(result.contains(capture(e7, d6)), is(true));
		assertThat(result.contains(capture(e7, f6)), is(true));
	}
	
	@Test
	public void testE7CannotCaptureWhitePiecesOnD6AndF6() {
		piece(d6, BlackBishop);
		piece(f6, BlackBishop);
		
		pawnMoves.generate(blackPawn(e7), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e7, e6, pawn)), is(true));
		assertThat(result.contains(new Move(e7, e5, pawnDoubleStep)), is(true));
	}

	@Test
	public void testE2Promotion() {
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e2, e1, promotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionRook)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionKnight)), is(true));
	}

	@Test
	public void testE2PromotionPreventedByBlackPiece() {
		piece(e1, BlackBishop);
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void testE2PromotionPreventedByWhitePiece() {
		piece(e1, WhiteBishop);
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE2CapturePromotionD1() {
		piece(e1, WhiteBishop);
		piece(d1, WhiteBishop);
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e2, d1, capturePromotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionRook)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionKnight)), is(true));
	}
	
	@Test
	public void testE7CapturePromotionF1() {
		piece(e1, WhiteBishop);
		piece(f1, WhiteBishop);
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e2, f1, capturePromotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionRook)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionKnight)), is(true));
	}
	
	@Test
	public void testPromotion() {
		piece(d1, WhiteBishop);
		piece(f1, WhiteBishop);
		pawnMoves.generate(blackPawn(e2), removed, moves);
		
		assertThat(moves.size(), is(12));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e2, e1, promotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionRook)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, e1, promotionKnight)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionRook)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, d1, capturePromotionKnight)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionQueen)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionRook)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionBishop)), is(true));
		assertThat(result.contains(new Move(e2, f1, capturePromotionKnight)), is(true));
	}

	@Test
	public void testEnPassantLeft() {
		piece(d4, WhitePawn);
		pawnMoves.generate(blackPawn(e4), d3, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, e3, pawn)), is(true));
		assertThat(result.contains(new Move(e4, d3, enpassant)), is(true));
	}

	@Test
	public void testEnPassantRight() {
		piece(e4, WhitePawn);
		pawnMoves.generate(blackPawn(d4), e3, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(d4, d3, pawn)), is(true));
		assertThat(result.contains(new Move(d4, e3, enpassant)), is(true));
	}
	
	private Move capture(int from, int to) {
		return new Move(from, to, capture);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece piece(int field, int type) {
		Piece p = new Piece(type, field);
		board[p.field()] = p;
		return p;
	}

	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
