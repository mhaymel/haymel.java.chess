/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.pieceTypeValid;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public final class Piece {

	private int type;
	private int field; 

	public static final Piece border = new Piece(Border, removed);
	
	public Piece(int type, int field) {
		assert pieceTypeValid(type);
		assert Field.valid(field);
		
		this.type = type;
		this.field = field;
	}
	
	public boolean white() {
		return PieceType.white(type);
	}

	public boolean black() {
		return PieceType.black(type);
	}

	public int type() {
		return type;
	}
	
	public void type(int type) {
		assert pieceTypeValid(type);
		this.type = type;
	}
	
	public int field() {
		return field;
	}
	
	public void field(int field) {
		assert Field.valid(field);

		assert field == removed || type != WhitePawn || (Field.rank(field) != 0 && Field.rank(field) != 7) : format("a white pawn must not be placed on file 1 or 8. The value of field is %s", field);
		assert field == removed || type != BlackPawn || (Field.rank(field) != 0 && Field.rank(field) != 7) : format("a black pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		assert type() != PieceType.Border || field == removed;
				
		this.field = field;
	}

	@Override
	public String toString() {
		return format("Piece(%s, %s)", type, fieldAsString(field));
	}
}
