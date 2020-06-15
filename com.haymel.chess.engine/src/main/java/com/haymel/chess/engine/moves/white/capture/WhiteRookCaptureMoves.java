/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static com.haymel.chess.engine.piece.PieceType.black;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteRookCaptureMoves {	//TODO unit test

	private final Piece[] pieces;
	
	public WhiteRookCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece rook, Moves moves) {
		assert rook != null;
		assert moves != null;
		assert rook.field() != removed;
		assert pieces[rook.field()] == rook;
		assert rook.type() == WhiteRook : format("piece must be white rook but is %s", rook);

		int from = rook.field();
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

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.add(from, to, captureRookMove);

		return true;
	}

	private boolean down(int from, Moves moves) {
		int to = Field.down(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.down(to);
			piece = pieces[to];		
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.add(from, to, captureRookMove);

		return true;
	}

	private boolean left(int from, Moves moves) {
		int to = Field.left(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.left(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.add(from, to, captureRookMove);

		return true;
	}

	private boolean right(int from, Moves moves) {
		int to = Field.right(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.right(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.add(from, to, captureRookMove);

		return true;
	}
	
}
