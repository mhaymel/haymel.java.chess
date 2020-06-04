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
	
	public Undo(Move move, int enPassant) {
		assert move != null;
		assert Field.valid(enPassant);
		
		this.move = move;
		this.enPassant = enPassant;
	}

	public Move move() {
		return move;
	}

	public int enPassant() {
		return enPassant;
	}

}
