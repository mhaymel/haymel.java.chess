/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.leftDownDown;
import static com.haymel.chess.engine.board.Field.leftLeftDown;
import static com.haymel.chess.engine.board.Field.leftLeftUp;
import static com.haymel.chess.engine.board.Field.leftUpUp;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightDownDown;
import static com.haymel.chess.engine.board.Field.rightRightDown;
import static com.haymel.chess.engine.board.Field.rightRightUp;
import static com.haymel.chess.engine.board.Field.rightUpUp;

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
		assert pieces[knight.field()] == knight;

		int from = knight.field();
		
		add(from, leftLeftUp(from), moves);
		add(from, leftDownDown(from), moves);
		add(from, rightRightUp(from), moves);
		add(from, rightRightDown(from), moves);
		add(from, rightDownDown(from), moves);
		add(from, leftUpUp(from), moves);
		add(from, rightUpUp(from), moves);
		add(from, leftLeftDown(from), moves);
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece == null) {
			moves.add(from, to);
		}
		else if (piece.black()) {
			moves.addCapture(from, to, piece);
		}
	}
	
}
