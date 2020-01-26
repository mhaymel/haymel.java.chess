/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteBishopCaptureMoves {	//TODO unit test
	
	private final Board board;
	private final Moves moves;
	
	public WhiteBishopCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece piece) {
		assert piece != null;
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == WhiteBishop;

		Field from = piece.field();
		leftUp(from);
		leftDown(from);
		rightUp(from);
		rightDown(from);
	}

	private void leftUp(Field from) {
		Field to = from.leftUp();
		Piece piece = board.piece(to);
		while(piece.free()) {
			to = to.leftUp();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(Field from) {
		Field to = from.leftDown();
		Piece piece = board.piece(to);
		while(piece.free()) {
			to = to.leftDown();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(Field from) {
		Field to = from.rightUp();
		Piece piece = board.piece(to);
		while(piece.free()) {
			to = to.rightUp();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(Field from) {
		Field to = from.rightDown();
		Piece piece = board.piece(to);
		while(piece.free()) {
			to = to.rightDown();
			piece = board.piece(to);
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
