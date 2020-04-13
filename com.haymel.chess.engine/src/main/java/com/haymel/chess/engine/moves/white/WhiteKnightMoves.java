/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKnightMoves {

	private final Piece[] pieces;
	
	public WhiteKnightMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece knight, Moves moves) {
		assert knight != null;
		assert moves != null;
		assert knight.field() != removed;
		assert pieces[knight.field().position()] == knight;

		Field from = knight.field();
		
		add(from, from.leftLeftUp(), moves);
		add(from, from.leftDownDown(), moves);
		add(from, from.rightRightUp(), moves);
		add(from, from.rightRightDown(), moves);
		add(from, from.rightDownDown(), moves);
		add(from, from.leftUpUp(), moves);
		add(from, from.rightUpUp(), moves);
		add(from, from.leftLeftDown(), moves);
	}

	private void add(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		
		if (piece.free()) {
			moves.add(from, to);
		}
		else if (piece.black()) {
			moves.addCapture(from, to, piece);
		}
	}
	
}
