/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.left;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;
import static java.lang.String.format;

import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKingCaptureMoves {	//TODO unit test

	private final Piece[] pieces;
	
	public WhiteKingCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.pieces = pieces;
	}
	
	public void generate(Piece king, Moves moves) {
		assert king != null;
		assert moves != null;
		assert king.field() != removed;
		assert pieces[king.field()] == king;
		assert king.whiteKing() : format("piece must be white king but is %s", king);

		int from = king.field();
		
		add(from, left(from), moves);
		add(from, right(from), moves);
		add(from, up(from), moves);
		add(from, down(from), moves);
		add(from, leftUp(from), moves);
		add(from, leftDown(from), moves);
		add(from, rightUp(from), moves);
		add(from, rightDown(from), moves);
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece.black())
			moves.addCapture(from, to, piece);
	}
	
}
