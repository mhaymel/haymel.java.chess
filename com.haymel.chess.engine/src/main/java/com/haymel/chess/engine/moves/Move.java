/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
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
import static com.haymel.chess.engine.moves.Castling.blackKingSide;
import static com.haymel.chess.engine.moves.Castling.blackQueenSide;
import static com.haymel.chess.engine.moves.Castling.noCastling;
import static com.haymel.chess.engine.moves.Castling.whiteKingSide;
import static com.haymel.chess.engine.moves.Castling.whiteQueenSide;
import static java.lang.String.format;

import java.util.Objects;

import com.haymel.chess.engine.board.Field;

public class Move {
	
	private final Field from;
	private final Field to;
	private final boolean capture;
	private final Castling castling;

	public Move(Field from, Field to) {
		this(from, to, false, noCastling);
	}
	
	public Move(Field from, Field to, boolean capture) {
		this(from, to, capture, noCastling);
	}
	
	public Move(Field from, Field to, boolean capture, Castling castling) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert castling != null;
		
		assert castling == noCastling || 
								(!capture && 
									((castling == whiteKingSide && from == e1 && to == g1) ||
									 (castling == whiteQueenSide && from == e1 && to == c1) ||
									 (castling == blackKingSide && from == e8 && to == g8) ||
									 (castling == blackQueenSide && from == e8 && to == c8)));				
		this.from = from;
		this.to = to;
		this.capture = capture;
		this.castling = castling;
	}
	
	public Field from() {
		return from;
	}
	
	public Field to() {
		return to;
	}
	
	@Override
	public String toString() {
		String op = capture ? "x" : "-";
		
		switch(castling) {
		case noCastling: 		return format("%s%s%s", from, op, to);
		case blackKingSide: 	return "O-O";
		case blackQueenSide:	return "O-O-O";
		case whiteKingSide: 	return "O-O";
		case whiteQueenSide:	return "O-O-O";
		default:
			assert false;
			throw new IllegalStateException(castling.toString());
		}
	}
	
	@Override
	public int hashCode() {			//TODO unit test
		return Objects.hash(from, to, capture, castling);
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
			capture == that.capture && 
			castling == that.castling;
	}
	
}
