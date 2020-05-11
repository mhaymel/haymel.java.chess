/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b3;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
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

public class WhiteKnightMovesTest {

	private Moves moves;
	private Piece[] board;
	private WhiteKnightMoves knightMoves;
	private Piece knight = new Piece(WhiteKnight, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = newBoard();
		knightMoves = new WhiteKnightMoves(board);
	}
	
	@Test
	public void testA1() {
		knight(a1);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, b3)), is(true));
		assertThat(result.contains(new Move(a1, c2)), is(true));
	}

	@Test
	public void testH1() {
		knight(h1);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(h1, g3)), is(true));
		assertThat(result.contains(new Move(h1, f2)), is(true));
	}

	@Test
	public void testA8() {
		knight(a8);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a8, b6)), is(true));
		assertThat(result.contains(new Move(a8, c7)), is(true));
	}
	
	@Test
	public void testH8() {
		knight(h8);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(h8, f7)), is(true));
		assertThat(result.contains(new Move(h8, g6)), is(true));
	}
	
	@Test
	public void testE4() {
		knight(e4);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(8));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, d2)), is(true));
		assertThat(result.contains(new Move(e4, c3)), is(true));
		assertThat(result.contains(new Move(e4, c5)), is(true));
		assertThat(result.contains(new Move(e4, d6)), is(true));
		assertThat(result.contains(new Move(e4, f6)), is(true));
		assertThat(result.contains(new Move(e4, g5)), is(true));
		assertThat(result.contains(new Move(e4, g3)), is(true));
		assertThat(result.contains(new Move(e4, f2)), is(true));
	}
	
	@Test
	public void testE4MoveNotPossible() {
		knight(e4);
		whitePawn(d2);
		whitePawn(c3);
		whitePawn(c5);
		whitePawn(d6);
		whitePawn(f6);
		whitePawn(g5);
		whitePawn(g3);
		whitePawn(f2);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE4Capture() {
		knight(e4);
		blackPawn(d2);
		blackPawn(c3);
		blackPawn(c5);
		blackPawn(d6);
		blackPawn(f6);
		blackPawn(g5);
		blackPawn(g3);
		blackPawn(f2);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(8));
		Set<Move> result = movesAsSet();
		assertThat(result.contains(capture(e4, d2)), is(true));
		assertThat(result.contains(capture(e4, c3)), is(true));
		assertThat(result.contains(capture(e4, c5)), is(true));
		assertThat(result.contains(capture(e4, d6)), is(true));
		assertThat(result.contains(capture(e4, f6)), is(true));
		assertThat(result.contains(capture(e4, g5)), is(true));
		assertThat(result.contains(capture(e4, g3)), is(true));
		assertThat(result.contains(capture(e4, f2)), is(true));
	}
	
	private Move capture(int from, int to) {
		return new Move(from, to, capture, board[to]);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whitePawn(int f) {
		return piece(f, WhitePawn);
	}
	
	private Piece piece(int field, int type) {
		Piece p = new Piece(type, removed);
		p.field(field);
		board[p.field()] = p;
		return p;
	}
	
	private void knight(int field) {
		knight.field(field);
		board[knight.field()] = knight;
	}
	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
