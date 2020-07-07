/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.piece.Piece;

public final class PieceList {	//TODO unit test

	private int index = 0;
	private final Piece[] pieces = new Piece[16];
	private Piece king = null;
	
	public void init(Piece piece) {
		assert piece != null;
		assert piece != null;
		assert piece.type() != Border;
		assert piece.field() != Field.removed;
		assert index < 16;
		assert piece.index() == -1;
		
		piece.index(index);
		pieces[index] = piece;
		index++;
		
		if (piece.type() == BlackKing || piece.type() == WhiteKing) {
			assert king == null;
			king = piece;
		}
	}

	public int index() {
		return index;
	}
	
	public Piece piece(int pos) {
		assert pos >= 0;
		assert pos < index;
		
		return pieces[pos];
	}

	public boolean contains(Piece p) {
		for(int i = 0; i < index; i++)
			if (pieces[i].field() == p.field())
				return true;
		
		return false;
	}

	public boolean verify() {
		if (index == 0)
			return false;
		
		for(int i = 0; i < index; i++)
			if (!pieces[i].captured())
				return true;
		
		return false;
	}
	
	public Piece king() {
		assert king != null;
		return king;
	}
}
