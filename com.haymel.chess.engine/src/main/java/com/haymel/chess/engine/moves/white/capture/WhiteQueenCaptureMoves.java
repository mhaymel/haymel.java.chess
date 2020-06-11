/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.black;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteQueenCaptureMoves {	//TODO unit test

	private final Piece[] pieces;
	
	public WhiteQueenCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == WhiteQueen;

		int from = piece.field();
		return
			up(from, moves) &&
			down(from, moves) &&
			left(from, moves) &&
			right(from, moves) &&
			leftUp(from, moves) &&
			leftDown(from, moves) &&
			rightUp(from, moves) &&
			rightDown(from, moves);
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
			moves.addCapture(from, to);
		
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
			moves.addCapture(from, to);
		
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
			moves.addCapture(from, to);
		
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
			moves.addCapture(from, to);
		
		return true;
	}

	private boolean leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftUp(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.addCapture(from, to);
		
		return true;
	}

	private boolean leftDown(int from, Moves moves) {
		int to = Field.leftDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftDown(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.addCapture(from, to);
		
		return true;
	}

	private boolean rightUp(int from, Moves moves) {
		int to = Field.rightUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightUp(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.addCapture(from, to);
		
		return true;
	}

	private boolean rightDown(int from, Moves moves) {
		int to = Field.rightDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightDown(to);
			piece = pieces[to];
		}

		if (piece.type() == BlackKing)
			return false;
		
		if (black(piece.type())) 
			moves.addCapture(from, to);
		
		return true;
	}
	
}
