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

	private final Piece[] pieces;
	
	public WhiteRookMoves(Board board) {
		assert board != null;
		
		this.pieces = board.pieces;
	}
	
	public void generate(Piece rook, Moves moves) {
		assert rook != null;
		assert moves != null;
		assert rook.field() != removed;
		assert pieces[rook.field().position()] == rook;
		assert rook.whiteRook() : format("piece must be white rook but is %s", rook);

		Field from = rook.field();
		up(from, moves);
		down(from, moves);
		left(from, moves);
		right(from, moves);
	}

	private void up(Field from, Moves moves) {
		Field to = from.up();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.up();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void down(Field from, Moves moves) {
		Field to = from.down();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.down();
			piece = pieces[to.position()];		
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void left(Field from, Moves moves) {
		Field to = from.left();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.left();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void right(Field from, Moves moves) {
		Field to = from.right();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.right();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
