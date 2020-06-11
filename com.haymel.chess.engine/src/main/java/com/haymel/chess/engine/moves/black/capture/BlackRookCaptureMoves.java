/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;
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
	
	public boolean generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackRook : format("piece must be black rook but is %s", piece);

		int from = piece.field();
		return
			up(from, moves) &&
			down(from, moves) &&
			left(from, moves) &&
			right(from, moves);
	}

	private boolean up(int from, Moves moves) {
		int to = Field.up(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.up(to);
			piece = pieces[to];
		}

		if (piece.type() == WhiteKing)
			return false;
		
		if (white(piece.type())) 
			moves.addRookCapture(from, to);
		
		return true;
	}

	private boolean down(int from, Moves moves) {
		int to = Field.down(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.down(to);
			piece = pieces[to];		
		}

		if (piece.type() == WhiteKing)
			return false;
		
		if (white(piece.type())) 
			moves.addRookCapture(from, to);
		
		return true;
	}

	private boolean left(int from, Moves moves) {
		int to = Field.left(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.left(to);
			piece = pieces[to];
		}

		if (piece.type() == WhiteKing)
			return false;
		
		if (white(piece.type())) 
			moves.addRookCapture(from, to);
		
		return true;
	}

	private boolean right(int from, Moves moves) {
		int to = Field.right(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.right(to);
			piece = pieces[to];
		}

		if (piece.type() == WhiteKing)
			return false;
		
		if (white(piece.type())) 
			moves.addRookCapture(from, to);
		
		return true;
	}
	
}
