/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.black;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.h8;
import static java.lang.String.format;

public final class BlackRook extends BlackPiece {

	private boolean moved;
	
	public BlackRook() {
		moved = true;
	}
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || field() == a8 || field() == h8 : format("a black rook which was not moved must be on field a8 or h8. The current value of field is %s", field()); 
		
		moved = value;
	}

}
