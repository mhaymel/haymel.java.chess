/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.moves.MoveType.normal;
import static java.lang.String.format;

import java.util.Objects;

import com.haymel.chess.engine.board.Field;

public class Move {
	
	private final MoveType type;
	private final Field from;
	private final Field to;

	public Move(Field from, Field to) {
		this(from, to, normal);
	}
	
	public Move(Field from, Field to, MoveType type) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert type != null;
		
		this.from = from;
		this.to = to;
		this.type = type;
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
	
	@Override
	public String toString() {
		switch(type) {
		case normal: 					return format("%s-%s", from, to);
		case capture: 					return format("%sx%s", from, to);
		case enpassant:					return format("%sx%s", from, to);
		case capturePromotionQueen:		return format("%sx%sQ", from, to);
		case capturePromotionRook:		return format("%sx%sR", from, to);
		case capturePromotionBishop:	return format("%sx%sB", from, to);
		case capturePromotionKnight:	return format("%sx%sK", from, to);
		case kingsideCastling:			return "O-O";
		case queensideCastling:			return "O-O-O";
		case promotionQueen:			return format("%s-%sQ", from, to);
		case promotionRook:				return format("%s-%sR", from, to);
		case promotionBishop:			return format("%s-%sB", from, to);	
		case promotionKnight:			return format("%s-%sK", from, to);
		default:
			assert false;
			throw new IllegalStateException(type.toString());
		}
	}
	
	@Override
	public int hashCode() {			//TODO unit test
		return Objects.hash(from, to, type);
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
			type == that.type;
	}
	
}
