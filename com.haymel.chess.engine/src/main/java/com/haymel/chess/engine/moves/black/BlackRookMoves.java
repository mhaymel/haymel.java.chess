/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackRookMoves {

	private final Piece[] pieces;
	
	public BlackRookMoves(Board board) {
		assert board != null;
		
		this.pieces = board.pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field().position()] == piece;
		assert piece.blackRook() : format("piece must be black rook but is %s", piece);

		Field from = piece.field();
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
		if (piece.white()) 
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
		if (piece.white()) 
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
		if (piece.white()) 
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
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}
	
}
