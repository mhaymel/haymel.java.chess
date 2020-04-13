/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f7;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public final class D8Attacked {

	/**
	 * This method will be used to check if white queen side castling is
	 * prevented by an attack of d1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a8, b8, c8 and e8, f8, g8, h8.
	 * This will be checked in {@link E8Attacked#e8Attacked(Board)}.
	 * 
	 */
	public static boolean d8Attacked(Board board) {
		assert board != null;
		assert board.assertVerify();

		return 
			d7d1(board) || 
			c7a5(board) ||
			e7h4(board) ||
			knights(board) || 
			pawns(board) ||
			board.pieces[c7.position()].whiteKing();
	}

	static boolean d7d1(Board board) {
		Field f = d7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.down();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean c7a5(Board board) {
		Field f = c7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.leftDown();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean e7h4(Board board) {
		Field f = e7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.rightDown();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.pieces[b7.position()].whiteKnight() ||
			board.pieces[c6.position()].whiteKnight() ||
			board.pieces[e6.position()].whiteKnight() ||
			board.pieces[f7.position()].whiteKnight();
	}

	static boolean pawns(Board board) {
		return board.pieces[c7.position()].whitePawn() || board.pieces[e7.position()].whitePawn();
	}

}
