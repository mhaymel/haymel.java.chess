/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

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
	public static boolean f8Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);
		
		return 
			f7f1(pieces) || 
			e7a3(pieces) ||
			g7h6(pieces) ||
			knights(pieces) || 
			pawns(pieces) ||
			whiteKing(pieces[g7]);
	}

	static boolean f7f1(Piece[] pieces) {
		int field = f7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = down(field);
			piece = pieces[field];
		}
		
		return piece.whiteRook() || piece.whiteQueen();
	}
	
	static boolean e7a3(Piece[] pieces) {
		int field = e7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftDown(field);
			piece = pieces[field];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean g7h6(Piece[] pieces) {
		int field = g7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = Field.rightDown(field);
			piece = pieces[field];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			whiteKnight(pieces[d7]) ||
			whiteKnight(pieces[e6]) ||
			whiteKnight(pieces[g6]) ||
			whiteKnight(pieces[h7]);
	}

	static boolean pawns(Piece[] pieces) {
		return whitePawn(pieces[e7]) || whitePawn(pieces[g7]);
	}

	private static boolean whiteKing(Piece piece) {
		return piece != null && piece.type() == WhiteKing;
	}

	private static boolean whiteKnight(Piece piece) {
		return piece != null && piece.whiteKnight();
	}
	
	private static boolean whitePawn(Piece piece) {
		return piece != null && piece.whitePawn();
	}

}
