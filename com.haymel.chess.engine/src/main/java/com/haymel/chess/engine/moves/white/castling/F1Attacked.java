/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.castling;

import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h2;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public final class F1Attacked {

	/**
	 * This method will be used to check if white king side castling is
	 * prevented by an attack of f1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a1, b1, c1, d1 and g1, h1.
	 * This will be checked in {@link E1Attacked#e1Attacked(Board)}.
	 * 
	 */
	public static boolean f1Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);

		return 
			f2f8(pieces) || 
			e2a6(pieces) ||
			g2h3(pieces) ||
			knights(pieces) || 
			pawns(pieces) ||
			pieces[g2.position()].blackKing();
	}

	static boolean f2f8(Piece[] pieces) {
		Field f = f2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.up();
			piece = pieces[f.position()];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean e2a6(Piece[] pieces) {
		Field f = e2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.leftUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean g2h3(Piece[] pieces) {
		Field f = g2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.rightUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			pieces[d2.position()].blackKnight() ||
			pieces[e3.position()].blackKnight() ||
			pieces[g3.position()].blackKnight() ||
			pieces[h2.position()].blackKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return pieces[e2.position()].blackPawn() || pieces[g2.position()].blackPawn();
	}

}
