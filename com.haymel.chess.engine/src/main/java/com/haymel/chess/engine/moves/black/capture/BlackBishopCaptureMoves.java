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

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackBishopCaptureMoves {	//TODO unit test
	
	private final Piece[] boardPieces;
	
	public BlackBishopCaptureMoves(Board board) {
		assert board != null;
		
		this.boardPieces = board.pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert boardPieces[piece.field().position()] == piece;
		assert piece.type() == BlackBishop;

		Field from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(Field from, Moves moves) {
		Field to = from.leftUp();
		Piece piece = boardPieces[to.position()];
		while(piece.free()) {
			to = to.leftUp();
			piece = boardPieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(Field from, Moves moves) {
		Field to = from.leftDown();
		Piece piece = boardPieces[to.position()];
		while(piece.free()) {
			to = to.leftDown();
			piece = boardPieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(Field from, Moves moves) {
		Field to = from.rightUp();
		Piece piece = boardPieces[to.position()];
		while(piece.free()) {
			to = to.rightUp();
			piece = boardPieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(Field from, Moves moves) {
		Field to = from.rightDown();
		Piece piece = boardPieces[to.position()];
		while(piece.free()) {
			to = to.rightDown();
			piece = boardPieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}
	
}
