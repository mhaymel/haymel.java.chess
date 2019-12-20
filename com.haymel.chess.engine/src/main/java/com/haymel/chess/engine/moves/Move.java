/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import com.haymel.chess.engine.board.Field;

public class Move {
	
	private final Field from;
	private final Field to;
	
	public Move(Field from, Field to) {
		assert from != null;
		assert to != null;
		assert from != to;
	
		this.from = from;
		this.to = to;
	}
	
	public Field from() {
		return from;
	}
	
	public Field to() {
		return to;
	}
	
	@Override
	public String toString() {
		return from.toString() + to.toString();
	}
	
}
