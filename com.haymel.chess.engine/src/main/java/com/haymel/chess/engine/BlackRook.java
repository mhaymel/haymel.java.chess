/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.chess.engine.Field.a8;
import static com.haymel.chess.engine.Field.h8;
import static com.haymel.chess.engine.Field.removed;
import static java.lang.String.format;

public final class BlackRook implements Piece {	//TODO unit test

	private Field field; 
	private boolean moved;
	
	public BlackRook() {
		field = removed;
		moved = true;
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
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert !value && (field == a8 || field == h8) : format("a black rook which was not moved must be on field a8 or h8. The current value of field is %s", field); 
		
		moved = value;
	}

}
