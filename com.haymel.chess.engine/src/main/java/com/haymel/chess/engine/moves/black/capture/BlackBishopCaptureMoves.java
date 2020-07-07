/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
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
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackBishop;

		int from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;
		
		if (PieceType.white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void leftDown(int from, Moves moves) {
		int to = Field.leftDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftDown(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightUp(int from, Moves moves) {
		int to = Field.rightUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightUp(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightDown(int from, Moves moves) {
		int to = Field.rightDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightDown(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}
	
}
