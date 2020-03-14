/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	12.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.result;

import static com.haymel.chess.engine.search.result.ResultType.MateInMoves;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.moves.Move;

public class MateInMoves implements Result {	//TODO unit tests

	private final int value;
	private final Move[] moves;
	
	public MateInMoves(int value, Move[] moves) {
		this.value = value;
		this.moves = verified(moves);
	}
	
	@Override
	public ResultType type() {
		return MateInMoves;
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
