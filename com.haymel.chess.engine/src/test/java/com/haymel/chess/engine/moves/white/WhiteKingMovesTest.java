/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
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

import com.haymel.chess.engine.castling.CastlingRight;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class WhiteKingMovesTest {

	private Moves moves;
	private WhiteKingMoves kingMoves;
	private Piece[] board;
	private Piece king = new Piece(WhiteKing, removed);
	private CastlingRight castling;
	
	@Before
	public void setup() {
		moves = new Moves();
		board = newBoard();
		kingMoves = new WhiteKingMoves(board);
		castling = new CastlingRight();
		castling.disable();
	}
	
	@Test
	public void testA1() {
		king(a1);
		kingMoves.generate(king, castling, moves);
		
		assertThat(moves.size(), is(3));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(a1, a2, normalKingMove)), is(true));
		assertThat(result.contains(new Move(a1, b2, normalKingMove)), is(true));
		assertThat(result.contains(new Move(a1, b1, normalKingMove)), is(true));
	}

	@Test
	public void testE4() {
		king(e4);
		kingMoves.generate(king, castling, moves);
		
		assertThat(moves.size(), is(8));
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(e4, e5, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, f5, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, f4, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, f3, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, e3, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, d3, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, d4, normalKingMove)), is(true));
		assertThat(result.contains(new Move(e4, d5, normalKingMove)), is(true));
	}

	@Test
	public void testE4Capture() {
		piece(e5, BlackPawn);
		piece(f5, BlackPawn);
		piece(f4, BlackPawn);
		piece(f3, BlackPawn);
		piece(e3, BlackPawn);
		piece(d3, BlackPawn);
		piece(d4, BlackPawn);
		piece(d5, BlackPawn);
		
		king(e4);
		kingMoves.generate(king, castling, moves);
		
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

	private Move capture(int from, int to) {
		return new Move(from, to, captureKingMove);
	}

	@Test
	public void testE4NoMove() {
		piece(e5, WhitePawn);
		piece(f5, WhitePawn);
		piece(f4, WhitePawn);
		piece(f3, WhitePawn);
		piece(e3, WhitePawn);
		piece(d3, WhitePawn);
		piece(d4, WhitePawn);
		piece(d5, WhitePawn);
		
		king(e4);
		kingMoves.generate(king, castling, moves);
		
		assertThat(moves.size(), is(0));
	}
	
	@Test
	public void testCastling1() {
		king(e1);
		rook(h1);
		
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void testCastling2() {
		king(e1);
		rook(a1);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling3() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.enableKingside();
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(7));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling4() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.disable();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void testCastling5() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.disableKingside();
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void testCastling6() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.enableKingside();
		castling.disableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void testCastling7() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.disable();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void testCastling8() {
		king(e1);
		rook(h1);
		rook(a1);
		
		castling.disable();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void testCastling9() {
		king(e1);
		rook(h1);
		
		piece(f1, WhiteBishop);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossibleIfF1IsAttacked() {
		king(e1);
		rook(h1);
		rook(a1);
		blackQueen(f8);

		castling.enableKingside();
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(false));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsNotPossible1() {
		king(e1);
		rook(h1);
		blackQueen(a1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible2() {
		king(e1);
		rook(h1);
		blackRook(a1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsPossible1() {
		king(e1);
		rook(h1);
		blackBishop(a1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible2() {
		king(e1);
		rook(h1);
		blackKnight(a1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible3() {
		king(e1);
		rook(h1);
		blackRook(a1);
		blackKnight(b1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsPossible4() {
		king(e1);
		rook(h1);
		blackPawn(c2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsNotPossible3() {
		king(e1);
		rook(h1);
		blackPawn(d2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossibleBecauseOfBlackKingOnG2() {
		blackKing(g2);
		king(e1);
		rook(h1);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsPossible5() {
		king(e1);
		rook(h1);
		whitePawn(c2);
		whitePawn(d2);
		whitePawn(e2);
		whitePawn(f2);
		whitePawn(g2);
		whitePawn(h2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible4() {
		king(e1);
		rook(h1);
		blackPawn(f2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible5() {
		king(e1);
		rook(h1);
		blackPawn(g2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible() {
		king(e1);
		rook(h1);
		blackPawn(h2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible6() {
		king(e1);
		rook(h1);
		blackKnight(c2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible7() {
		king(e1);
		rook(h1);
		blackKnight(d2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible8() {
		king(e1);
		rook(h1);
		blackKnight(d3);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible9() {
		king(e1);
		rook(h1);
		blackKnight(e3);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible10() {
		king(e1);
		rook(h1);
		blackKnight(f3);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
		
	@Test
	public void kingSideCastlingIsNotPossible11() {
		king(e1);
		rook(h1);
		blackKnight(g3);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible12() {
		king(e1);
		rook(h1);
		blackKnight(g2);

		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible13() {
		king(e1);
		rook(h1);
		blackKnight(h2);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsPossible6() {
		king(e1);
		rook(h1);
		whiteKnight(c2);
		whiteKnight(d2);
		whiteKnight(d3);
		whiteKnight(e3);
		whiteKnight(f3);
		whiteKnight(g3);
		whiteKnight(g2);
		whiteKnight(h2);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	@Test
	public void kingSideCastlingIsPossible7() {
		king(e1);
		rook(h1);
		whiteBishop(a5);
		whiteBishop(a6);
		whiteBishop(h2);
		whiteBishop(h3);
		whiteBishop(h4);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible8() {
		king(e1);
		rook(h1);
		whiteQueen(a6);
		whiteQueen(a5);
		whiteQueen(e8);
		whiteQueen(h2);
		whiteQueen(h3);
		whiteQueen(h4);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible2() {
		king(e1);
		rook(h1);
		blackKnight(e2);
		blackKnight(h3);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible14() {
		king(e1);
		rook(h1);
		blackQueen(a5);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible15() {
		king(e1);
		rook(h1);
		blackQueen(e8);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible16() {
		king(e1);
		rook(h1);
		blackQueen(h4);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible17() {
		king(e1);
		rook(h1);
		blackQueen(h3);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsPossible9() {
		king(e1);
		rook(h1);
		whiteQueen(a5);
		whiteQueen(e8);
		whiteQueen(h4);
		whiteQueen(h3);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}

	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after Castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible3() {
		king(e1);
		rook(h1);
		blackQueen(h2);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible18() {
		king(e1);
		rook(h1);
		blackBishop(a5);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}

	@Test
	public void kingSideCastlingIsNotPossible19() {
		king(e1);
		rook(h1);
		blackBishop(h4);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	@Test
	public void kingSideCastlingIsNotPossible20() {
		king(e1);
		rook(h1);
		blackBishop(h3);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastlingIsNotPossibleButReportedToBePossible4() {
		king(e1);
		rook(h1);
		blackBishop(h2);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void kingSideCastlingIsPossible10() {
		king(e1);
		rook(h1);
		whiteBishop(a5);
		whiteBishop(h4);
		whiteBishop(h3);
		castling.enableKingside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked1() {
		king(e1);
		rook(a1);
		blackQueen(h1);
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked2() {
		king(e1);
		rook(a1);
		blackRook(h1);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsPossible1() {
		king(e1);
		rook(a1);
		blackBishop(h1);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked3() {
		king(e1);
		rook(a1);
		blackPawn(d2);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked4() {
		king(e1);
		rook(a1);
		blackPawn(f2);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked5() {
		king(e1);
		rook(a1);
		blackKnight(c2);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked6() {
		king(e1);
		rook(a1);
		blackKnight(d3);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked7() {
		king(e1);
		rook(a1);
		blackKnight(f3);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked8() {
		king(e1);
		rook(a1);
		blackKnight(g2);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked9() {
		king(e1);
		rook(a1);
		blackQueen(a5);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked10() {
		king(e1);
		rook(a1);
		blackBishop(a5);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked11() {
		king(e1);
		rook(a1);
		blackQueen(e8);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked12() {
		king(e1);
		rook(a1);
		blackRook(e8);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked13() {
		king(e1);
		rook(a1);
		blackQueen(h4);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfE1IsAttacked14() {
		king(e1);
		rook(a1);
		blackBishop(h4);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(5));
		assertThat(result.contains(queenSideCastling()), is(false));
	}
	
	@Test
	public void queenSideCastlingIsPossible2() {
		king(e1);
		rook(a1);
		blackRook(a5);
		blackRook(h4);
		blackBishop(e8);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCastling()), is(true));
	}

	@Test
	public void queenSideCastlingIsPossible3() {
		king(e1);
		rook(a1);
		whiteQueen(e8);
		whiteQueen(h1);
		whitePawn(d2);
		whitePawn(f2);
		whiteKnight(c2);
		whiteKnight(d3);
		whiteKnight(f3);
		whiteKnight(g2);
		
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(queenSideCastling()), is(true));
	}
	
	@Test
	public void queenSideCastlingIsNotPossibleIfD1IsAttacked() {
		king(e1);
		rook(h1);
		rook(a1);
		blackQueen(d8);
		castling.enableKingside();
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCastling()), is(true));
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	@Test
	public void queenSideCastlingIsNotPossibleBecauseOfBlackKingOnC2() {
		king(e1);
		rook(a1);
		blackKing(c2);
		castling.enableQueenside();
		kingMoves.generate(king, castling, moves);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(queenSideCastling()), is(false));
	}

	private Piece blackKing(int field) {
		return piece(field, BlackKing);
	}

	private Piece blackQueen(int field) {
		return piece(field, BlackQueen);
	}
	
	private Piece blackRook(int field) {
		return piece(field, BlackRook);
	}

	private Piece blackBishop(int field) {
		return piece(field, BlackBishop);
	}
	
	private Piece blackKnight(int field) {
		return piece(field, BlackKnight);
	}
	
	private Piece blackPawn(int field) {
		return piece(field, BlackPawn);
	}
	
	private Piece whitePawn(int field) {
		return piece(field, WhitePawn);
	}
	
	private Piece whiteKnight(int field) {
		return piece(field, WhiteKnight);
	}

	private Piece whiteBishop(int field) {
		return piece(field, WhiteBishop);
	}
	
	private Piece whiteQueen(int field) {
		return piece(field, WhiteQueen);
	}

	private Piece piece(int field, int type) {
		Piece p = new Piece(type, field);
		board[p.field()] = p;
		return p;
	}
	
	private Move kingSideCastling() {
		return new Move(e1, g1, kingsideCastling);
	}

	private Move queenSideCastling() {
		return new Move(e1, c1, queensideCastling);
	}

	private Piece king(int field) {
		king.field(field);
		board[king.field()] = king;
		return king;
	}
	
	private Piece rook(int field) {
		Piece p = new Piece(WhiteRook, field);
		board[p.field()] = p;
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
