/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b3;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.Move.newMove;
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

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class WhitePawnMoveTest {

	private Moves moves;
	private Piece[] board;
	private WhitePawnMoves pawnMoves;
	
	@Before
	public void setup() {
		moves = new Moves();
		board = newBoard();
		pawnMoves = new WhitePawnMoves(board);
	}
	
	@Test
	public void testA2() {
		pawnMoves.generate(whitePawn(a2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
 		assertThat(result.contains(newMove(a2, a3, pawn)), is(true));
		assertThat(result.contains(newMove(a2, a4, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testB2() {
		pawnMoves.generate(whitePawn(b2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(b2, b3, pawn)), is(true));
		assertThat(result.contains(newMove(b2, b4, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testC2() {
		pawnMoves.generate(whitePawn(c2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(c2, c3, pawn)), is(true));
		assertThat(result.contains(newMove(c2, c4, pawnDoubleStep)), is(true));
	}

	@Test
	public void testD2() {
		pawnMoves.generate(whitePawn(Field.d2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(d2, d3, pawn)), is(true));
		assertThat(result.contains(newMove(d2, d4, pawnDoubleStep)), is(true));
	}

	@Test
	public void testE2() {
		pawnMoves.generate(whitePawn(e2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
 		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
		assertThat(result.contains(newMove(e2, e4, pawnDoubleStep)), is(true));
	}

	@Test
	public void testF2() {
		pawnMoves.generate(whitePawn(f2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(f2, f3, pawn)), is(true));
		assertThat(result.contains(newMove(f2, f4, pawnDoubleStep)), is(true));
	}

	@Test
	public void testG2() {
		pawnMoves.generate(whitePawn(g2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(g2, g3, pawn)), is(true));
		assertThat(result.contains(newMove(g2, g4, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testH2() {
		pawnMoves.generate(whitePawn(h2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(h2, h3, pawn)), is(true));
		assertThat(result.contains(newMove(h2, h4, pawnDoubleStep)), is(true));
	}
	
	@Test
	public void testE3() {
		pawnMoves.generate(whitePawn(e3), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e3, e4, pawn)), is(true));
	}
	
	@Test
	public void testE2MoveBlockedByWhitePiece() {
		piece(e3, WhiteBishop);
		pawnMoves.generate(whitePawn(e2), removed, moves);
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE2MoveBlockedByBlackPiece() {
		piece(e3, BlackBishop);
		pawnMoves.generate(whitePawn(e2), removed, moves);
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE2DoubleMovedBlockedByWhitePiece() {
		piece(e4, WhiteBishop);
		pawnMoves.generate(whitePawn(e2), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Integer> result = movesAsSet();
 		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
	}

	@Test
	public void testE2DoubleMovedBlockedByBlackPiece() {
		piece(e4, BlackBishop);
		
		pawnMoves.generate(whitePawn(e2), removed, moves);
		
		assertThat(moves.size(), is(1));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
	}
	
	@Test
	public void testE2CaptureD3AndF3() {
		piece(d3, BlackBishop);
		piece(f3, BlackBishop);
		
		pawnMoves.generate(whitePawn(e2), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
		assertThat(result.contains(newMove(e2, e4, pawnDoubleStep)), is(true));
		assertThat(result.contains(capture(e2, d3)), is(true));
		assertThat(result.contains(capture(e2, f3)), is(true));
	}
	
	@Test
	public void testE2CannotCaptureWhitePiecesOnD3AndF3() {
		piece(d3, WhiteBishop);
		piece(f3, WhiteBishop);
		
		pawnMoves.generate(whitePawn(e2), removed, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
		assertThat(result.contains(newMove(e2, e4, pawnDoubleStep)), is(true));
	}

	@Test
	public void testE7Promotion() {
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e7, e8, promotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionRook)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionKnight)), is(true));
	}

	@Test
	public void testE7PromotionPreventedByWhitePiece() {
		piece(e8, WhiteBishop);
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void testE7PromotionPreventedByBlackPiece() {
		piece(e8, BlackBishop);
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE7CapturePromotionD8() {
		piece(e8, BlackBishop);
		piece(d8, BlackBishop);
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e7, d8, capturePromotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionRook)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionKnight)), is(true));
	}
	
	@Test
	public void testE7CapturePromotionF8() {
		piece(e8, BlackBishop);
		piece(f8, BlackBishop);
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(4));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e7, f8, capturePromotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionRook)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionKnight)), is(true));
	}
	
	@Test
	public void testPromotion() {
		piece(d8, BlackBishop);
		piece(f8, BlackBishop);
		pawnMoves.generate(whitePawn(e7), removed, moves);
		
		assertThat(moves.size(), is(12));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e7, e8, promotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionRook)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, e8, promotionKnight)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionRook)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, d8, capturePromotionKnight)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionQueen)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionRook)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionBishop)), is(true));
		assertThat(result.contains(newMove(e7, f8, capturePromotionKnight)), is(true));
	}

	@Test
	public void testEnPassantLeft() {
		piece(d5, BlackPawn);
		pawnMoves.generate(whitePawn(e5), d6, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e5, e6, pawn)), is(true));
		assertThat(result.contains(newMove(e5, d6, enpassant)), is(true));
	}

	@Test
	public void testEnPassantRight() {
		piece(f5, BlackPawn);
		pawnMoves.generate(whitePawn(e5), f6, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e5, e6, pawn)), is(true));
		assertThat(result.contains(newMove(e5, f6, enpassant)), is(true));
	}
	
	private int capture(int from, int to) {
		return newMove(from, to, capture);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece piece(int field, int type) {
		Piece p = new Piece(type, field);
		board[p.field()] = p;
		return p;
	}

	private Set<Integer> movesAsSet() {
		Set<Integer> result = new HashSet<>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
