/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.white;

import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public final class WhitePawn extends WhitePiece {

	public void field(Field field) {
		assert field != null;
		assert field.rank() != 0 && field.rank() != 7 : format("a white pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		super.field(field);
	}
	
}
