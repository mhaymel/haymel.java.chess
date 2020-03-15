/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKingCaptureMoves {	//TODO unit test

	private final Board board;
	
	public WhiteKingCaptureMoves(Board board) {
		assert board != null;
		
		this.board = board;
	}
	
	public void generate(Piece king, Moves moves) {
		assert king != null;
		assert moves != null;
		assert king.field() != removed;
		assert board.piece(king.field()) == king;
		assert king.whiteKing() : format("piece must be white king but is %s", king);

		Field from = king.field();
		
		add(from, from.left(), moves);
		add(from, from.right(), moves);
		add(from, from.up(), moves);
		add(from, from.down(), moves);
		add(from, from.leftUp(), moves);
		add(from, from.leftDown(), moves);
		add(from, from.rightUp(), moves);
		add(from, from.rightDown(), moves);
	}

	private void add(Field from, Field to, Moves moves) {
		Piece piece = board.piece(to);
		
		if (piece.black())
			moves.addCapture(from, to, piece);
	}
	
}
