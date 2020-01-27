/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

public class StringFromMove {		//TODO unit test

	private final Move move;
	
	public StringFromMove(Move move) {
		this.move = nonNull(move, "move");
	}
	
	public String value() {
		return move.from().toString() + move.to().toString();
	}

}
