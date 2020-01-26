/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	23.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

public class CurrentMove {		//TODO unit test

	private final Move move;
	private final int current;
	private final int count;
	
	public CurrentMove(Move move, int current, int count) {
		this.move = nonNull(move, "move");
		this.current = greaterThanZero(current, "current");
		this.count = greaterThanZero(count, "count");
		
		if (current > count)
			throwIAE("%s: %s must be less than or equal %s", current, count);
	}
	
	public Move move() {
		return move;
	}
	
	public int current() {
		return current;
	}

	public int count() {
		return count;
	}

}
