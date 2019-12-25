/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.g2;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

final class E1Attacked {

	static boolean e1Attacked(Board board) {
		return 
			e2e8(board) || 
			d1a1(board) ||
			f1h1(board) ||
			d2a5(board) ||
			f2h4(board) ||
			knights(board) || 
			pawns(board);
	}

	static boolean e2e8(Board board) {
		Field f = e2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.up();
			piece = board.piece(f);
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d1a1(Board board) {
		Field f = d1;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.left();
			piece = board.piece(f);
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean f1h1(Board board) {
		Field f = f1;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.right();
			piece = board.piece(f);
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d2a5(Board board) {
		Field f = d2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.leftUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean f2h4(Board board) {
		Field f = f2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.rightUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.piece(c2).blackKnight() ||
			board.piece(d3).blackKnight() ||
			board.piece(f3).blackKnight() ||
			board.piece(g2).blackKnight();
	}

	static boolean pawns(Board board) {
		return board.piece(d2).blackPawn() || board.piece(f2).blackPawn();
	}

}
