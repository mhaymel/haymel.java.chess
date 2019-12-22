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
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.piece.Piece;

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
	
}
