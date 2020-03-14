/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	11.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.result;

import static com.haymel.chess.engine.search.result.ResultType.Normal;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.moves.Move;

public class Normal implements Result { //TODO unit tests

	private final int value;
	private final Move[] moves;
	
	public Normal(int value, Move[] moves) {
		this.value = value;
		this.moves = verified(moves);
	}
	
	@Override
	public ResultType type() {
		return Normal;
	}
	
	@Override
	public int value() {
		return value;
	}
	
	public Move[] moves() {
		return moves;
	}
	
	private static Move[] verified(Move[] moves) {
		nonNull(moves, "moves");
		if (moves.length == 0)
			return throwIAE("move must not be empty");
		
		return moves;
	}

}
