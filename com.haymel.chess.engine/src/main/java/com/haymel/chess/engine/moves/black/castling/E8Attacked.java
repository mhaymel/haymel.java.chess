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

	public static boolean e8Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);
 
		return 
			e7e1(pieces) || 
			d8a8(pieces) ||
			f8h8(pieces) ||
			d7a4(pieces) ||
			f7h5(pieces) ||
			knights(pieces) || 
			pawns(pieces);
	}

	static boolean e7e1(Piece[] pieces) {
		Field f = e7;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.down();
			piece = pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean d8a8(Piece[] pieces) {
		Field f = d8;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.left();
			piece = pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean f8h8(Piece[] pieces) {
		Field f = f8;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.right();
			piece = pieces[f.position()];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean d7a4(Piece[] pieces) {
		Field f = d7;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.leftDown();
			piece = pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean f7h5(Piece[] pieces) {
		Field f = f7;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.rightDown();
			piece = pieces[f.position()];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			pieces[c7.position()].whiteKnight() ||
			pieces[d6.position()].whiteKnight() ||
			pieces[f6.position()].whiteKnight() ||
			pieces[g7.position()].whiteKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return pieces[d7.position()].whitePawn() || pieces[f7.position()].whitePawn();
	}

}
