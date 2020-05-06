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
import static com.haymel.chess.engine.board.Field.file;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normal;
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
import static java.lang.Math.abs;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
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
		moves = new Move[200];
		kingCaptureCount = 0;
	}
	
	public Move[] moves() {
		return moves;
	}
	
	public void add(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		
		add(new Move(from, to));
	}

	public void addCapture(int from, int to, Piece piece) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert piece != null;
		assert piece.black() || piece.white();

		add(new Move(from, to, capture, piece));
		
		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addPawnMove(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert rank(to) != 7;
		assert rank(from) != 0;
		
		add(new Move(from, to, normal));
	}

	public void addPawnDoubleStep(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert rank(from) == 1 || rank(from) == 6;
		assert rank(to) == 3 || rank(to) == 4;
		assert file(from) == file(to);

		add(new Move(from, to, pawnDoubleStep));
	}

	public void addWhitePromotion(int from) {
		assert valid(from);
		assert rank(from) == 6;
		
		int to = up(from);
		add(new Move(from, to, WhiteQueen));
		add(new Move(from, to, WhiteRook));
		add(new Move(from, to, WhiteBishop));
		add(new Move(from, to, WhiteKnight));
	}

	public void addWhiteCapturePromotion(int from, int to, Piece piece) {		//TODO unit test
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert Math.abs(file(from) - file(to)) == 1;
		assert rank(from) == 6;
		assert rank(to) == 7;
		assert piece.black();
		
		add(new Move(from, to, piece, WhiteQueen));
		add(new Move(from, to, piece, WhiteRook));
		add(new Move(from, to, piece, WhiteBishop));
		add(new Move(from, to, piece, WhiteKnight));

		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addBlackPromotion(int from) {
		assert valid(from);
		assert rank(from) == 1;
		
		int to = Field.down(from);
		add(new Move(from, to, BlackQueen));
		add(new Move(from, to, BlackRook));
		add(new Move(from, to, BlackBishop));
		add(new Move(from, to, BlackKnight));
	}

	public void addBlackCapturePromotion(int from, int to, Piece piece) {
		assert valid(from);
		assert valid(to);
		assert abs(file(from) - file(to)) == 1;
		assert rank(from) == 1;
		assert rank(to) == 0;
		assert piece.white();
		
		add(new Move(from, to, piece, BlackQueen));
		add(new Move(from, to, piece, BlackRook));
		add(new Move(from, to, piece, BlackBishop));
		add(new Move(from, to, piece, BlackKnight));

		if (piece.blackKing() || piece.whiteKing())
			kingCaptureCount++;
	}

	public void addEnpassant(int from, int to, Piece captured) {
		assert valid(from);
		assert valid(to);
		assert captured != null;
		assert captured.blackPawn() || captured.whitePawn();
		assert from != to;
		assert Math.abs(file(from) - file(to)) == 1;
		assert 
			rank(from) == 4 && rank(to) == 5 ||
			rank(from) == 3 && rank(to) == 2;
		
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
		ArrayList<Move> list = new ArrayList<Move>(index);
		for(int i = 0; i < index; i++)
			list.add(moves[i]);
		return list.stream();
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
	
	public List<Move> findMoves(int from, int to) {
		nonNull(from, "from");
		nonNull(to, "to");
		assert from != to;
		
		Predicate<Move> match = move -> (move.from() == from) && (move.to() == to);
		return movesStream().filter(match).collect(toList());
	}

}
