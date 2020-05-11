/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Move;

final class Undo {			//TODO unit test

	private final Move move;
	private ActiveColor activeColor;
	private int enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
	public Undo(Move move, ActiveColor activeColor, int enPassant, int halfMoveClock, int fullMoveNumber) {
		assert move != null;
		assert activeColor != null;
		assert Field.valid(enPassant);
		assert halfMoveClock >= 0;
		assert fullMoveNumber >= 1;
		
		this.move = move;
		this.activeColor = activeColor;
		this.enPassant = enPassant;
		this.halfMoveClock = halfMoveClock;
		this.fullMoveNumber = fullMoveNumber;
	}

	public Move move() {
		return move;
	}

	public int enPassant() {
		return enPassant;
	}

	public ActiveColor activeColor() {
		return activeColor;
	}

	public int halfMoveClock() {
		return halfMoveClock;
	}

	public int fullMoveNumber() {
		return fullMoveNumber;
	}

}
