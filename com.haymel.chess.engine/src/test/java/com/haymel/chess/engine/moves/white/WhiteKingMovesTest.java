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
import static com.haymel.chess.engine.moves.Castling.whiteKingSide;
import static com.haymel.chess.engine.moves.Castling.whiteQueenSide;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
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
	public void testCasteling1() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void testCasteling2() {
		king(e1).setMoved(false);
		rook(a1).setMoved(false);
		
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(queenSideCasteling()), is(true));
	}

	@Test
	public void testCasteling3() {
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
	public void testCasteling4() {
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
	public void testCasteling5() {
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
	public void testCasteling6() {
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
	public void testCasteling7() {
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
	public void testCasteling8() {
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
	public void testCasteling9() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		
		place(f1, WhiteBishop);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(4));
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossibleIfF1IsAttacked() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		rook(a1).setMoved(false);
		blackQueen(f8);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(false));
		assertThat(result.contains(queenSideCasteling()), is(true));
	}

	@Test
	public void kingSideCastelingIsNotPossible1() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(a1);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossible2() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackRook(a1);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsPossible1() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackBishop(a1);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsPossible2() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(a1);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsPossible3() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackRook(a1);
		blackKnight(b1);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}

	@Test
	public void kingSideCastelingIsPossible4() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackPawn(c2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}

	@Test
	public void kingSideCastelingIsNotPossible3() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackPawn(d2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsPossible5() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whitePawn(c2);
		whitePawn(d2);
		whitePawn(e2);
		whitePawn(f2);
		whitePawn(g2);
		whitePawn(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible4() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackPawn(f2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossible5() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackPawn(g2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	/**
	 * King side casteling is a pseudo legal move, since king is in
	 * check after casteling. This will be detected during search.
	 */
	@Test
	public void kingSideCastelingIsNotPossibleButReportedToBePossible() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackPawn(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible6() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(c2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible7() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(d2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible8() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(d3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible9() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(e3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible10() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(f3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
		
	@Test
	public void kingSideCastelingIsNotPossible11() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(g3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossible12() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(g2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible13() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsPossible6() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whiteKnight(c2);
		whiteKnight(d2);
		whiteKnight(d3);
		whiteKnight(e3);
		whiteKnight(f3);
		whiteKnight(g3);
		whiteKnight(g2);
		whiteKnight(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}

	@Test
	public void kingSideCastelingIsPossible7() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whiteBishop(a5);
		whiteBishop(a6);
		whiteBishop(h2);
		whiteBishop(h3);
		whiteBishop(h4);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsPossible8() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whiteQueen(a6);
		whiteQueen(a5);
		whiteQueen(e8);
		whiteQueen(h2);
		whiteQueen(h3);
		whiteQueen(h4);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	/**
	 * King side casteling is a pseudo legal move, since king is in
	 * check after casteling. This will be detected during search.
	 */
	@Test
	public void kingSideCastelingIsNotPossibleButReportedToBePossible2() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackKnight(e2);
		blackKnight(h3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible14() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(a5);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossible15() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(e8);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible16() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(h4);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible17() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(h3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsPossible9() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whiteQueen(a5);
		whiteQueen(e8);
		whiteQueen(h4);
		whiteQueen(h3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}

	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after casteling. This will be detected during search.
	 */
	@Test
	public void kingSideCastelingIsNotPossibleButReportedToBePossible3() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackQueen(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible18() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackBishop(a5);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}

	@Test
	public void kingSideCastelingIsNotPossible19() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackBishop(h4);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	@Test
	public void kingSideCastelingIsNotPossible20() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackBishop(h3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(false));
	}
	
	/**
	 * King side castling is a pseudo legal move, since king is in
	 * check after castling. This will be detected during search.
	 */
	@Test
	public void kingSideCastelingIsNotPossibleButReportedToBePossible4() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		blackBishop(h2);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	
	@Test
	public void kingSideCastelingIsPossible10() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		whiteBishop(a5);
		whiteBishop(h4);
		whiteBishop(h3);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(result.contains(kingSideCasteling()), is(true));
	}
	
	@Test
	public void queenSideCastelingIsNotPossibleIfD1IsAttacked() {
		king(e1).setMoved(false);
		rook(h1).setMoved(false);
		rook(a1).setMoved(false);
		blackQueen(d8);
		kingMoves.generate(king);
		
		Set<Move> result = movesAsSet();
		assertThat(moves.size(), is(6));
		assertThat(result.contains(kingSideCasteling()), is(true));
		assertThat(result.contains(queenSideCasteling()), is(false));
	}
	
	private Piece blackQueen(Field f) {
		return piece(f, BlackQueen);
	}
	
	private Piece blackRook(Field f) {
		return piece(f, BlackRook);
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
		Piece p = new Piece(t);
		p.field(f);
		board.place(p);
		return p;
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
