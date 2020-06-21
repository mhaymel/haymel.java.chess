/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	23.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.moves.Move;

public class AnalyzedMove {		//TODO unit test

	private final int move;
	private final int moveNumber;
	private final int numberOfPossibleMoves;
	
	public AnalyzedMove(int move, int moveNumber, int numberOfPossibleMoves) {
		Move.validMove(move);
		this.move = move;
		this.moveNumber = greaterThanZero(moveNumber, "moveNumber");
		this.numberOfPossibleMoves = greaterThanZero(numberOfPossibleMoves, "numberOfPossibleMoves");
		
		if (moveNumber > numberOfPossibleMoves)
			throwIAE("%s: %s must be less than or equal %s", move, moveNumber, numberOfPossibleMoves);
	}
	
	public int move() {
		return move;
	}
	
	public int moveNumber() {
		return moveNumber;
	}

	public int numberOfPossibleMoves() {
		return numberOfPossibleMoves;
	}

}
