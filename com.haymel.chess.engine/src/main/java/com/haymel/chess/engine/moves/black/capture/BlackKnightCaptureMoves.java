/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackKnightCaptureMoves {	//TODO unit test

	private final Piece[] pieces;
	
	public BlackKnightCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackKnight;

		int from = piece.field();
		
		return
			add(from, Field.leftLeftUp(from), moves) &&
			add(from, Field.leftDownDown(from), moves) &&
			add(from, Field.rightRightUp(from), moves) &&
			add(from, Field.rightRightDown(from), moves) &&
			add(from, Field.rightDownDown(from), moves) &&
			add(from, Field.leftUpUp(from), moves) &&
			add(from, Field.rightUpUp(from), moves) &&
			add(from, Field.leftLeftDown(from), moves);
	}

	private boolean add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (!white(piece))
			return true;

		if (piece.type() == WhiteKing)
			return false;

		moves.add(from, to, capture);
		
		return true;
	}

	private static boolean white(Piece piece) {
		return piece != null && PieceType.white(piece.type());
	}
	
}
