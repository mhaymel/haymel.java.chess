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
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static java.lang.String.format;

import com.haymel.chess.engine.castling.CastlingRight;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhiteKingMoves {

	private final Piece[] pieces;
	
	public WhiteKingMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece king, CastlingRight castling, Moves moves) {
		assert king != null;
		assert castling != null;
		assert moves != null;
		assert king.field() != removed;
		assert pieces[king.field()] == king;
		assert king.type() == WhiteKing : format("piece must be white king but is %s", king);

		int from = king.field();
		
		add(from, left(from), moves);
		add(from, right(from), moves);
		add(from, up(from), moves);
		add(from, down(from), moves);
		add(from, leftUp(from), moves);
		add(from, leftDown(from), moves);
		add(from, rightUp(from), moves);
		add(from, rightDown(from), moves);
		
		if (castling.kingside())
			kingSidecasteling(king, moves);
		
		if (castling.queenside())
			queenSidecasteling(king, moves);
	}

	private void kingSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;
		assert isWhiteRook(h1);
		
		if (!isFree(f1))
			return;

		if (!isFree(g1))
			return;
		
		if (e1Attacked(pieces))
			return;

		if (f1Attacked(pieces))
			return; 
		
		moves.addWhiteKingSideCastling();
	}

	private void queenSidecasteling(Piece king, Moves moves) {
		assert king.field() == e1;
		assert isWhiteRook(a1);

		if (!isFree(d1))
			return;

		if (!isFree(c1))
			return;

		if (!isFree(b1))
			return;
		
		if (e1Attacked(pieces))
			return;
		
		if (d1Attacked(pieces))
			return;

		moves.addWhiteQueenSideCastling();
	}

	private boolean isWhiteRook(int field) {
		return whiteRook(pieces[field]);
	}

	private static boolean whiteRook(Piece piece) {
		return piece != null && piece.type() == WhiteRook;
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece == null) {
			moves.addKingMove(from, to);
		}
		else if (PieceType.black(piece.type())) {
			moves.addKingCapture(from, to, piece);
		}
	}

	private final boolean isFree(int field) {
		return pieces[field] == null;
	}
}
