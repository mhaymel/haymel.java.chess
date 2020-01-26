/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;
import com.haymel.util.Require;

public class BestMove {

	private final Move move;
	private final int value;
	
	public BestMove(Move move, int value) {
		this.move = Require.nonNull(move, "move");
		this.value = value;
	}
	
	public Move move() {
		return move;
	}
	
	public int value() {
		return value;
	}
	
}
