/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.util.Require.nonNull;

public class StringFromMove {		//TODO unit test

	private final Move move;
	
	public StringFromMove(Move move) {
		this.move = nonNull(move, "move");
	}
	
	public String value() {
		return fieldAsString(move.from()) + fieldAsString(move.to());
	}

}
