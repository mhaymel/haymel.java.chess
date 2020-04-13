/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackBishopMoves {
	
	private final Piece[] board;
	
	public BlackBishopMoves(Piece[] board) {
		assert board != null;
		this.board = board;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert board[piece.field().position()] == piece;
		assert piece.type() == BlackBishop;

		Field from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(Field from, Moves moves) {
		Field to = from.leftUp();
		Piece piece = board[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.leftUp();
			piece = board[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(Field from, Moves moves) {
		Field to = from.leftDown();
		Piece piece = board[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.leftDown();
			piece = board[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(Field from, Moves moves) {
		Field to = from.rightUp();
		Piece piece = board[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.rightUp();
			piece = board[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(Field from, Moves moves) {
		Field to = from.rightDown();
		Piece piece = board[to.position()];
		while(piece.free()) {
			moves.add(from, to);
			to = to.rightDown();
			piece = board[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}
	
}
