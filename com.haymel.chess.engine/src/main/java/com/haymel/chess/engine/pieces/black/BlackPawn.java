/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.black;

import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public final class BlackPawn extends BlackPiece {

	@Override
	public final void field(Field field) {
		assert field != null;
		assert field.rank() != 0 && field.rank() != 7 : format("a black pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		super.field(field);
	}
	
}
