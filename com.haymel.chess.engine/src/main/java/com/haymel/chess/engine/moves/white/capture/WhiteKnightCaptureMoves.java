/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKnightCaptureMoves {

	private final Board board;
	
	public WhiteKnightCaptureMoves(Board board) {	//TODO unit test
		assert board != null;
		
		this.board = board;
	}
	
	public void generate(Piece knight, Moves moves) {
		assert knight != null;
		assert moves != null;
		assert knight.field() != removed;
		assert board.piece(knight.field()) == knight;

		Field from = knight.field();
		
		add(from, from.leftLeftUp(), moves);
		add(from, from.leftDownDown(), moves);
		add(from, from.rightRightUp(), moves);
		add(from, from.rightRightDown(), moves);
		add(from, from.rightDownDown(), moves);
		add(from, from.leftUpUp(), moves);
		add(from, from.rightUpUp(), moves);
		add(from, from.leftLeftDown(), moves);
	}

	private void add(Field from, Field to, Moves moves) {
		Piece piece = board.piece(to);
		
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
