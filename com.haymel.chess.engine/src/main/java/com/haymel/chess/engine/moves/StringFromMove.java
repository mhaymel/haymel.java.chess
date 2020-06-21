/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.fieldAsString;

public class StringFromMove {		//TODO unit test

	private final int move;
	
	public StringFromMove(int move) {
		assert Move.validMove(move);
		this.move = move;
	}
	
	public String value() {
		return fieldAsString(Move.from(move)) + fieldAsString(Move.to(move));
	}

}
