/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g7;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public final class E8Attacked {

	public static boolean e8Attacked(Board board) {
		assert board != null;
		assert board.assertVerify();
 
		return 
			e7e1(board) || 
			d8a8(board) ||
			f8h8(board) ||
			d7a4(board) ||
			f7h5(board) ||
			knights(board) || 
			pawns(board);
	}

	static boolean e7e1(Board board) {
		Field f = e7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.down();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean d8a8(Board board) {
		Field f = d8;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.left();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean f8h8(Board board) {
		Field f = f8;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.right();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean d7a4(Board board) {
		Field f = d7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.leftDown();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean f7h5(Board board) {
		Field f = f7;
		Piece piece = board.pieces[f.position()];
		while(piece.free()) {
			f = f.rightDown();
			piece = board.pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.pieces[c7.position()].whiteKnight() ||
			board.pieces[d6.position()].whiteKnight() ||
			board.pieces[f6.position()].whiteKnight() ||
			board.pieces[g7.position()].whiteKnight();
	}

	static boolean pawns(Board board) {
		return board.pieces[d7.position()].whitePawn() || board.pieces[f7.position()].whitePawn();
	}

}
