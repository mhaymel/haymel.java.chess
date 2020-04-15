/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteBishopMoves {
	
	private final Piece[] pieces;
	
	public WhiteBishopMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == WhiteBishop;

		int from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void leftDown(int from, Moves moves) {
		int to = Field.leftDown(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.leftDown(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightUp(int from, Moves moves) {
		int to = Field.rightUp(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.rightUp(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}

	private void rightDown(int from, Moves moves) {
		int to = Field.rightDown(from);
		Piece piece = pieces[to];
		while(piece.free()) {
			moves.add(from, to);
			to = Field.rightDown(to);
			piece = pieces[to];
		}
		if (piece.black()) 
			moves.addCapture(from, to, piece);
	}
	
}
