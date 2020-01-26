/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.Piece.free;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.Free;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static java.lang.String.format;

import java.util.Objects;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class Move {
	
	private final MoveType type;
	private final Field from;
	private final Field to;
	private final Piece capturedPiece;
	private final PieceType pieceType;

	public Move(Field from, Field to) {
		this(from, to, normal);
	}
	
	public Move(Field from, Field to, MoveType type) {
		this(from, to, type, free, Free);
	}	

	public Move(Field from, Field to, MoveType type, Piece capturedPiece) {
		this(from, to, type, capturedPiece, Free);
	}
	
	public Move(Field from, Field to, PieceType pieceType) {
		this(from, to, promotion, free, pieceType);
	}

	public Move(Field from, Field to, Piece capturedPiece, PieceType promotion) {
		this(from, to, capturePromotion, capturedPiece, promotion);
	}
	
	private Move(Field from, Field to, MoveType type, Piece capturedPiece, PieceType promotion) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert type != null;
		assert capturedPiece != null;
		assert promotion != null;
		assert 
			promotion != BlackKing && 
			promotion != WhiteKing && 
			promotion != BlackPawn && 
			promotion != WhitePawn &&
			promotion != Border;
		
		assert capturedPiece.free() || 
			type == enpassant ||
			type == capture ||
			type == capturePromotion;

		assert 
			promotion == Free || 
			to.rank() == 0 || 
			to.rank() == 7; 
				
		this.from = from;
		this.to = to;
		this.type = type;
		this.capturedPiece = capturedPiece;
		this.pieceType = promotion;
	}
	
	public Field from() {
		return from;
	}
	
	public Field to() {
		return to;
	}
	
	public MoveType type() {
		return type;
	}
	
	public PieceType pieceType() {
		return pieceType;
	}
	
	@Override
	public String toString() {
		switch(type) {
		case normal: 
		case pawn:
		case pawnDoubleStep:	return format("%s-%s", from, to);
		
		case capture: 			return format("%sx%s", from, to);
		case enpassant:			return format("%sx%se.p.", from, to);
		case capturePromotion:	return format("%sx%s%s", from, to, letterForPieceType(pieceType));
		case kingsideCastling:	return "O-O";
		case queensideCastling:	return "O-O-O";
		case promotion:			return format("%s-%s%s", from, to, letterForPieceType(pieceType));
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
			from.equals(that.from) && 
			to.equals(that.to) && 
			type == that.type &&
			capturedPiece.equals(that.capturedPiece) &&
			pieceType == that.pieceType;
	}

	public Piece capturedPiece() {
		return capturedPiece;
	}
	
	private static String letterForPieceType(PieceType type) {
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
			throw new IllegalStateException(type.toString());
		}
	}
	
}
