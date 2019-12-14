/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.chess.engine.Field.removed;
import static java.lang.String.format;

public final class BlackPawn implements Piece {	//TODO unit test

	private Field field; 
	
	public BlackPawn() {
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
		assert field.rank() != 0 && field.rank() != 7 : format("a black pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		this.field = field;
	}
	
}
