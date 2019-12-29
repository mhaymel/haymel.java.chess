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
import static java.lang.String.format;

import com.haymel.chess.engine.piece.Piece;

public final class Board {

	private final Piece[] pieces = new Piece[up*up];
	
	public Board() {
		reset();
	}
	
	public Piece piece(Field f) {
		return pieces[f.position()];
	}

	public void place(Piece piece) {
		assert !piece(piece.field()).border() : format("cannot place piece on border: %s", piece);
		assert !piece.free() : format("piece.free() must return false for %s", piece);
		assert (!piece.black() && piece.white()) || (piece.black() && !piece.white()): format("piece must be black or white %s", piece);
		
		pieces[piece.field().position()] = piece;
	}

	public void clear(Field f) {
		assert !piece(f).border() : format("cannot clear border: %s", f);
		pieces[f.position()] = free;
	}
	
	public void reset() {		//TODO unit test
		for(int i = 0; i < pieces.length; i++)
			pieces[i] = border;

		Field f = Field.a1;
		for(int i = 0; i < 8; i++) {
			init(f);
			f = f.up();
		}
	}
	
	public boolean isFree(Field f) {
		assert f != null;
		return piece(f).free();
	}
	
	private void init(Field f) {
		for(int i = 0; i < 8; i++) {
			pieces[f.position()] = free;
			f = f.right();
		}
	}

	public boolean assertVerify() {
		for(int i = 0; i < pieces.length; i++) {
			assert pieces[i].free() || pieces[i].border() || piece(pieces[i].field()) == pieces[i];
		}
		return true;
	}

}
