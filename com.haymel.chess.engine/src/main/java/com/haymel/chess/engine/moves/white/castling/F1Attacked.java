/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.castling;

import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.piece.Piece;

public final class F1Attacked {

	/**
	 * This method will be used to check if white king side castling is
	 * prevented by an attack of f1 by a black piece.
	 * 
	 * ATTENTION: 
	 * The method does not check for an attack from a1, b1, c1, d1 and g1, h1.
	 * This will be checked in {@link E1Attacked#e1Attacked(Board)}.
	 * 
	 */
	public static boolean f1Attacked(Piece[] pieces) {
		assert pieces != null;
		assert Board.assertVerify(pieces);

		return 
			f2f8(pieces) || 
			e2a6(pieces) ||
			g2h3(pieces) ||
			knights(pieces) || 
			pawns(pieces) ||
			blackKing(pieces[g2]);
	}

	static boolean f2f8(Piece[] pieces) {
		int field = f2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = up(field);
			piece = pieces[field];
		}
		
		return piece.type() == BlackRook || piece.type() == BlackQueen;
	}
	
	static boolean e2a6(Piece[] pieces) {
		int field = e2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = leftUp(field);
			piece = pieces[field];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}
	
	static boolean g2h3(Piece[] pieces) {
		int field = g2;
		Piece piece = pieces[field];
		while(piece == null) {
			field = rightUp(field);
			piece = pieces[field];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}
	
	static boolean knights(Piece[] pieces) {
		return 
			blackKnight(pieces[d2]) ||
			blackKnight(pieces[e3]) ||
			blackKnight(pieces[g3]) ||
			blackKnight(pieces[h2]);
	}

	static boolean pawns(Piece[] pieces) {
		return blackPawn(pieces[e2]) || blackPawn(pieces[g2]);
	}

	private static boolean blackPawn(Piece piece) {
		return piece != null && piece.type() == BlackPawn;
	}

	private static boolean blackKing(Piece piece) {
		return piece != null && piece.type() == BlackKing;
	}

	private static boolean blackKnight(Piece piece) {
		return piece != null && piece.type() == BlackKnight;
	}

}
