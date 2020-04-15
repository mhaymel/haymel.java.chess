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

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteRookMoves {

	private final Piece[] pieces;
	
	public WhiteRookMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece rook, Moves moves) {
		assert rook != null;
		assert moves != null;
		assert rook.field() != removed;
		assert pieces[rook.field()] == rook;
		assert rook.whiteRook() : format("piece must be white rook but is %s", rook);

		int from = rook.field();
		up(from, moves);
		down(from, moves);
		left(from, moves);
		right(from, moves);
	}

	private void up(int from, Moves moves) {
		int to = Field.up(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.up(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void down(int from, Moves moves) {
		int to = Field.down(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.down(to);
			piece = pieces[to];		
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void left(int from, Moves moves) {
		int to = Field.left(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.left(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void right(int from, Moves moves) {
		int to = Field.right(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.right(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
