/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class WhiteKnightMoves {	//TODO unit test

	private final Board board;
	private final Moves moves;
	
	public WhiteKnightMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece knight) {
		assert knight != null;
		assert knight.field() != removed;
		assert board.piece(knight.field()) == knight;

		Field from = knight.field();
		
		add(from, from.leftLeftUp());
		add(from, from.leftDownDown());
		add(from, from.rightRightUp());
		add(from, from.rightRightDown());
		add(from, from.rightDownDown());
		add(from, from.leftUpUp());
		add(from, from.rightUpUp());
		add(from, from.leftLeftDown());
	}

	private void add(Field from, Field to) {
		Piece piece = board.piece(to);
		
		if (piece.free()) {
			moves.add(from, to);
		}
		else if (piece.black()) {
			moves.addCapture(from, to, piece);
		}
	}
	
}
