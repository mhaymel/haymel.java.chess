/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h2;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

final class D1Attacked {

	static boolean d1Attacked(Board board) {
		return 
			d2d8(board) || 
			d2a4(board) ||
			g2h3(board) ||
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
	
	static boolean d2a4(Board board) {
		Field f = d2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.leftUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean g2h3(Board board) {
		Field f = g2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.rightUp();
			piece = board.piece(f);
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Board board) {
		return 
			board.piece(d2).blackKnight() ||
			board.piece(e3).blackKnight() ||
			board.piece(g3).blackKnight() ||
			board.piece(h2).blackKnight();
	}

	static boolean pawns(Board board) {
		return board.piece(e2).blackPawn() || board.piece(g2).blackPawn();
	}

}
