/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	19.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class Moves {
	
	private int kingCaptureCount;
	
	private final Move[] moves;
	private int index = 0;
	
	public Moves() {
		moves = new Move[500];
		kingCaptureCount = 0;
	}
	
	public Move[] moves() {
		Move[] dest = new Move[index];
		System.arraycopy(moves, 0, dest, 0, index);
		return dest;
	}
	
	public void add(Field from, Field to) {
		assert from != null;
		assert to != null;
		assert from != to;
		add(new Move(from, to));
	}

	public void addCapture(Field from, Field to, Piece piece) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert !piece.free();
		assert piece.black() || piece.white();

		add(new Move(from, to, capture, piece));
		
		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addPawnMove(Field from, Field to) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert to.rank() != 7;
		assert from.rank() != 0;
		
		add(new Move(from, to, pawn));
	}

	public void addPawnDoubleStep(Field from, Field to) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert from.rank() == 1 || from.rank() == 6;
		assert to.rank() == 3 || to.rank() == 4;
		assert from.file() == to.file();

		add(new Move(from, to, pawnDoubleStep));
	}

	public void addWhitePromotion(Field from) {
		assert from != null;
		assert from.rank() == 6;
		
		Field to = from.up();
		add(new Move(from, to, WhiteQueen));
		add(new Move(from, to, WhiteRook));
		add(new Move(from, to, WhiteBishop));
		add(new Move(from, to, WhiteKnight));
	}

	public void addWhiteCapturePromotion(Field from, Field to, Piece piece) {		//TODO unit test
		assert from != null;
		assert to != null;
		assert from != to;
		assert Math.abs(from.file() - to.file()) == 1;
		assert from.rank() == 6;
		assert to.rank() == 7;
		assert piece.black();
		
		add(new Move(from, to, piece, WhiteQueen));
		add(new Move(from, to, piece, WhiteRook));
		add(new Move(from, to, piece, WhiteBishop));
		add(new Move(from, to, piece, WhiteKnight));

		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addBlackPromotion(Field from) {
		assert from != null;
		assert from.rank() == 1;
		
		Field to = from.down();
		add(new Move(from, to, BlackQueen));
		add(new Move(from, to, BlackRook));
		add(new Move(from, to, BlackBishop));
		add(new Move(from, to, BlackKnight));
	}

	public void addBlackCapturePromotion(Field from, Field to, Piece piece) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert Math.abs(from.file() - to.file()) == 1;
		assert from.rank() == 1;
		assert to.rank() == 0;
		assert piece.white();
		
		add(new Move(from, to, piece, BlackQueen));
		add(new Move(from, to, piece, BlackRook));
		add(new Move(from, to, piece, BlackBishop));
		add(new Move(from, to, piece, BlackKnight));

		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addEnpassant(Field from, Field to, Piece captured) {
		assert from != null;
		assert to != null;
		assert captured != null;
		assert captured.blackPawn() || captured.whitePawn();
		assert from != to;
		assert Math.abs(from.file() - to.file()) == 1;
		assert 
			from.rank() == 4 && to.rank() == 5 ||
			from.rank() == 3 && to.rank() == 2;
		
		add(new Move(from, to, enpassant, captured));
	}
	
	public void addWhiteKingSideCastling() {
		add(new Move(e1, g1, kingsideCastling));
	}

	public void addWhiteQueenSideCastling() {
		add(new Move(e1, c1, queensideCastling));
	}
	
	public void addBlackKingSideCastling() {
		add(new Move(e8, g8, kingsideCastling));
	}

	public void addBlackQueenSideCastling() {
		add(new Move(e8, c8, queensideCastling));
	}
	
	private void add(Move move) {
		moves[index++] = move;
	}

	public int size() {
		return index;
	}
	
	@Override
	public String toString() {
		List<String> strings = movesStream().map(Move::toString).collect(toList());		
		return String.format("Moves(%s)", join(", ", strings));
	}
	
	private Stream<Move> movesStream() {
		return Arrays.stream(moves());
	}

	public Move move(int index) {
		return moves[index];
	}
	
	public int kingCaptureCount() {
		return kingCaptureCount;
	}
	
	public boolean kingCaptured() {
		return kingCaptureCount > 0;
	};
	
	public List<Move> findMoves(Field from, Field to) {
		nonNull(from, "from");
		nonNull(to, "to");
		assert from != to;
		
		Predicate<Move> match = move -> (move.from() == from) && (move.to() == to);
		return movesStream().filter(match).collect(toList());
	}

}
