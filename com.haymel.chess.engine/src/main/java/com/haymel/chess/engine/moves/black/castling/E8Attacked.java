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
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.left;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

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
		int field = e7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = down(field);
			piece = pieces[field];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}
	
	static boolean d8a8(Piece[] pieces) {
		int field = d8;
		Piece piece = pieces[field];
		while(piece == null) {
			field = left(field);
			piece = pieces[field];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}
	
	static boolean f8h8(Piece[] pieces) {
		int field = f8;
		Piece piece = pieces[field];
		while(piece == null) {
			field = right(field);
			piece = pieces[field];
		}
		
		return piece.type() == PieceType.WhiteRook || piece.type() == WhiteQueen;
	}
	
	static boolean d7a4(Piece[] pieces) {
		int field = d7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftDown(field);
			piece = pieces[field];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}
	
	static boolean f7h5(Piece[] pieces) {
		int field = f7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = Field.rightDown(field);
			piece = pieces[field];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			whiteKnight(pieces[c7]) ||
			whiteKnight(pieces[d6]) ||
			whiteKnight(pieces[f6]) ||
			whiteKnight(pieces[g7]);
	}

	static boolean pawns(Piece[] pieces) {
		return whitePawn(pieces[d7]) || whitePawn(pieces[f7]);
	}

	private static boolean whiteKnight(Piece piece) {
		return piece != null && piece.type() == WhiteKnight;
	}

	private static boolean whitePawn(Piece piece) {
		return piece != null && piece.type() == WhitePawn;
	}

}
