/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.chess.engine.Field.removed;

public final class BlackKnight implements Piece {	//TODO unit test

	private Field field; 
	
	public BlackKnight() {
		field = removed;
	}
	
	@Override
	public boolean white() {
		return false;
	}

	@Override
	public boolean black() {
		return true;
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
