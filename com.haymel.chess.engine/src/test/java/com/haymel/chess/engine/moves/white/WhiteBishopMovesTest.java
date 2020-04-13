/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class WhiteBishopMovesTest {

	private Moves moves;
	private Board board;
	private WhiteBishopMoves bishopMoves;
	private Piece bishop = new Piece(WhiteBishop, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = new Board();
		bishopMoves = new WhiteBishopMoves(board);
	}
	
	@Test
	public void testA1() {
		bishop(a1);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(7));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, b2)), is(true));
		assertThat(result.contains(new Move(a1, c3)), is(true));
		assertThat(result.contains(new Move(a1, d4)), is(true));
		assertThat(result.contains(new Move(a1, e5)), is(true));
		assertThat(result.contains(new Move(a1, f6)), is(true));
		assertThat(result.contains(new Move(a1, g7)), is(true));
		assertThat(result.contains(new Move(a1, h8)), is(true));
	}

	@Test
	public void testE4() {
		bishop(e4);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(13));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, d3)), is(true));
		assertThat(result.contains(new Move(e4, c2)), is(true));
		assertThat(result.contains(new Move(e4, b1)), is(true));
		assertThat(result.contains(new Move(e4, f5)), is(true));
		assertThat(result.contains(new Move(e4, g6)), is(true));
		assertThat(result.contains(new Move(e4, h7)), is(true));
		assertThat(result.contains(new Move(e4, d5)), is(true));
		assertThat(result.contains(new Move(e4, c6)), is(true));
		assertThat(result.contains(new Move(e4, b7)), is(true));
		assertThat(result.contains(new Move(e4, a8)), is(true));
		assertThat(result.contains(new Move(e4, f3)), is(true));
		assertThat(result.contains(new Move(e4, g2)), is(true));
		assertThat(result.contains(new Move(e4, h1)), is(true));
	}

	@Test
	public void testE4Capture() {
		bishop(e4);
		blackPawn(d5);
		blackPawn(f3);
		blackPawn(d3);
		blackPawn(f5);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(4));
		Set<Move> result = movesAsSet();
		assertThat(result.contains(capture(e4, d5)), is(true));
		assertThat(result.contains(capture(e4, f3)), is(true));
		assertThat(result.contains(capture(e4, d3)), is(true));
		assertThat(result.contains(capture(e4, f5)), is(true));
	}

	@Test
	public void testE4MoveNotPossible() {
		bishop(e4);
		whitePawn(d5);
		whitePawn(f3);
		whitePawn(d3);
		whitePawn(f5);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	private Move capture(Field from, Field to) {
		return new Move(from, to, capture, board.pieces[to.position()]);
	}
	
	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}
	
	private Piece whitePawn(Field f) {
		return piece(f, WhitePawn);
	}
	
	private Piece piece(Field f, PieceType t) {
		Piece p = new Piece(t, f);
		board.place(p);
		return p;
	}
	
	private void bishop(Field f) {
		bishop.field(f);
		board.place(bishop);
	}
	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
