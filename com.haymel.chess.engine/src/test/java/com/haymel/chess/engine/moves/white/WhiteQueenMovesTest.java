/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	31.12.2019
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
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
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
import com.haymel.chess.engine.piece.PieceType;

public class WhiteQueenMovesTest {

	private Moves moves;
	private Piece[] board;
	private WhiteQueenMoves queenMoves;
	private Piece queen = new Piece(WhiteQueen, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = Board.newBoard();
		queenMoves = new WhiteQueenMoves(board);
	}
	
	@Test
	public void testA1() {
		queen(a1);
		queenMoves.generate(queen, moves);
		
		assertThat(moves.size(), is(21));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, b2)), is(true));
		assertThat(result.contains(new Move(a1, c3)), is(true));
		assertThat(result.contains(new Move(a1, d4)), is(true));
		assertThat(result.contains(new Move(a1, e5)), is(true));
		assertThat(result.contains(new Move(a1, f6)), is(true));
		assertThat(result.contains(new Move(a1, g7)), is(true));
		assertThat(result.contains(new Move(a1, h8)), is(true));
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
	public void testE4() {
		queen(e4);
		queenMoves.generate(queen, moves);
		
		assertThat(moves.size(), is(27));
		
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
		queen(e4);
		blackPawn(d5);
		blackPawn(f3);
		blackPawn(d3);
		blackPawn(f5);
		blackPawn(e5);
		blackPawn(e3);
		blackPawn(d4);
		blackPawn(f4);
		queenMoves.generate(queen, moves);
		
		assertThat(moves.size(), is(8));
		Set<Move> result = movesAsSet();
		assertThat(result.contains(capture(e4, d5)), is(true));
		assertThat(result.contains(capture(e4, f3)), is(true));
		assertThat(result.contains(capture(e4, d3)), is(true));
		assertThat(result.contains(capture(e4, f5)), is(true));
		assertThat(result.contains(capture(e4, e5)), is(true));
		assertThat(result.contains(capture(e4, e3)), is(true));
		assertThat(result.contains(capture(e4, d4)), is(true));
		assertThat(result.contains(capture(e4, f4)), is(true));
	}

	@Test
	public void testE4MoveNotPossible() {
		queen(e4);
		whitePawn(d5);
		whitePawn(f3);
		whitePawn(d3);
		whitePawn(f5);
		whitePawn(e5);
		whitePawn(e3);
		whitePawn(d4);
		whitePawn(f4);
		queenMoves.generate(queen, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	private Move capture(int from, int to) {
		return new Move(from, to, capture, board[to]);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece piece(int field, PieceType t) {
		Piece p = new Piece(t, field);
		board[p.field()] = p;
		return p;
	}
	
	private void queen(int field) {
		queen.field(field);
		board[queen.field()] = queen;
	}
	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}

}
