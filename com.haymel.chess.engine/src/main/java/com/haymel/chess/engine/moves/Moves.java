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
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.moves.MoveType.normalRookMove;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.moves.MoveType.validMoveType;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
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
import com.haymel.chess.engine.piece.PieceType;

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

	public void addKingMove(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		
		add(new Move(from, to, normalKingMove));
	}
	
	public void addRook(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		
		add(new Move(from, to, normalRookMove));
	}

	
	public void addCapture(int from, int to, Piece piece) {
		addCapture(from, to, piece, capture);
	}

	public void addKingCapture(int from, int to, Piece piece) {
		addCapture(from, to, piece, captureKingMove);
	}
	
	public void addRookCapture(int from, int to, Piece piece) {
		addCapture(from, to, piece, captureRookMove);
	}

	private void addCapture(int from, int to, Piece piece, int type) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert piece != null;
		assert PieceType.black(piece.type()) || PieceType.white(piece.type());
		assert validMoveType(type);
		assert type == capture || type == captureKingMove || type == captureRookMove;
		
		add(new Move(from, to, type));
		
		if (piece.type() == BlackKing || piece.type() == WhiteKing)
			kingCaptureCount++;
	}
	
	public void addPawnMove(int from, int to) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert rank(to) != 7;
		assert rank(from) != 0;
		
		add(new Move(from, to, pawn));
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
		add(new Move(from, to, promotionQueen));
		add(new Move(from, to, promotionRook));
		add(new Move(from, to, promotionBishop));
		add(new Move(from, to, promotionKnight));
	}

	public void addWhiteCapturePromotion(int from, int to, Piece piece) {		//TODO unit test
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert Math.abs(file(from) - file(to)) == 1;
		assert rank(from) == 6;
		assert rank(to) == 7;
		assert PieceType.black(piece.type());
		
		add(new Move(from, to, capturePromotionQueen));
		add(new Move(from, to, capturePromotionRook));
		add(new Move(from, to, capturePromotionBishop));
		add(new Move(from, to, capturePromotionKnight));

		if (piece.type() == PieceType.BlackKing || piece.type() == WhiteKing)
			kingCaptureCount++;
	}

	public void addBlackPromotion(int from) {
		assert valid(from);
		assert rank(from) == 1;
		
		int to = Field.down(from);
		add(new Move(from, to, promotionQueen));
		add(new Move(from, to, promotionRook));
		add(new Move(from, to, promotionBishop));
		add(new Move(from, to, promotionKnight));
	}

	public void addBlackCapturePromotion(int from, int to, Piece piece) {
		assert valid(from);
		assert valid(to);
		assert abs(file(from) - file(to)) == 1;
		assert rank(from) == 1;
		assert rank(to) == 0;
		assert PieceType.white(piece.type());
		
		add(new Move(from, to, capturePromotionQueen));
		add(new Move(from, to, capturePromotionRook));
		add(new Move(from, to, capturePromotionBishop));
		add(new Move(from, to, capturePromotionKnight));

		if (piece.type() == PieceType.BlackKing || piece.type() == WhiteKing)
			kingCaptureCount++;
	}

	public void addEnpassant(int from, int to, Piece captured) {
		assert valid(from);
		assert valid(to);
		assert captured != null;
		assert captured.type() == BlackPawn || captured.type() == WhitePawn;
		assert from != to;
		assert Math.abs(file(from) - file(to)) == 1;
		assert 
			rank(from) == 4 && rank(to) == 5 ||
			rank(from) == 3 && rank(to) == 2;
		
		add(new Move(from, to, enpassant));
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
