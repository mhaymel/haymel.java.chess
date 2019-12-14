/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.white;

import static com.haymel.chess.engine.board.Field.e1;
import static java.lang.String.format;

public final class WhiteKing extends WhitePiece {

	private boolean moved;
	
	public WhiteKing() {
		moved = true;
	}
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || field() == e1 : format("a white king which was not moved must be on field e1. The current value of field is %s", field()); 
		
		moved = value;
	}

}
