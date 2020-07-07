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
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.moves.black.castling.D8Attacked.d8Attacked;
import static com.haymel.chess.engine.moves.black.castling.E8Attacked.e8Attacked;
import static com.haymel.chess.engine.moves.black.castling.F8Attacked.f8Attacked;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.castling.CastlingRight;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackKingMoves {

	private final Piece[] pieces;
	
	public BlackKingMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece king, CastlingRight castling, Moves moves) {
		assert king != null;
		assert castling != null;
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
		castling(king, castling, moves);
	}

	private void castling(Piece king, CastlingRight castling, Moves moves) {
		if (castling.kingside())
			kingSidecasteling(king, moves);
		
		if (castling.queenside())
			queenSidecasteling(king, moves);
	}

	private void kingSidecasteling(Piece king, Moves moves) {
		assert king.field() == e8;
		assert isBlackRook(h8);
		
		if (!isFree(f8))
			return;

		if (!isFree(g8))
			return;
		
		if (e8Attacked(pieces))
			return;

		if (f8Attacked(pieces))
			return; 
		
		moves.add(e8, g8, kingsideCastling);
	}

	private void queenSidecasteling(Piece king, Moves moves) {
		assert king.field() == e8;
		assert isBlackRook(a8);

		if (!isFree(d8))
			return;

		if (!isFree(c8))
			return;

		if (!isFree(b8))
			return;
		
		if (e8Attacked(pieces))
			return;
		
		if (d8Attacked(pieces))
			return;

		moves.add(e8, c8, queensideCastling);
	}

	private boolean isBlackRook(int field) {
		return pieces[field] != null && pieces[field].type() == BlackRook;
	}

	private void add(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		
		if (piece == null) {
			moves.add(from, to, normalKingMove);
		}
		else if (white(piece.type())) { 
			assert piece.type() != WhiteKing;
	
			moves.add(from, to, captureKingMove);
		}
	}

	private boolean isFree(int field) {
		return pieces[field] == null;
	}

}
