/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		moves.add(e2, e4, normal);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(e2-e4)"));
	}
	
	@Test
	public void addTwoMoves() {
		moves.add(e2, e4, normal);
		moves.add(e2, e3, normal);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(e2-e4, e2-e3)"));
	}
	
	@Test
	public void addOneCapture() {
		moves.add(e4, d5, capture);
	
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(e4xd5)"));
	}

	@Test
	public void addTwoCaptures() {
		moves.add(e4, d5, capture);
		moves.add(d4, c5, capture);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(e4xd5, d4xc5)"));
	}

	@Test
	public void addWhiteKingSideCastling() {
		moves.add(e1, g1, kingsideCastling);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O)"));
	}
	
	@Test
	public void addWhiteQueenSideCastling() {
		moves.add(e1, c1, queensideCastling);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O-O)"));
	}

	@Test
	public void addWhiteCastling() {
		moves.add(e1, g1, kingsideCastling);
		moves.add(e1, c1, queensideCastling);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(O-O, O-O-O)"));
	}
	
	@Test
	public void addBlackKingSideCastling() {
		moves.add(e8, g8, kingsideCastling);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O)"));
	}
	
	@Test
	public void addBlackQueenSideCastling() {
		moves.add(e8, c8, queensideCastling);
		
		assertThat(moves.size(), is(1));
		assertThat(moves.toString(), is("Moves(O-O-O)"));
	}

	@Test
	public void addBlackCastling() {
		moves.add(e8, g8, kingsideCastling);
		moves.add(e8, c8, queensideCastling);
		
		assertThat(moves.size(), is(2));
		assertThat(moves.toString(), is("Moves(O-O, O-O-O)"));
	}
	
	@Test
	public void findMoveReturnsEmptyListIfNoMoveWasFound() {
		assertThat(moves.findMoves(e2, e4).size(), is(0));
	}

	@Test
	public void findMoveReturnsTheRightMove() {
		moves.add(e2, e4, normal);
		moves.add(e2, e3, normal);

		List<Move> foundMoves = moves.findMoves(e2, e4);
		assertThat(foundMoves.size(), is(1));
		Move move = foundMoves.get(0);
		assertThat(move.from(), is(e2));
		assertThat(move.to(), is(e4));
	}

}
