/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

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
import static com.haymel.chess.engine.moves.Move.newMove;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class BlackKnightMovesTest {

	private Moves moves;
	private Piece[] board;
	private BlackKnightMoves knightMoves;
	private Piece knight = new Piece(BlackKnight, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = Board.newBoard();
		knightMoves = new BlackKnightMoves(board);
	}
	
	@Test
	public void testA1() {
		knight(a1);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(a1, b3, normal)), is(true));
		assertThat(result.contains(newMove(a1, c2, normal)), is(true));
	}

	@Test
	public void testH1() {
		knight(h1);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(h1, g3, normal)), is(true));
		assertThat(result.contains(newMove(h1, f2, normal)), is(true));
	}

	@Test
	public void testA8() {
		knight(a8);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(a8, b6, normal)), is(true));
		assertThat(result.contains(newMove(a8, c7, normal)), is(true));
	}
	
	@Test
	public void testH8() {
		knight(h8);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(2));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(h8, f7, normal)), is(true));
		assertThat(result.contains(newMove(h8, g6, normal)), is(true));
	}
	
	@Test
	public void testE4() {
		knight(e4);
		knightMoves.generate(knight, moves);
		
		assertThat(moves.size(), is(8));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e4, d2, normal)), is(true));
		assertThat(result.contains(newMove(e4, c3, normal)), is(true));
		assertThat(result.contains(newMove(e4, c5, normal)), is(true));
		assertThat(result.contains(newMove(e4, d6, normal)), is(true));
		assertThat(result.contains(newMove(e4, f6, normal)), is(true));
		assertThat(result.contains(newMove(e4, g5, normal)), is(true));
		assertThat(result.contains(newMove(e4, g3, normal)), is(true));
		assertThat(result.contains(newMove(e4, f2, normal)), is(true));
	}
	
	@Test
	public void testE4MoveNotPossible() {
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
		
		assertThat(moves.size(), is(0));
	}

	@Test
	public void testE4Capture() {
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
		
		assertThat(moves.size(), is(8));
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(capture(e4, d2)), is(true));
		assertThat(result.contains(capture(e4, c3)), is(true));
		assertThat(result.contains(capture(e4, c5)), is(true));
		assertThat(result.contains(capture(e4, d6)), is(true));
		assertThat(result.contains(capture(e4, f6)), is(true));
		assertThat(result.contains(capture(e4, g5)), is(true));
		assertThat(result.contains(capture(e4, g3)), is(true));
		assertThat(result.contains(capture(e4, f2)), is(true));
	}
	
	private int capture(int from, int to) {
		return newMove(from, to, capture);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece piece(int field, int pieceType) {
		Piece p = new Piece(pieceType, field);
		board[p.field()] = p;
		return p;
	}
	
	private void knight(int field) {
		knight.field(field);
		board[knight.field()] = knight;
	}
	
	private Set<Integer> movesAsSet() {
		Set<Integer> result = new HashSet<>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
