/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.FieldFromString;

class FieldsFromMoveString {

	private final String move;
	
	public FieldsFromMoveString(String move) {
		nonNull(move, "move");
		
		if (move.length() != 4)
			throw new IllegalArgumentException(format("move must have a lenght of 4 but is '%s'", move));

		this.move = move;
	}
	
	public Field from() {
		String from = move.substring(0, 2);
		return new FieldFromString(from).value();
	}
	
	public Field to() {
		String to = move.substring(2);
		return new FieldFromString(to).value();
	}
	
}
