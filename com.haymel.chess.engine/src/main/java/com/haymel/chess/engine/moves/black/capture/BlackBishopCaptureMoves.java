/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackBishopCaptureMoves {	//TODO unit test
	
	private final Piece[] pieces;
	
	public BlackBishopCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackBishop;

		int from = piece.field();
		return 
			leftUp(from, moves) &&
			leftDown(from, moves) &&
			rightUp(from, moves) &&
			rightDown(from, moves);
	}

	private boolean leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		
		if (piece.type() == WhiteKing)
			return false;
		
		if (PieceType.white(piece.type())) 
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
		
		if (piece.type() == WhiteKing)
			return false;

		if (white(piece.type())) 
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
		
		if (piece.type() == WhiteKing)
			return false;

		if (white(piece.type())) 
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
		
		if (piece.type() == WhiteKing)
			return false;

		if (white(piece.type())) 
			moves.addCapture(from, to);

		return true;
	}
	
}
