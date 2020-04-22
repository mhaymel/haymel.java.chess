/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.Free;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static java.lang.String.format;

import java.util.Objects;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class Move {
	
	private final MoveType type;
	private final int from;
	private final int to;
	private final Piece capturedPiece;
	private final int pieceType;

	public Move(int from, int to) {
		this(from, to, normal);
	}
	
	public Move(int from, int to, MoveType type) {
		this(from, to, type, null, Free);
	}	

	public Move(int from, int to, MoveType type, Piece capturedPiece) {
		this(from, to, type, capturedPiece, Free);
	}
	
	public Move(int from, int to, int pieceType) {
		this(from, to, promotion, null, pieceType);
	}

	public Move(int from, int to, Piece capturedPiece, int promotion) {
		this(from, to, capturePromotion, capturedPiece, promotion);
	}
	
	private Move(int from, int to, MoveType type, Piece capturedPiece, int promotion) {
		assert Field.valid(from);
		assert Field.valid(to);
		assert from != to;
		assert type != null;
		assert PieceType.pieceTypeValid(promotion);
		assert 
			promotion != BlackKing && 
			promotion != WhiteKing && 
			promotion != BlackPawn && 
			promotion != WhitePawn &&
			promotion != Border;
		
		assert capturedPiece == null || 
			type == enpassant ||
			type == capture ||
			type == capturePromotion;

		assert 
			promotion == Free || 
			rank(to) == 0 || 
			rank(to) == 7; 
				
		this.from = from;
		this.to = to;
		this.type = type;
		this.capturedPiece = capturedPiece;
		this.pieceType = promotion;
	}
	
	public int from() {
		return from;
	}
	
	public int to() {
		return to;
	}
	
	public MoveType type() {
		return type;
	}
	
	public int pieceType() {
		return pieceType;
	}
	
	@Override
	public String toString() {
		switch(type) {
		case normal: 
		case pawn:
		case pawnDoubleStep:	return format("%s-%s", fieldAsString(from), fieldAsString(to));
		
		case capture: 			return format("%sx%s", fieldAsString(from), fieldAsString(to));
		case enpassant:			return format("%sx%se.p.", fieldAsString(from), fieldAsString(to));
		case capturePromotion:	return format("%sx%s%s", fieldAsString(from), fieldAsString(to), letterForPieceType(pieceType));
		case kingsideCastling:	return "O-O";
		case queensideCastling:	return "O-O-O";
		case promotion:			return format("%s-%s%s", fieldAsString(from), fieldAsString(to), letterForPieceType(pieceType));
		default:
			assert false;
			throw new IllegalStateException(type.toString());
		}
	}
	
	@Override
	public int hashCode() {			//TODO unit test
		return Objects.hash(type, from, to, capturedPiece, pieceType);
	}
	
	@Override			
	public boolean equals(Object obj) {		//TODO unit test
		if (obj == this)
			return true;
		
		if (!(obj instanceof Move))
			return false;
		
		Move that = (Move)obj;
		
		return 
			from == that.from && 
			to == that.to && 
			type == that.type &&
			Objects.equals(capturedPiece, that.capturedPiece) &&
			pieceType == that.pieceType;
	}

	public Piece capturedPiece() {
		return capturedPiece;
	}
	
	private static String letterForPieceType(int type) {
		switch(type) {
		case BlackBishop:
		case WhiteBishop:	return "B";
		
		case BlackKnight:
		case WhiteKnight:	return "N";
		
		case BlackQueen:
		case WhiteQueen:	return "Q";
		
		case BlackRook:
		case WhiteRook:		return "R";

		case BlackPawn:
		case WhitePawn:
		case BlackKing:
		case WhiteKing:
		case Free:
		case Border:
		default:
			assert false;
			throw new IllegalStateException(String.valueOf(type));
		}
	}
	
	public boolean capture() {
		return MoveType.capture(type);
	}
	
}
