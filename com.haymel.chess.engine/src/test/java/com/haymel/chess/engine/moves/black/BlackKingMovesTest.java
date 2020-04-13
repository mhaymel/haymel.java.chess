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
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e1;
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
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
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
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
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

public class BlackKingMovesTest {

	private Moves moves;
	private Board board;
	private BlackKingMoves kingMoves;
	private Piece king = new Piece(BlackKing, removed);
	
	@Before
	public void setup() {
		moves = new Moves();
		board = new Board();
		kingMoves = new BlackKingMoves(board);
	}
	
	@Test
	public void testA1() {
		king(a1);
		kingMoves.generate(king, moves);
		
		assertThat(moves.size(), is(3));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, a2)), is(true));
		assertThat(result.contains(new Move(a1, b2)), is(true));
		assertThat(result.contains(new Move(a1, b1)), is(true));
	}

	@Test
	public void testE4() {
		king(e4);
		kingMoves.generate(king, moves);
		
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
		place(e5, WhitePawn);
		place(f5, WhitePawn);
		place(f4, WhitePawn);
		place(f3, WhitePawn);
		place(e3, WhitePawn);
		place(d3, WhitePawn);
		place(d4, WhitePawn);
		place(d5, WhitePawn);
		
		king(e4);
		kingMoves.generate(king, moves);
		
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
		return new Move(from, to, capture, board.pieces[to.position()]);
	}

	@Test
	public void testE4NoMove() {
		place(e5, BlackPawn);
		place(f5, BlackPawn);
		place(f4, BlackPawn);
		place(f3, BlackPawn);
		place(e3, BlackPawn);
		place(d3, BlackPawn);
		place(d4, BlackPawn);
		place(d5, BlackPawn);
		
		king(e4);
		kingMoves.generate(king, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void testCastling1() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void testCastling2() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling3() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		rook(a8).setMoved(false);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(7));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling4() {
		king(e8).setMoved(true);
		rook(h8).setMoved(false);
		rook(a8).setMoved(false);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void testCastling5() {
		king(e8).setMoved(false);
		rook(h8).setMoved(true);
		rook(a8).setMoved(false);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling6() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		rook(a8).setMoved(true);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void testCastling7() {
		king(e8).setMoved(false);
		rook(h8).setMoved(true);
		rook(a8).setMoved(true);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void testCastling8() {
		king(e8).setMoved(true);
		rook(h8).setMoved(true);
		rook(a8).setMoved(true);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void testCastling9() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		
		place(f8, BlackBishop);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossibleIfF1IsAttacked() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(f1);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsNotPossible1() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(a8);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible2() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteRook(a8);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsPossible1() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteBishop(a8);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible2() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(a8);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible3() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteRook(a8);
		whiteKnight(b8);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsPossible4() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whitePawn(c7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsNotPossible3() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whitePawn(d7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossibleBecauseOfWhiteKingOnG7() {
		whiteKing(g7);
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsPossible5() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		blackPawn(c7);
		blackPawn(d7);
		blackPawn(e7);
		blackPawn(f7);
		blackPawn(g7);
		blackPawn(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible4() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whitePawn(f7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible5() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whitePawn(g7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whitePawn(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible6() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(c7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible7() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(d7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible8() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(d6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible9() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(e6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible10() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(f6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
		
	@Test
	public void kingSideCastlingIsNotPossible11() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(g6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible12() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(g7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible13() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsPossible6() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		blackKnight(c7);
		blackKnight(d7);
		blackKnight(d6);
		blackKnight(e6);
		blackKnight(f6);
		blackKnight(g6);
		blackKnight(g7);
		blackKnight(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsPossible7() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		blackBishop(a6);
		blackBishop(a5);
		blackBishop(a4);
		blackBishop(h5);
		blackBishop(h6);
		blackBishop(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible8() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		blackQueen(a2);
		blackQueen(a3);
		blackQueen(a4);
		blackQueen(a5);
		blackQueen(a6);
		blackQueen(a2);
		blackQueen(e1);
		blackQueen(h3);
		blackQueen(h4);
		blackQueen(h5);
		blackQueen(h6);
		blackQueen(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible2() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteKnight(e7);
		whiteKnight(h6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible14() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(a4);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible15() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(e1);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible16() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(h5);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible17() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(h6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible3() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteQueen(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible18() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteBishop(a4);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible19() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteBishop(h5);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible20() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteBishop(h6);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible4() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		whiteBishop(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible10() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		blackBishop(a1);
		blackBishop(a2);
		blackBishop(a3);
		blackBishop(a4);
		blackBishop(a5);
		blackBishop(a6);
		blackBishop(a7);
		blackBishop(h1);
		blackBishop(h2);
		blackBishop(h3);
		blackBishop(h4);
		blackBishop(h5);
		blackBishop(h6);
		blackBishop(h7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked1() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(h8);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked2() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteRook(h8);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsPossible1() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteBishop(h8);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked3() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whitePawn(d7);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked4() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whitePawn(f7);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked5() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteKnight(c7);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked6() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteKnight(d6);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked7() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteKnight(f6);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE18sAttacked8() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteKnight(g7);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked9() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(a4);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked10() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteBishop(a4);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked11() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(e1);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked12() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteRook(e1);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked13() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(h5);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE8IsAttacked14() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteBishop(h5);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsPossible2() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteRook(a4);
		whiteRook(h5);
		whiteBishop(e1);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void queenSideCastlingIsPossible3() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		blackQueen(e1);
		blackQueen(h8);
		blackPawn(d7);
		blackPawn(f7);
		blackKnight(c7);
		blackKnight(d6);
		blackKnight(f6);
		blackKnight(g7);
		
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(queenSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfD8IsAttacked() {
		king(e8).setMoved(false);
		rook(h8).setMoved(false);
		rook(a8).setMoved(false);
		whiteQueen(d1);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleBecauseOfWhiteKingOnC7() {
		king(e8).setMoved(false);
		rook(a8).setMoved(false);
		whiteKing(c7);
		kingMoves.generate(king, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	private Piece whiteKing(Field f) {
		return piece(f, WhiteKing);
	}
	
	private Piece whiteRook(Field f) {
		return piece(f, WhiteRook);
	}

	private Piece blackQueen(Field f) {
		return piece(f, BlackQueen);
	}
	
	private Piece blackBishop(Field f) {
		return piece(f, BlackBishop);
	}
	
	private Piece blackKnight(Field f) {
		return piece(f, BlackKnight);
	}
	
	private Piece blackPawn(Field f) {
		return piece(f, BlackPawn);
	}
	
	private Piece whitePawn(Field f) {
		return piece(f, WhitePawn);
	}
	
	private Piece whiteKnight(Field f) {
		return piece(f, WhiteKnight);
	}

	private Piece whiteBishop(Field f) {
		return piece(f, WhiteBishop);
	}
	
	private Piece whiteQueen(Field f) {
		return piece(f, WhiteQueen);
	}

	private Piece piece(Field f, PieceType t) {
		Piece p = new Piece(t, f);
		board.place(p);
		return p;
	}
	
	private Move kingSideCastling() {
		return new Move(e8, g8, kingsideCastling);
	}

	private Move queenSideCastling() {
		return new Move(e8, c8, queensideCastling);
	}

	private void place(Field f, PieceType t) {
		Piece piece = new Piece(t, f);
		board.place(piece);
	}

	private Piece king(Field f) {
		king.field(f);
		board.place(king);
		king.setMoved(true);
		return king;
	}
	
	private Piece rook(Field f) {
		Piece p = new Piece(BlackRook, f);
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
