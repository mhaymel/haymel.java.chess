/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.removed;
import static java.lang.String.format;

import java.util.List;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.pieces.Piece;

public class WhiteKingMoves {

	private final Board board;
	private final List<Move> moves;
	private final Piece king;
	
	public WhiteKingMoves(Board board, List<Move> moves, Piece king) {
		assert board != null;
		assert moves != null;
		assert king != null;
		assert king.field() != removed;
		assert board.piece(king.field()) == king;
		assert king.isWhiteKing() : format("piece must be white king but is %s", king);
		
		this.board = board;
		this.moves = moves;
		this.king = king;
	}
	
	public void generate() {
		Field from = king.field();
		
		add(from, from.left());
		add(from, from.right());
		add(from, from.up());
		add(from, from.down());
		add(from, from.leftUp());
		add(from, from.leftDown());
		add(from, from.rightUp());
		add(from, from.rightDown());
		
		if (king.moved())
			return;
		
		kingSidecasteling();
		queenSidecasteling();
	}

	private void kingSidecasteling() {
		assert king.field() == e1;
		
		if (!board.isFree(f1))
			return;

		if (!board.isFree(g1))
			return;
		
		if (!isRookNotMoved(h1))
			return;
		
		moves.add(new Move(e1, g1));
	}

	private void queenSidecasteling() {
		assert king.field() == e1;

		if (!board.isFree(d1))
			return;

		if (!board.isFree(c1))
			return;

		if (!board.isFree(b1))
			return;
		
		if (!isRookNotMoved(a1))
			return;
		
		moves.add(new Move(e1, c1));
	}

	private void add(Field from, Field to) {
		Piece piece = board.piece(to);
		
		if (piece.free()) {
			moves.add(new Move(from, to));
		}
		else if (piece.black()) {
			assert !piece.isBlackKing() : format("cannot capture black king %s", piece);	
			moves.add(new Move(from, to));
		}
	}

	private boolean isRookNotMoved(Field f) {
		Piece piece = board.piece(f);
		return piece.isWhiteRook() && !piece.moved();
	}
	
}
