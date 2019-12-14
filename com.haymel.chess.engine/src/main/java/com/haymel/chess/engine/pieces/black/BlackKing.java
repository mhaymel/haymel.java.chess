/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.black;

import static com.haymel.chess.engine.board.Field.e8;
import static java.lang.String.format;

public final class BlackKing extends BlackPiece {

	private boolean moved;
	
	public BlackKing() {
		moved = true;
	}
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || field() == e8 : format("a black king which was not moved must be on field e8. The current value of field is %s", field()); 
		
		moved = value;
	}

}
