/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h6;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
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

public class BlackRookMovesTest {

	private Moves moves;
	private Board board;
	private BlackRookMoves rookMoves;
	private Piece rook = new Piece(BlackRook, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = new Board();
		rookMoves = new BlackRookMoves(board, moves);
	}
	
	@Test
	public void testA1() {
		rook(a1);
		rookMoves.generate(rook);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, a2)), is(true));
		assertThat(result.contains(new Move(a1, a3)), is(true));
		assertThat(result.contains(new Move(a1, a4)), is(true));
		assertThat(result.contains(new Move(a1, a5)), is(true));
		assertThat(result.contains(new Move(a1, a6)), is(true));
		assertThat(result.contains(new Move(a1, a7)), is(true));
		assertThat(result.contains(new Move(a1, a8)), is(true));
		assertThat(result.contains(new Move(a1, b1)), is(true));
		assertThat(result.contains(new Move(a1, c1)), is(true));
		assertThat(result.contains(new Move(a1, d1)), is(true));
		assertThat(result.contains(new Move(a1, e1)), is(true));
		assertThat(result.contains(new Move(a1, f1)), is(true));
		assertThat(result.contains(new Move(a1, g1)), is(true));
		assertThat(result.contains(new Move(a1, h1)), is(true));
	}

	@Test
	public void testH8() {
		rook(h8);
		rookMoves.generate(rook);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(h8, g8)), is(true));
		assertThat(result.contains(new Move(h8, f8)), is(true));
		assertThat(result.contains(new Move(h8, g8)), is(true));
		assertThat(result.contains(new Move(h8, e8)), is(true));
		assertThat(result.contains(new Move(h8, d8)), is(true));
		assertThat(result.contains(new Move(h8, c8)), is(true));
		assertThat(result.contains(new Move(h8, b8)), is(true));
		assertThat(result.contains(new Move(h8, a8)), is(true));
		assertThat(result.contains(new Move(h8, h7)), is(true));
		assertThat(result.contains(new Move(h8, h6)), is(true));
		assertThat(result.contains(new Move(h8, h5)), is(true));
		assertThat(result.contains(new Move(h8, h4)), is(true));
		assertThat(result.contains(new Move(h8, h3)), is(true));
		assertThat(result.contains(new Move(h8, h2)), is(true));
		assertThat(result.contains(new Move(h8, h1)), is(true));
	}

	@Test
	public void testE4() {
		rook(e4);
		rookMoves.generate(rook);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, e3)), is(true));
		assertThat(result.contains(new Move(e4, e2)), is(true));
		assertThat(result.contains(new Move(e4, e1)), is(true));
		assertThat(result.contains(new Move(e4, e5)), is(true));
		assertThat(result.contains(new Move(e4, e6)), is(true));
		assertThat(result.contains(new Move(e4, e7)), is(true));
		assertThat(result.contains(new Move(e4, e8)), is(true));
		assertThat(result.contains(new Move(e4, d4)), is(true));
		assertThat(result.contains(new Move(e4, c4)), is(true));
		assertThat(result.contains(new Move(e4, b4)), is(true));
		assertThat(result.contains(new Move(e4, a4)), is(true));
		assertThat(result.contains(new Move(e4, f4)), is(true));
		assertThat(result.contains(new Move(e4, g4)), is(true));
		assertThat(result.contains(new Move(e4, h4)), is(true));
	}
	
	@Test
	public void testE4Capture() {
		rook(e4);
		whitePawn(e5);
		whitePawn(e3);
		whitePawn(d4);
		whitePawn(f4);
		rookMoves.generate(rook);
		
		assertThat(moves.size(), is(4));
		Set<Move> result = movesAsSet();
		assertThat(result.contains(capture(e4, e5)), is(true));
		assertThat(result.contains(capture(e4, e3)), is(true));
		assertThat(result.contains(capture(e4, d4)), is(true));
		assertThat(result.contains(capture(e4, f4)), is(true));
	}

	@Test
	public void testE4MoveNotPossible() {
		rook(e4);
		blackPawn(e5);
		blackPawn(e3);
		blackPawn(d4);
		blackPawn(f4);
		rookMoves.generate(rook);
		
		assertThat(moves.size(), is(0));
	}
	
	private Move capture(Field from, Field to) {
		return new Move(from, to, capture, board.piece(to));
	}
	
	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}
	
	private Piece whitePawn(Field f) {
		return piece(f, WhitePawn);
	}
	
	private Piece piece(Field f, PieceType t) {
		Piece p = new Piece(t, f);
		p.field(f);
		board.place(p);
		return p;
	}
	
	private void rook(Field f) {
		rook.field(f);
		board.place(rook);
	}
	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
