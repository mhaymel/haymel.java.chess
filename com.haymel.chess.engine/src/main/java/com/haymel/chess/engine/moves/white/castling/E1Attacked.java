/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.castling;

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

public final class E1Attacked {

	public static boolean e1Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);

		return 
			e2e8(pieces) || 
			d1a1(pieces) ||
			f1h1(pieces) ||
			d2a5(pieces) ||
			f2h4(pieces) ||
			knights(pieces) || 
			pawns(pieces);
	}

	static boolean e2e8(Piece[] pieces) {
		Field f = e2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.up();
			piece = pieces[f.position()];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d1a1(Piece[] pieces) {
		Field f = d1;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.left();
			piece = pieces[f.position()];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean f1h1(Piece[] pieces) {
		Field f = f1;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.right();
			piece = pieces[f.position()];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d2a5(Piece[] pieces) {
		Field f = d2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.leftUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean f2h4(Piece[] pieces) {
		Field f = f2;
		Piece piece = pieces[f.position()];
		while(piece.free()) {
			f = f.rightUp();
			piece = pieces[f.position()];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			pieces[c2.position()].blackKnight() ||
			pieces[d3.position()].blackKnight() ||
			pieces[f3.position()].blackKnight() ||
			pieces[g2.position()].blackKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return pieces[d2.position()].blackPawn() || pieces[f2.position()].blackPawn();
	}

}
