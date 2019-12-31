/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteRookMoves {

	private final Board board;
	private final Moves moves;
	
	public WhiteRookMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece rook) {
		assert rook != null;
		assert rook.field() != removed;
		assert board.piece(rook.field()) == rook;
		assert rook.whiteRook() : format("piece must be white rook but is %s", rook);

		Field from = rook.field();
		up(from);
		down(from);
		left(from);
		right(from);
	}

	private void up(Field from) {
		Field to = from.up();
		Piece piece = board.piece(to);
		while(piece.free()) {
			moves.add(from, to);
			to = to.up();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void down(Field from) {
		Field to = from.down();
		Piece piece = board.piece(to);
		while(piece.free()) {
			moves.add(from, to);
			to = to.down();
			piece = board.piece(to);		
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void left(Field from) {
		Field to = from.left();
		Piece piece = board.piece(to);
		while(piece.free()) {
			moves.add(from, to);
			to = to.left();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void right(Field from) {
		Field to = from.right();
		Piece piece = board.piece(to);
		while(piece.free()) {
			moves.add(from, to);
			to = to.right();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
