/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.black.castling.E8Attacked.e8Attacked;
import static com.haymel.chess.engine.moves.black.castling.F8Attacked.f8Attacked;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.castling.D8Attacked;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKingMoves {

	private final Board board;
	private final Piece[] pieces;
	
	public BlackKingMoves(Board board) {
		assert board != null;
		
		this.board = board;
		this.pieces = board.pieces;
	}
	
	public void generate(Piece king, Moves moves) {
		assert king != null;
		assert moves != null;
		assert king.field() != removed;
		assert pieces[king.field().position()] == king;
		assert king.blackKing() : format("piece must be black king but is %s", king);

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
		assert king.field() == e8;
		
		if (!board.isFree(f8))
			return;

		if (!board.isFree(g8))
			return;
		
		if (isNoRookOrMoved(h8))
			return;
		
		if (e8Attacked(board))
			return;

		if (f8Attacked(board))
			return; 
		
		moves.addBlackKingSideCastling();
	}

	private void queenSidecasteling(Piece king, Moves moves) {
		assert king.field() == e8;

		if (!board.isFree(d8))
			return;

		if (!board.isFree(c8))
			return;

		if (!board.isFree(b8))
			return;
		
		if (isNoRookOrMoved(a8))
			return;
		
		if (e8Attacked(board))
			return;
		
		if (D8Attacked.d8Attacked(board))
			return;

		moves.addBlackQueenSideCastling();
	}

	private boolean isMoved(Field f) {
		return pieces[f.position()].moved();
	}

	private boolean isBlackRook(Field f) {
		return pieces[f.position()].blackRook();
	}

	private void add(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		
		if (piece.free()) 
			moves.add(from, to);
		
		else if (piece.white()) 
			moves.addCapture(from, to, piece);
		
	}

	private boolean isNoRookOrMoved(Field f) {
		return !isBlackRook(f) || isMoved(f);
	}
	
}
