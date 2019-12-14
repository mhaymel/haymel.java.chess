/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.chess.engine.Field.removed;

public final class WhiteRook implements Piece {

	private Field field; 
	
	public WhiteRook() {
		field = removed;
	}
	
	@Override
	public boolean white() {
		return true;
	}

	@Override
	public boolean black() {
		return false;
	}

	@Override
	public boolean free() {
		return false;
	}

	@Override
	public Field field() {
		return field;
	}

	@Override
	public void field(Field field) {
		assert field != null;
		this.field = field;
	}

}
