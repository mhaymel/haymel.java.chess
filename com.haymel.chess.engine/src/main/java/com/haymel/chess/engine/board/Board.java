/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

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

		Field f = Field.a1;
		for(int i = 0; i < 8; i++) {
			init(f, pieces);
			f = f.up();
		}
		
		return pieces;
	}
	
	private static void init(Field f, Piece[] pieces) {
		for(int i = 0; i < 8; i++) {
			pieces[f.position()] = free;
			f = f.right();
		}
	}

	private static boolean doVerify(Piece[] pieces) {
		for(int i = 0; i < pieces.length; i++) {
			assert pieces[i].free() || pieces[i].border() || pieces[pieces[i].field().position()] == pieces[i];
		}
		return true;
	}

	public static boolean assertVerify(Piece[] pieces) {
		assert doVerify(pieces);
		return true;
	}

}
