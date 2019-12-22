/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import com.haymel.chess.engine.board.Field;

class Move {
	
	private final Field from;
	private final Field to;
	private final boolean capture;
	
	public Move(Field from, Field to, boolean capture) {
		assert from != null;
		assert to != null;
		assert from != to;

		this.from = from;
		this.to = to;
		this.capture = capture;
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
		return String.format("%s%s%s", from, op, to);
	}
	
}
