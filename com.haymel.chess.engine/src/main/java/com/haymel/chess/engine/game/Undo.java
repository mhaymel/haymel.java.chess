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

public class Undo {			//TODO unit test

	private final Move move;
	private int enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
	public Undo(Move move, int enPassant, int halfMoveClock, int fullMoveNumber) {
		assert move != null;
		assert Field.valid(enPassant);
		assert halfMoveClock >= 0;
		assert fullMoveNumber >= 1;
		
		this.move = move;
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

	public int halfMoveClock() {
		return halfMoveClock;
	}

	public int fullMoveNumber() {
		return fullMoveNumber;
	}

}
