/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.moves.Casteling.whiteKingSide;
import static com.haymel.chess.engine.moves.Casteling.whiteQueenSide;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
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

public class WhiteKingMovesTest {

	private Moves moves;
	private Board board;
	private WhiteKingMoves kingMoves;
	private Piece king = new Piece(WhiteKing);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = new Board();
		kingMoves = new WhiteKingMoves(board, moves);
	}
	
	@Test
	public void testA1() {
		king(a1);
		kingMoves.generate(king);
		
		assertThat(moves.size(), is(3));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, a2)), is(true));
		assertThat(result.contains(new Move(a1, b2)), is(true));
		assertThat(result.contains(new Move(a1, b1)), is(true));
	}

	@Test
	public void testE4() {
		king(e4);
		kingMoves.generate(king);
		
		assertThat(moves.size(), is(8));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, e5)), is(true));
		assertThat(result.contains(new Move(e4, f5)), is(true));
		assertThat(result.contains(new Move(e4, f4)), is(true));
		assertThat(result.contains(new Move(e4, f3)), is(true));
		assertThat(result.contains(new Move(e4, e3)), is(true));
		assertThat(result.contains(new Move(e4, d3)), is(true));
		assertThat(result.contains(new Move(e4, d4)), is(true));
		assertThat(result.contains(new Move(e4, d5)), is(true));
	}

	@Test
	public void testE4Capture() {
		place(e5, BlackPawn);
		place(f5, BlackPawn);
		place(f4, BlackPawn);
		place(f3, BlackPawn);
		place(e3, BlackPawn);
		place(d3, BlackPawn);
		place(d4, BlackPawn);
		place(d5, BlackPawn);
		
		king(e4);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(8));
		assertThat(result.contains(capture(e4, e5)), is(true));
		assertThat(result.contains(capture(e4, f5)), is(true));
		assertThat(result.contains(capture(e4, f4)), is(true));
		assertThat(result.contains(capture(e4, f3)), is(true));
		assertThat(result.contains(capture(e4, e3)), is(true));
		assertThat(result.contains(capture(e4, d3)), is(true));
		assertThat(result.contains(capture(e4, d4)), is(true));
		assertThat(result.contains(capture(e4, d5)), is(true));
	}

	private Move capture(Field from, Field to) {
		return new Move(from, to, true);
	}

	@Test
	public void testE4NoMove() {
		place(e5, WhitePawn);
		place(f5, WhitePawn);
		place(f4, WhitePawn);
		place(f3, WhitePawn);
		place(e3, WhitePawn);
		place(d3, WhitePawn);
		place(d4, WhitePawn);
		place(d5, WhitePawn);
		
		king(e4);
		kingMoves.generate(king);
		
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void testE4Casteling1() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void testE4Casteling2() {
		king(e1).setMoved(false);
		rook(a1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCasteling()), is(true));
	}

	@Test
	public void testE4Casteling3() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		rook(a1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(7));
		assertThat(result.contains(kingSideCasteling()), is(true));
		assertThat(result.contains(queenSideCasteling()), is(true));
	}

	@Test
	public void testE4Casteling4() {
		king(e1).setMoved(true);
		rook(h1).setMoved(false);
		rook(a1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCasteling()), is(false));
		assertThat(result.contains(queenSideCasteling()), is(false));
	}
	
	@Test
	public void testE4Casteling5() {
		king(e1).setMoved(false);
		rook(h1).setMoved(true);
		rook(a1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(false));
		assertThat(result.contains(queenSideCasteling()), is(true));
	}

	@Test
	public void testE4Casteling6() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		rook(a1).setMoved(true);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(true));
		assertThat(result.contains(queenSideCasteling()), is(false));
	}

	@Test
	public void testE4Casteling7() {
		king(e1).setMoved(false);
		rook(h1).setMoved(true);
		rook(a1).setMoved(true);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCasteling()), is(false));
		assertThat(result.contains(queenSideCasteling()), is(false));
	}

	@Test
	public void testE4Casteling8() {
		king(e1).setMoved(true);
		rook(h1).setMoved(true);
		rook(a1).setMoved(true);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCasteling()), is(false));
		assertThat(result.contains(queenSideCasteling()), is(false));
	}
	
	@Test
	public void testE4Casteling9() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		
		place(f1, WhiteBishop);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	private Move kingSideCasteling() {
		return new Move(e1, g1, false, whiteKingSide);
	}

	private Move queenSideCasteling() {
		return new Move(e1, c1, false, whiteQueenSide);
	}


	private void place(Field f, PieceType t) {
		Piece piece = new Piece(t);
		piece.field(f);
		board.place(piece);
	}

	private Piece king(Field f) {
		king.field(f);
		board.place(king);
		king.setMoved(true);
		return king;
	}
	
	private Piece rook(Field f) {
		Piece p = new Piece(WhiteRook);
		p.field(f);
		board.place(p);
		return p;
	}

	
	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	

}
