/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.casteling;

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

	public static boolean f1Attacked(Board board) {
		return 
			f2f8(board) || 
			e2a6(board) ||
			g2h3(board) ||
			knights(board) || 
			pawns(board);
	}

	static boolean f2f8(Board board) {
		Field f = f2;
		Piece piece = board.piece(f);
		while(piece.free()) {
			f = f.up();
			piece = board.piece(f);
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean e2a6(Board board) {
		Field f = e2;
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
