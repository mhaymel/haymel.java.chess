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

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackRookCaptureMoves {		//TODO unit test

	private final Piece[] pieces;
	
	public BlackRookCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.blackRook() : format("piece must be black rook but is %s", piece);

		int from = piece.field();
		up(from, moves);
		down(from, moves);
		left(from, moves);
		right(from, moves);
	}

	private void up(int from, Moves moves) {
		int to = Field.up(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.up(to);
			piece = pieces[to];
		}
		if (piece.white()) 
			moves.addRookCapture(from, to, piece);
	}

	private void down(int from, Moves moves) {
		int to = Field.down(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.down(to);
			piece = pieces[to];		
		}
		if (piece.white()) 
			moves.addRookCapture(from, to, piece);
	}

	private void left(int from, Moves moves) {
		int to = Field.left(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.left(to);
			piece = pieces[to];
		}
		if (piece.white()) 
			moves.addRookCapture(from, to, piece);
	}

	private void right(int from, Moves moves) {
		int to = Field.right(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.right(to);
			piece = pieces[to];
		}
		if (piece.white()) 
			moves.addRookCapture(from, to, piece);
	}
	
}
