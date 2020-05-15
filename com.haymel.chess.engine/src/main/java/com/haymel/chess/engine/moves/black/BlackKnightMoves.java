/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.white;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKnightMoves {

	private final Piece[] pieces;
	
	public BlackKnightMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackKnight;

		int from = piece.field();
		
		add(from, Field.leftLeftUp(from), moves);
		add(from, Field.leftDownDown(from), moves);
		add(from, Field.rightRightUp(from), moves);
		add(from, Field.rightRightDown(from), moves);
		add(from, Field.rightDownDown(from), moves);
		add(from, Field.leftUpUp(from), moves);
		add(from, Field.rightUpUp(from), moves);
		add(from, Field.leftLeftDown(from), moves);
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece == null) {
			moves.add(from, to);
		}
		else if (white(piece.type())) {
			moves.addCapture(from, to, piece);
		}
	}
	
}
