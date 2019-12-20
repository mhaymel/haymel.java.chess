/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.pieces.PieceType.BlackKing;
import static com.haymel.chess.engine.pieces.PieceType.BlackPawn;
import static com.haymel.chess.engine.pieces.PieceType.BlackRook;
import static com.haymel.chess.engine.pieces.PieceType.Border;
import static com.haymel.chess.engine.pieces.PieceType.Free;
import static com.haymel.chess.engine.pieces.PieceType.WhiteKing;
import static com.haymel.chess.engine.pieces.PieceType.WhitePawn;
import static com.haymel.chess.engine.pieces.PieceType.WhiteRook;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public final class Piece {

	private final PieceType type;
	private Field field; 
	private boolean moved;

	public static final Piece border = new Piece(Border);
	public static final Piece free = new Piece(Free);
	
	public Piece(PieceType type) {
		assert type != null;
		
		this.type = type;
		field = removed;
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

	public boolean free() {
		return type == Free;
	}
	
	public boolean isBorder() {
		return type == Border;
	}
	
	public Field field() {
		return field;
	}
	
	public void field(Field field) {
		assert field != null;

		assert field == removed || type != WhitePawn || (field.rank() != 0 && field.rank() != 7) : format("a white pawn must not be placed on file 1 or 8. The value of field is %s", field);
		assert field == removed || type != BlackPawn || (field.rank() != 0 && field.rank() != 7) : format("a black pawn must not be placed on file 1 or 8. The value of field is %s", field);
		
		
		this.field = field;
	}

	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean value) {
		assert value || type != WhiteKing || field() == e1 : format("a white king which was not moved must be on field e1. The current value of field is %s", field()); 
		assert value || type != WhiteRook || field() == a1 || field() == h1 : format("a white rook which was not moved must be on field a1 or h1. The current value of field is %s", field()); 
		
		assert value || type != BlackKing || field() == e8 : format("a black king which was not moved must be on field e8. The current value of field is %s", field()); 
		assert value || type != BlackRook || field() == a8 || field() == h8 : format("a black rook which was not moved must be on field a8 or h8. The current value of field is %s", field()); 
		
		moved = value;
	}

	public boolean isBlackKing() {
		return type == BlackKing;
	}

	public boolean isWhiteKing() {
		return type == WhiteKing;
	}

	public boolean isWhiteRook() {
		return type == WhiteRook;
	}

}
