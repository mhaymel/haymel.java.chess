/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.leftDownDown;
import static com.haymel.chess.engine.board.Field.leftLeftDown;
import static com.haymel.chess.engine.board.Field.leftLeftUp;
import static com.haymel.chess.engine.board.Field.leftUpUp;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightDownDown;
import static com.haymel.chess.engine.board.Field.rightRightDown;
import static com.haymel.chess.engine.board.Field.rightRightUp;
import static com.haymel.chess.engine.board.Field.rightUpUp;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;

import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhiteKnightCaptureMoves {

	private final Piece[] pieces;
	
	public WhiteKnightCaptureMoves(Piece[] pieces) {	//TODO unit test
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
		
		if (!black(piece))
			return;

		assert piece.type() != BlackKing;

		moves.add(from, to, capture);
	}

	private static boolean black(Piece piece) {
		return piece != null && PieceType.black(piece.type());
	}
	
}
