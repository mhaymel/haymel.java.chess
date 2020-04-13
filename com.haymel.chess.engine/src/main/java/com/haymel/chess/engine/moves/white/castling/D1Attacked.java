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
			pieces[c2.position()].blackKing();
	}

	static boolean d2d8(Piece[] pieces) {
		Field f = d2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.up();
			piece = pieces[f.position()];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean c2a4(Piece[] pieces) {
		Field f = c2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.leftUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean e2h5(Piece[] pieces) {
		Field f = e2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.rightUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			pieces[b2.position()].blackKnight() ||
			pieces[c3.position()].blackKnight() ||
			pieces[e3.position()].blackKnight() ||
			pieces[f2.position()].blackKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return pieces[c2.position()].blackPawn() || pieces[e2.position()].blackPawn();
	}

}
