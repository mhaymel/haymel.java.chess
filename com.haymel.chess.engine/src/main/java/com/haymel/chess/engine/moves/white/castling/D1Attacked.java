/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.castling;

import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.up;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public final class D1Attacked {

	/**
	 * This method will be used to check if white queen side castling is
	 * prevented by an attack of d1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a1, b1, c1 and e1, f1, g1, h1.
	 * This will be checked in {@link E1Attacked#e1Attacked(Board)}.
	 * 
	 */
	public static boolean d1Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);

		return 
			d2d8(pieces) || 
			c2a4(pieces) ||
			e2h5(pieces) ||
			knights(pieces) || 
			pawns(pieces) ||
			king(c2, pieces);
	}

	static boolean d2d8(Piece[] pieces) {
		int field = d2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = up(field);
			piece = pieces[field];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean c2a4(Piece[] pieces) {
		int field = c2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftUp(field);
			piece = pieces[field];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean e2h5(Piece[] pieces) {
		int field = e2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = Field.rightUp(field);
			piece = pieces[field];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			blackKnight(b2, pieces) ||
			blackKnight(c3, pieces) ||
			blackKnight(e3, pieces) ||
			blackKnight(f2, pieces);
	}

	private static boolean blackKnight(int field, Piece[] pieces) {
		return pieces[field] != null && pieces[field].blackKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return blackPawn(c2, pieces) || blackPawn(e2, pieces);
	}

	private static boolean blackPawn(int field, Piece[] pieces) {
		return pieces[field] != null && pieces[field].blackPawn();
	}

	private static boolean king(int field, Piece[] pieces) {
		return pieces[field] != null && pieces[field].blackKing();
	}
	
}
