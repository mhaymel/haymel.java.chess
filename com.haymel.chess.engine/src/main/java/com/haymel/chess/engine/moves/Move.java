/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normal;
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
import static java.lang.String.format;

import java.util.Objects;

import com.haymel.chess.engine.board.Field;

public class Move {
	
	private final int type;
	private final int from;
	private final int to;

	public Move(int from, int to) {
		this(from, to, normal);
	}
	
	public Move(int from, int to, int type) {
		assert Field.valid(from);
		assert Field.valid(to);
		assert validMoveType(type);
		assert from != to;
		
		this.from = from;
		this.to = to;
		this.type = type;
	}
	
	public int from() {
		return from;
	}
	
	public int to() {
		return to;
	}
	
	public int type() {
		return type;
	}
	
	@Override
	public String toString() {
		switch(type) {
		case normal: 
		case pawn:
		case pawnDoubleStep:	
		case normalRookMove:
		case normalKingMove:			return format("%s-%s", fieldAsString(from), fieldAsString(to));
		
		case captureRookMove:
		case captureKingMove:
		case capture: 					return format("%sx%s", fieldAsString(from), fieldAsString(to));
		
		case enpassant:					return format("%sx%se.p.", fieldAsString(from), fieldAsString(to));
		case capturePromotionQueen:		return format("%sx%sQ", fieldAsString(from), fieldAsString(to));
		case capturePromotionRook:		return format("%sx%sR", fieldAsString(from), fieldAsString(to));
		case capturePromotionBishop:	return format("%sx%sB", fieldAsString(from), fieldAsString(to));
		case capturePromotionKnight:	return format("%sx%sN", fieldAsString(from), fieldAsString(to));
		case kingsideCastling:			return "O-O";
		case queensideCastling:			return "O-O-O";
		case promotionQueen:			return format("%s-%sQ", fieldAsString(from), fieldAsString(to));
		case promotionRook:				return format("%s-%sR", fieldAsString(from), fieldAsString(to));
		case promotionBishop:			return format("%s-%sB", fieldAsString(from), fieldAsString(to));
		case promotionKnight:			return format("%s-%sN", fieldAsString(from), fieldAsString(to));
		default:
			assert false;
			throw new IllegalStateException(String.valueOf(type));
		}
	}
	
	@Override
	public int hashCode() {			//TODO unit test
		return Objects.hash(type, from, to);
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
			type == that.type;
	}

	public boolean capture() {
		return MoveType.capture(type);
	}
	
}
