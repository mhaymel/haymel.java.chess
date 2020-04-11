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

public class AnalyzedMove {		//TODO unit test

	private final Move move;
	private final int moveNumber;
	private final int numberOfPossibleMoves;
	
	public AnalyzedMove(Move move, int moveNumber, int numberOfPossibleMoves) {
		this.move = nonNull(move, "move");
		this.moveNumber = greaterThanZero(moveNumber, "moveNumber");
		this.numberOfPossibleMoves = greaterThanZero(numberOfPossibleMoves, "numberOfPossibleMoves");
		
		if (moveNumber > numberOfPossibleMoves)
			throwIAE("%s: %s must be less than or equal %s", move, moveNumber, numberOfPossibleMoves);
	}
	
	public Move move() {
		return move;
	}
	
	public int moveNumber() {
		return moveNumber;
	}

	public int numberOfPossibleMoves() {
		return numberOfPossibleMoves;
	}

}
