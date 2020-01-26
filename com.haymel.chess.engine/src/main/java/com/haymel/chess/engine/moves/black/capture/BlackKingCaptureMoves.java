/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKingCaptureMoves {		//TODO unit test

	private final Board board;
	private final Moves moves;
	
	public BlackKingCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece king) {
		assert king != null;
		assert king.field() != removed;
		assert board.piece(king.field()) == king;
		assert king.blackKing() : format("piece must be black king but is %s", king);

		Field from = king.field();
		
		add(from, from.left());
		add(from, from.right());
		add(from, from.up());
		add(from, from.down());
		add(from, from.leftUp());
		add(from, from.leftDown());
		add(from, from.rightUp());
		add(from, from.rightDown());
	}

	private void add(Field from, Field to) {
		Piece piece = board.piece(to);
		
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

}
