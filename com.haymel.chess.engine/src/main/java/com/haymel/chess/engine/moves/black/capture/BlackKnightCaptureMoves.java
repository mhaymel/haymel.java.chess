/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKnightCaptureMoves {	//TODO unit test

	private final Board board;
	
	public BlackKnightCaptureMoves(Board board) {
		assert board != null;
		
		this.board = board;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == BlackKnight;

		Field from = piece.field();
		
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
		
		if (piece.white())
			moves.addCapture(from, to, piece);
	}
	
}
