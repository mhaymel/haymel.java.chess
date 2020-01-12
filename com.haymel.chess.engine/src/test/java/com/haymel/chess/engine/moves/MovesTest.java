/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.piece.Piece;
import com.haymel.util.exception.HaymelNullPointerException;

public class MovesTest {

	private Moves moves;
	
	@Before
	public void setup() {
		moves = new Moves();
	}
	
	@Test
	public void sizeOfEmptyMovesIsZero() {
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void addOneMove() {
		moves.add(e2, e4);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(e2-e4)"));
	}
	
	@Test
	public void addTwoMoves() {
		moves.add(e2, e4);
		moves.add(e2, e3);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(e2-e4, e2-e3)"));
	}
	
	@Test
	public void addOneCapture() {
		Piece blackPawn = new Piece(BlackPawn);
		moves.addCapture(e4, d5, blackPawn);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(e4xd5)"));
	}

	@Test
	public void addTwoCaptures() {
		Piece blackPawn1 = new Piece(BlackPawn);
		Piece blackPawn2 = new Piece(BlackPawn);
		moves.addCapture(e4, d5, blackPawn1);
		moves.addCapture(d4, c5, blackPawn2);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(e4xd5, d4xc5)"));
	}

	@Test
	public void addWhiteKingSideCastling() {
		moves.addWhiteKingSideCastling();
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O)"));
	}
	
	@Test
	public void addWhiteQueenSideCastling() {
		moves.addWhiteQueenSideCastling();
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O-O)"));
	}

	@Test
	public void addWhiteCastling() {
		moves.addWhiteKingSideCastling();
		moves.addWhiteQueenSideCastling();
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(O-O, O-O-O)"));
	}
	
	@Test
	public void addBlackKingSideCastling() {
		moves.addBlackKingSideCastling();
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O)"));
	}
	
	@Test
	public void addBlackQueenSideCastling() {
		moves.addBlackQueenSideCastling();
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O-O)"));
	}

	@Test
	public void addBlackCastling() {
		moves.addBlackKingSideCastling();
		moves.addBlackQueenSideCastling();
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(O-O, O-O-O)"));
	}
	
	@Test(expected=HaymelNullPointerException.class)
	public void findMoveWithNullAsFromThrowsException() {
		moves.findMoves(null, e2);
	}
	
	@Test(expected=HaymelNullPointerException.class)
	public void findMoveWithNullAsToThrowsException() {
		moves.findMoves(e2, null);
	}

	@Test
	public void findMoveReturnsEmptyListIfNoMoveWasFound() {
		assertThat(moves.findMoves(e2, e4).size(), is(0));
	}

	@Test
	public void findMoveReturnsTheRightMove() {
		moves.add(e2, e4);
		moves.add(e2, e3);

		List<Move> foundMoves = moves.findMoves(e2, e4);
		assertThat(foundMoves.size(), is(1));
		Move move = foundMoves.get(0);
		assertThat(move.from(), is(e2));
		assertThat(move.to(), is(e4));
	}

	@Test
	public void findPromotionMoves() {
		moves.addWhitePromotion(e7);

		List<Move> foundMoves = moves.findMoves(e7, e8);
		assertThat(foundMoves.size(), is(4));

		for (Move move : foundMoves) 
			assertThat(move.type(), is(promotion));
	}
	
	@Test
	public void findCapturePromotionMoves() {
		Piece blackQueen = new Piece(BlackQueen);
		moves.addWhiteCapturePromotion(e7, d8, blackQueen);;

		List<Move> foundMoves = moves.findMoves(e7, d8);
		assertThat(foundMoves.size(), is(4));

		for (Move move : foundMoves) 
			assertThat(move.type(), is(capturePromotion));
	}
	
}
