/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.board.Field.removed;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.piece.Piece;

public final class PieceList {	//TODO unit test

	private final List<Piece> pieces = new ArrayList<Piece>();
	
	public void add(Piece piece) {
		assert piece != null;
		assert !piece.free();
		assert !piece.border();
		assert piece.field() != removed;
		assert size() <= 16;

		pieces.add(piece);
		
		assert size() <= 16;
	}

	public void remove(Piece piece) {
		assert piece != null;
		assert size() > 0;

		boolean removed = pieces.remove(piece);
		
		assert removed;
	}
	
	public int size() {
		return pieces.size();
	}
	
	public Piece piece(int index) {
		assert index >= 0;
		assert index < size();
		
		return pieces.get(index);
	}

	public boolean contains(Piece p) {
		return pieces.contains(p);
	}
	
	public void clear() {
		pieces.clear();
	}
	
}
