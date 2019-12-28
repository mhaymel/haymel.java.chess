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
	private final boolean moved;
	private ActiveColor activeColor;
	private Field enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
	public Undo(Move move, boolean moved, ActiveColor activeColor, Field enPassant, int halfMoveClock, int fullMoveNumber) {
		assert move != null;
		assert activeColor != null;
		assert enPassant != null;
		assert halfMoveClock >= 0;
		assert fullMoveNumber >= 1;
		
		this.move = move;
		this.moved = moved;
		this.activeColor = activeColor;
		this.enPassant = enPassant;
		this.halfMoveClock = halfMoveClock;
		this.fullMoveNumber = fullMoveNumber;
	}

	public Move move() {
		return move;
	}

	public Field enPassant() {
		return enPassant;
	}

	public boolean moved() {
		return moved;
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
