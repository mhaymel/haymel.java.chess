/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.casteling;

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
	 * This method will be used to check if white queen side casteling is
	 * prevented by an attack of d1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a1, b1, c1 and e1, f1, g1, h1.
	 * This will be checked in {@link E1Attacked#e1Attacked(Board)}.
	 * 
	 */
	public static boolean d1Attacked(Board board) {
		return 
			d2d8(board) || 
			c2a4(board) ||
			e2h5(board) ||
			knights(board) || 
			pawns(board);
	}

	static boolean d2d8(Board board) {
		Field f = d2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.up();
			piece = board.piece(f);
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean c2a4(Board board) {
		Field f = c2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.leftUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean e2h5(Board board) {
		Field f = e2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.rightUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.piece(b2).blackKnight() ||
			board.piece(c3).blackKnight() ||
			board.piece(e3).blackKnight() ||
			board.piece(f2).blackKnight();
	}

	static boolean pawns(Board board) {
		return board.piece(c2).blackPawn() || board.piece(e2).blackPawn();
	}

}
