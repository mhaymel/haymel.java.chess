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
	
	private final Piece[] pieces;
	
	public WhiteBishopCaptureMoves(Board board) {
		assert board != null;
		
		this.pieces = board.pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field().position()] == piece;
		assert piece.type() == WhiteBishop;

		Field from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(Field from, Moves moves) {
		Field to = from.leftUp();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.leftUp();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(Field from, Moves moves) {
		Field to = from.leftDown();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.leftDown();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(Field from, Moves moves) {
		Field to = from.rightUp();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.rightUp();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(Field from, Moves moves) {
		Field to = from.rightDown();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.rightDown();
			piece = pieces[to.position()];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
