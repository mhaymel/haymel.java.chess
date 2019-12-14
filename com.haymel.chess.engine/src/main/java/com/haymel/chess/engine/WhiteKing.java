/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.chess.engine.Field.e1;
import static com.haymel.chess.engine.Field.removed;
import static java.lang.String.format;

public final class WhiteKing implements Piece {	//TODO unit test

	private Field field; 
	private boolean moved;
	
	public WhiteKing() {
		field = removed;
		moved = true;
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
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert !value && (field == e1) : format("a white king which was not moved must be on field h1. The current value of field is %s", field); 
		
		moved = value;
	}

}
