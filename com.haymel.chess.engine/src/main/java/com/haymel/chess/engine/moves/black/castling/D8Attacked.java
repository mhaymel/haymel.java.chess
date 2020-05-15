/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.castling;

import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.piece.Piece;

public final class D8Attacked {

	/**
	 * This method will be used to check if white queen side castling is
	 * prevented by an attack of d1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a8, b8, c8 and e8, f8, g8, h8.
	 * This will be checked in {@link E8Attacked#e8Attacked(Board)}.
	 * 
	 */
	public static boolean d8Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);

		return 
			d7d1(pieces) || 
			c7a5(pieces) ||
			e7h4(pieces) ||
			knights(pieces) || 
			pawns(pieces) ||
			whiteKing(pieces[c7]);
	}

	static boolean d7d1(Piece[] pieces) {
		int field = d7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = down(field);
			piece = pieces[field];
		}
		
		return piece.type() == WhiteRook || piece.whiteQueen();
	}
	
	static boolean c7a5(Piece[] pieces) {
		int field = c7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftDown(field);
			piece = pieces[field];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean e7h4(Piece[] pieces) {
		int field = e7;
		Piece piece = pieces[field];
		while(piece == null) {
			field = rightDown(field);
			piece = pieces[field];
		}
		
		return piece.whiteBishop() || piece.whiteQueen();
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			whiteKnight(pieces[b7]) ||
			whiteKnight(pieces[c6]) ||
			whiteKnight(pieces[e6]) ||
			whiteKnight(pieces[f7]);
	}

	static boolean pawns(Piece[] pieces) {
		return whitePawn(pieces[c7]) || whitePawn(pieces[e7]);
	}

	private static boolean whitePawn(Piece piece) {
		return piece != null && piece.whitePawn();
	}

	private static boolean whiteKnight(Piece piece) {
		return piece != null && piece.whiteKnight();
	}

	private static boolean whiteKing(Piece piece) {
		return piece != null && piece.type() == WhiteKing;
	}

}
