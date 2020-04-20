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
import static com.haymel.chess.engine.board.Field.left;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;

import com.haymel.chess.engine.board.Board;
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
		int field = e2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = up(field);
			piece = pieces[field];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d1a1(Piece[] pieces) {
		int field = d1;
		Piece piece = pieces[field];
		while(piece == null) {
			field = left(field);
			piece = pieces[field];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean f1h1(Piece[] pieces) {
		int field = f1;
		Piece piece = pieces[field];
		while(piece == null) {
			field = right(field);
			piece = pieces[field];
		}
		
		return piece.blackRook() || piece.blackQueen();
	}
	
	static boolean d2a5(Piece[] pieces) {
		int field = d2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftUp(field);
			piece = pieces[field];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean f2h4(Piece[] pieces) {
		int field = f2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = rightUp(field);
			piece = pieces[field];
		}
		
		return piece.blackBishop() || piece.blackQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			blackKnight(pieces[c2]) ||
			blackKnight(pieces[d3]) ||
			blackKnight(pieces[f3]) ||
			blackKnight(pieces[g2]);
	}

	private static boolean blackKnight(Piece piece) {
		return piece != null && piece.blackKnight();
	}

	static boolean pawns(Piece[] pieces) {
		return blackPawn(pieces[d2]) || blackPawn(pieces[f2]);
	}

	private static boolean blackPawn(Piece piece) {
		return piece != null && piece.blackPawn();
	}

}
