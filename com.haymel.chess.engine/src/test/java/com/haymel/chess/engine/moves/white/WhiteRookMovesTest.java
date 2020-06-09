/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

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
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.normalRookMove;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class WhiteRookMovesTest {

	private Moves moves;
	private WhiteRookMoves rookMoves;
	private Piece rook = new Piece(WhiteRook, removed);
	private Piece[] pieces;
	
	@Before
	public void setup() {
		moves = new Moves();
		pieces = Board.newBoard();
		rookMoves = new WhiteRookMoves(pieces);
	}
	
	@Test
	public void testA1() {
		rook(a1);
		rookMoves.generate(rook, moves);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, a2, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a3, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a5, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a6, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a7, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, a8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, b1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, c1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, d1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, e1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, f1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, g1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(a1, h1, normalRookMove)), is(true));
	}

	@Test
	public void testH8() {
		rook(h8);
		rookMoves.generate(rook, moves);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(h8, g8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, f8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, g8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, e8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, d8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, c8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, b8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, a8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h7, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h6, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h5, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h3, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h2, normalRookMove)), is(true));
		assertThat(result.contains(new Move(h8, h1, normalRookMove)), is(true));
	}

	@Test
	public void testE4() {
		rook(e4);
		rookMoves.generate(rook, moves);
		
		assertThat(moves.size(), is(14));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, e3, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e2, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e1, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e5, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e6, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e7, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, e8, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, d4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, c4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, b4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, a4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, f4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, g4, normalRookMove)), is(true));
		assertThat(result.contains(new Move(e4, h4, normalRookMove)), is(true));
	}
	
	@Test
	public void testE4Capture() {
		rook(e4);
		blackPawn(e5);
		blackPawn(e3);
		blackPawn(d4);
		blackPawn(f4);
		rookMoves.generate(rook, moves);
		
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
		whitePawn(e5);
		whitePawn(e3);
		whitePawn(d4);
		whitePawn(f4);
		rookMoves.generate(rook, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	private Move capture(int from, int to) {
		return new Move(from, to, captureRookMove);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece piece(int field, int type) {
		Piece p = new Piece(type, field);
		pieces[p.field()] = p;
		return p;
	}
	
	private void rook(int field) {
		rook.field(field);
		pieces[rook.field()] = rook;
	}
	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
