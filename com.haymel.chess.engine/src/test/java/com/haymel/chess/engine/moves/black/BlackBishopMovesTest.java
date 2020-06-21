/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Board.newBoard;
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
import static com.haymel.chess.engine.moves.Move.newMove;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class BlackBishopMovesTest {

	private Moves moves;
	private Piece[] board;
	private BlackBishopMoves bishopMoves;
	private Piece bishop = new Piece(BlackBishop, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = newBoard();
		bishopMoves = new BlackBishopMoves(board);
	}
	
	@Test
	public void testA1() {
		bishop(a1);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(7));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(a1, b2, normal)), is(true));
		assertThat(result.contains(newMove(a1, c3, normal)), is(true));
		assertThat(result.contains(newMove(a1, d4, normal)), is(true));
		assertThat(result.contains(newMove(a1, e5, normal)), is(true));
		assertThat(result.contains(newMove(a1, f6, normal)), is(true));
		assertThat(result.contains(newMove(a1, g7, normal)), is(true));
		assertThat(result.contains(newMove(a1, h8, normal)), is(true));
	}

	@Test
	public void testE4() {
		bishop(e4);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(13));
		
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(newMove(e4, d3, normal)), is(true));
		assertThat(result.contains(newMove(e4, c2, normal)), is(true));
		assertThat(result.contains(newMove(e4, b1, normal)), is(true));
		assertThat(result.contains(newMove(e4, f5, normal)), is(true));
		assertThat(result.contains(newMove(e4, g6, normal)), is(true));
		assertThat(result.contains(newMove(e4, h7, normal)), is(true));
		assertThat(result.contains(newMove(e4, d5, normal)), is(true));
		assertThat(result.contains(newMove(e4, c6, normal)), is(true));
		assertThat(result.contains(newMove(e4, b7, normal)), is(true));
		assertThat(result.contains(newMove(e4, a8, normal)), is(true));
		assertThat(result.contains(newMove(e4, f3, normal)), is(true));
		assertThat(result.contains(newMove(e4, g2, normal)), is(true));
		assertThat(result.contains(newMove(e4, h1, normal)), is(true));
	}

	@Test
	public void testE4Capture() {
		bishop(e4);
		whitePawn(d5);
		whitePawn(f3);
		whitePawn(d3);
		whitePawn(f5);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(4));
		Set<Integer> result = movesAsSet();
		assertThat(result.contains(capture(e4, d5)), is(true));
		assertThat(result.contains(capture(e4, f3)), is(true));
		assertThat(result.contains(capture(e4, d3)), is(true));
		assertThat(result.contains(capture(e4, f5)), is(true));
	}

	@Test
	public void testE4MoveNotPossible() {
		bishop(e4);
		blackPawn(d5);
		blackPawn(f3);
		blackPawn(d3);
		blackPawn(f5);
		bishopMoves.generate(bishop, moves);
		
		assertThat(moves.size(), is(0));
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
	
	private void bishop(int field) {
		bishop.field(field);
		board[bishop.field()] = bishop;
	}
	
	private Set<Integer> movesAsSet() {
		Set<Integer> result = new HashSet<>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
