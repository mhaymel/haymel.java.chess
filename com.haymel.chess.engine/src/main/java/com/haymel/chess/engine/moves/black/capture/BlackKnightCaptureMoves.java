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
	private final Moves moves;
	
	public BlackKnightCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece piece) {
		assert piece != null;
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == BlackKnight;

		Field from = piece.field();
		
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
		
		if (piece.white())
			moves.addCapture(from, to, piece);
	}
	
}
