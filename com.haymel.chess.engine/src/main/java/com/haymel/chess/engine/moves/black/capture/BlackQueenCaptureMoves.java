/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackQueenCaptureMoves {	//TODO unit test

	private final Piece[] pieces;
	
	public BlackQueenCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field().position()] == piece;
		assert piece.type() == BlackQueen;

		Field from = piece.field();
		up(from, moves);
		down(from, moves);
		left(from, moves);
		right(from, moves);
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void up(Field from, Moves moves) {
		Field to = from.up();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.up();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void down(Field from, Moves moves) {
		Field to = from.down();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.down();
			piece = pieces[to.position()];		
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void left(Field from, Moves moves) {
		Field to = from.left();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.left();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void right(Field from, Moves moves) {
		Field to = from.right();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.right();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void leftUp(Field from, Moves moves) {
		Field to = from.leftUp();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.leftUp();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(Field from, Moves moves) {
		Field to = from.leftDown();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.leftDown();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(Field from, Moves moves) {
		Field to = from.rightUp();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.rightUp();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(Field from, Moves moves) {
		Field to = from.rightDown();
		Piece piece = pieces[to.position()];
		while(piece.free()) {
			to = to.rightDown();
			piece = pieces[to.position()];
		}
		if (piece.white()) 
			moves.addCapture(from, to, piece);
	}
	
}
