/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKingCaptureMoves {		//TODO unit test

	private final Piece[] pieces;
	
	public BlackKingCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece king, Moves moves) {
		assert king != null;
		assert moves != null;
		assert king.field() != removed;
		assert pieces[king.field()] == king;
		assert king.type() == BlackKing : format("piece must be black king but is %s", king);

		int from = king.field();
		
		add(from, Field.left(from), moves);
		add(from, Field.right(from), moves);
		add(from, Field.up(from), moves);
		add(from, Field.down(from), moves);
		add(from, Field.leftUp(from), moves);
		add(from, Field.leftDown(from), moves);
		add(from, Field.rightUp(from), moves);
		add(from, Field.rightDown(from), moves);
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (white(piece)) 
			moves.addKingCapture(from, to, piece);
	}

	private boolean white(Piece piece) {
		return piece != null && piece.white();
	}

}
