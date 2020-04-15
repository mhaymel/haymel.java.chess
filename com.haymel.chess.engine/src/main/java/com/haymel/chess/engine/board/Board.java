/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.piece.Piece.border;
import static com.haymel.chess.engine.piece.Piece.free;

import com.haymel.chess.engine.piece.Piece;

public final class Board {

	public static Piece[] newBoard() {
		return reset(new Piece[up*up]);
	}
	
	public static Piece[] reset(Piece[] pieces) {		//TODO unit test
		for(int i = 0; i < pieces.length; i++)
			pieces[i] = border;

		int field = Field.a1;
		for(int i = 0; i < 8; i++) {
			init(field, pieces);
			field = up(field);
		}
		
		return pieces;
	}
	
	private static void init(int field, Piece[] pieces) {
		for(int i = 0; i < 8; i++) {
			pieces[field] = free;
			field = right(field);
		}
	}

	private static boolean doVerify(Piece[] pieces) {
		for(int i = 0; i < pieces.length; i++) {
			assert pieces[i].free() || pieces[i].border() || pieces[pieces[i].field()] == pieces[i];
		}
		return true;
	}

	public static boolean assertVerify(Piece[] pieces) {
		assert doVerify(pieces);
		return true;
	}

}
