/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.BoardElement;
import com.haymel.chess.engine.board.Field;

public abstract class Piece implements BoardElement {

	private Field field; 

	public Piece() {
		field = removed;
	}
	
	@Override
	public final boolean free() {
		return false;
	}
	
	public Field field() {
		return field;
	}
	
	public void field(Field field) {
		assert field != null;
		this.field = field;
	}

}
