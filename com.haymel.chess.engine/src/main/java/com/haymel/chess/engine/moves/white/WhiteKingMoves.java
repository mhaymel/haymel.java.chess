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
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.left;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.moves.white.castling.D1Attacked.d1Attacked;
import static com.haymel.chess.engine.moves.white.castling.E1Attacked.e1Attacked;
import static com.haymel.chess.engine.moves.white.castling.F1Attacked.f1Attacked;
import static java.lang.String.format;

import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteKingMoves {

	private final Piece[] pieces;
	
	public WhiteKingMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece king, Moves moves) {
		if (pieces[king.field()] != king)
			System.out.println(king);
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
		
		if (king.moved())
			return;
		
		kingSidecasteling(king, moves);
		queenSidecasteling(king, moves);
	}

	private void kingSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;
		
		if (!isFree(f1))
			return;

		if (!isFree(g1))
			return;
		
		if (isNoRookOrMoved(h1))
			return;
		
		if (e1Attacked(pieces))
			return;

		if (f1Attacked(pieces))
			return; 
		
		moves.addWhiteKingSideCastling();
	}

	private void queenSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;

		if (!isFree(d1))
			return;

		if (!isFree(c1))
			return;

		if (!isFree(b1))
			return;
		
		if (isNoRookOrMoved(a1))
			return;
		
		if (e1Attacked(pieces))
			return;
		
		if (d1Attacked(pieces))
			return;

		moves.addWhiteQueenSideCastling();
	}

	private boolean isMoved(int field) {
		return pieces[field].moved();
	}

	private boolean isWhiteRook(int field) {
		return pieces[field].whiteRook();
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece.free()) {
			moves.add(from, to);
		}
		else if (piece.black()) {
			moves.addCapture(from, to, piece);
		}
	}

	private boolean isNoRookOrMoved(int field) {
		return !isWhiteRook(field) || isMoved(field);
	}
	
	private final boolean isFree(int field) {
		return pieces[field].free();
	}
}
