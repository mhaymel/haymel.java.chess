/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public final class Piece {

	private PieceType type;
	private int field; 
	private boolean moved;

	public static final Piece border = new Piece(Border, removed);
	
	public Piece(PieceType type, int field) {
		assert type != null;
		assert Field.valid(field);
		
		this.type = type;
		this.field = field;
		moved = true;
	}
	
	public boolean white() {
		switch(type) {
		case WhitePawn:
		case WhiteRook:
		case WhiteKnight:
		case WhiteBishop:
		case WhiteQueen:
		case WhiteKing:
			return true;
		default:
			return false;
		}
	}

	public boolean black() {
		switch(type) {
		case BlackPawn:
		case BlackRook:
		case BlackKnight:
		case BlackBishop:
		case BlackQueen:
		case BlackKing:
			return true;
		default:
			return false;
		}
	}

	public PieceType type() {
		return type;
	}
	
	public void type(PieceType type) {
		this.type = type;
	}

	public boolean border() {
		return type == Border;
	}
	
	public int field() {
		return field;
	}
	
	public void field(int field) {
		assert Field.valid(field);

		assert field == removed || type != WhitePawn || (Field.rank(field) != 0 && Field.rank(field) != 7) : format("a white pawn must not be placed on file 1 or 8. The value of field is %s", field);
		assert field == removed || type != BlackPawn || (Field.rank(field) != 0 && Field.rank(field) != 7) : format("a black pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		assert !border() || field == removed;
				
		this.field = field;
	}

	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || type != WhiteKing || field() == e1 : format("a white king which was not moved must be on field e1. The current value of field is %s", fieldAsString(field())); 
		assert value || type != WhiteRook || field() == a1 || field() == h1 : format("a white rook which was not moved must be on field a1 or h1. The current value of field is %s", fieldAsString(field())); 
		
		assert value || type != BlackKing || field() == e8 : format("a black king which was not moved must be on field e8. The current value of field is %s", fieldAsString(field())); 
		assert value || type != BlackRook || field() == a8 || field() == h8 : format("a black rook which was not moved must be on field a8 or h8. The current value of field is %s", fieldAsString(field())); 
		
		moved = value;
	}

	public boolean blackKing() {
		return type == BlackKing;
	}

	public boolean whiteKing() {
		return type == WhiteKing;
	}

	public boolean blackRook() {
		return type == BlackRook;
	}

	public boolean blackQueen() {
		return type == BlackQueen;
	}
	
	public boolean whiteQueen() {		// TODO unit test
		return type == WhiteQueen;
	}

	public boolean whiteRook() {
		return type == WhiteRook;
	}

	public boolean blackKnight() {
		return type == BlackKnight;
	}

	public boolean blackBishop() {
		return type == BlackBishop;
	}

	public boolean blackPawn() {
		return type == BlackPawn;
	}

	public boolean whitePawn() {
		return type == WhitePawn;
	}

	public boolean whiteBishop() {			//TODO unit test
		return type == WhiteBishop;	
	}

	public boolean whiteKnight() {
		return  type == WhiteKnight;
	}

	@Override
	public String toString() {
		return format("Piece(%s, %s)", type, fieldAsString(field));
	}
}
