/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

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
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;

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
			moves.add(from, to, normal);
		}
		else if (white(piece.type())) {
			assert piece.type() != WhiteKing;
			moves.add(from, to, capture);
		}
	}
	
}
