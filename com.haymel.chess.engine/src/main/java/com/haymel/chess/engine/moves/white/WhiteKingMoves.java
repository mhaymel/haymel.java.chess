/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.white.castling.D1Attacked.d1Attacked;
import static com.haymel.chess.engine.moves.white.castling.E1Attacked.e1Attacked;
import static com.haymel.chess.engine.moves.white.castling.F1Attacked.f1Attacked;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKingMoves {

	private final Board board;
	
	public WhiteKingMoves(Board board) {
		assert board != null;
		this.board = board;
	}
	
	public void generate(Piece king, Moves moves) {
		assert king != null;
		assert moves != null;
		assert king.field() != removed;
		assert board.piece(king.field()) == king;
		assert king.whiteKing() : format("piece must be white king but is %s", king);

		Field from = king.field();
		
		add(from, from.left(), moves);
		add(from, from.right(), moves);
		add(from, from.up(), moves);
		add(from, from.down(), moves);
		add(from, from.leftUp(), moves);
		add(from, from.leftDown(), moves);
		add(from, from.rightUp(), moves);
		add(from, from.rightDown(), moves);
		
		if (king.moved())
			return;
		
		kingSidecasteling(king, moves);
		queenSidecasteling(king, moves);
	}

	private void kingSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;
		
		if (!board.isFree(f1))
			return;

		if (!board.isFree(g1))
			return;
		
		if (isNoRookOrMoved(h1))
			return;
		
		if (e1Attacked(board))
			return;

		if (f1Attacked(board))
			return; 
		
		moves.addWhiteKingSideCastling();
	}

	private void queenSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;

		if (!board.isFree(d1))
			return;

		if (!board.isFree(c1))
			return;

		if (!board.isFree(b1))
			return;
		
		if (isNoRookOrMoved(a1))
			return;
		
		if (e1Attacked(board))
			return;
		
		if (d1Attacked(board))
			return;

		moves.addWhiteQueenSideCastling();
	}

	private boolean isMoved(Field f) {
		return board.piece(f).moved();
	}

	private boolean isWhiteRook(Field f) {
		return board.piece(f).whiteRook();
	}

	private void add(Field from, Field to, Moves moves) {
		Piece piece = board.piece(to);
		
		if (piece.free()) {
			moves.add(from, to);
		}
		else if (piece.black()) {
			moves.addCapture(from, to, piece);
		}
	}

	private boolean isNoRookOrMoved(Field f) {
		return !isWhiteRook(f) || isMoved(f);
	}
	
}
