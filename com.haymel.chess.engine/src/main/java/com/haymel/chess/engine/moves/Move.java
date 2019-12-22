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
import static com.haymel.chess.engine.moves.Casteling.blackKingSide;
import static com.haymel.chess.engine.moves.Casteling.blackQueenSide;
import static com.haymel.chess.engine.moves.Casteling.noCasteling;
import static com.haymel.chess.engine.moves.Casteling.whiteKingSide;
import static com.haymel.chess.engine.moves.Casteling.whiteQueenSide;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

class Move {
	
	private final Field from;
	private final Field to;
	private final boolean capture;
	private final Casteling casteling;

	public Move(Field from, Field to, boolean capture) {
		this(from, to, capture, noCasteling);
	}
	
	public Move(Field from, Field to, boolean capture, Casteling casteling) {
		assert from != null;
		assert to != null;
		assert from != to;
		assert casteling != null;
		
		assert casteling == noCasteling || 
								(!capture && 
									((casteling == whiteKingSide && from == e1 && to == g1) ||
									 (casteling == whiteQueenSide && from == e1 && to == c1) ||
									 (casteling == blackKingSide && from == e8 && to == g8) ||
									 (casteling == blackQueenSide && from == e8 && to == c8)));				
		this.from = from;
		this.to = to;
		this.capture = capture;
		this.casteling = casteling;
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
		
		switch(casteling) {
		case noCasteling: 		return format("%s%s%s", from, op, to);
		case blackKingSide: 	return "O-O";
		case blackQueenSide:	return "O-O-O";
		case whiteKingSide: 	return "O-O";
		case whiteQueenSide:	return "O-O-O";
		default:
			assert false;
			throw new IllegalStateException(casteling.toString());
		}
	}
	
}
