/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h7;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public final class F8Attacked {

	/**
	 * This method will be used to check if white king side castling is
	 * prevented by an attack of f8 by a white piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a8, b8, c8, d8 and g8, h8.
	 * This will be checked in {@link E8Attacked#e8Attacked(Board)}.
	 * 
	 */
	public static boolean f8Attacked(Board board) {
		assert board != null;
		assert board.assertVerify();
		
		return 
			f7f1(board) || 
			e7a3(board) ||
			g7h6(board) ||
			knights(board) || 
			pawns(board) ||
			board.piece(g7).whiteKing();
	}

	static boolean f7f1(Board board) {
		Field f = f7;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.down();
			piece = board.piece(f);
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean e7a3(Board board) {
		Field f = e7;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.leftDown();
			piece = board.piece(f);
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean g7h6(Board board) {
		Field f = g7;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.rightDown();
			piece = board.piece(f);
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.piece(d7).whiteKnight() ||
			board.piece(e6).whiteKnight() ||
			board.piece(g6).whiteKnight() ||
			board.piece(h7).whiteKnight();
	}

	static boolean pawns(Board board) {
		return board.piece(e7).whitePawn() || board.piece(g7).whitePawn();
	}

}
