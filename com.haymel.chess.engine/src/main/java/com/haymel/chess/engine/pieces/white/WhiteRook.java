/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;
import static java.lang.String.format;

public final class WhiteRook extends WhitePiece {

	private boolean moved;
	
	public WhiteRook() {
		moved = true;
	}
	
	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || field() == a1 || field() == h1 : format("a white rook which was not moved must be on field a1 or h1. The current value of field is %s", field()); 
		
		moved = value;
	}

}
