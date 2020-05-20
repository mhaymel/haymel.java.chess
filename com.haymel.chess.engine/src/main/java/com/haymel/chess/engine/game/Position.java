/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.castling.PositionCastlingRight;
import com.haymel.chess.engine.piece.Piece;

public final class Position {	//TODO unit test and refactor

	private Piece[] board;
	private ActiveColor activeColor;
	private int enPassant;
	private int halfMoveClock = 0;
	private int fullMoveNumber = 1;
	private final PositionCastlingRight castling;
	
	public Position(
		Piece[] board, 
		ActiveColor activeColor,
		int enPassant,
		int halfMoveClock,
		int fullMoveNumber,
		PositionCastlingRight castling) {
		
		this.board = board;
		this.activeColor = activeColor;
		this.enPassant = enPassant;
		this.halfMoveClock = halfMoveClock;
		this.fullMoveNumber = fullMoveNumber;
		this.castling = castling;
	}
	
	public Position() {
		this(Board.newBoard(), white, removed, 0, 1, new PositionCastlingRight());
	}

	public PositionCastlingRight castlingRight() {
		return castling;
	}

	public Piece[] board() {
		return board;
	}

	public void fullMoveNumber(int value) {
		this.fullMoveNumber = value;	
	}

	public void halfMoveClock(int value) {
		this.halfMoveClock = value;	
	}

	public void resetEnPassant() {
		enPassant = removed;
	}

	public ActiveColor activeColor() {
		return activeColor;
	}

	public void enPassant(int field) {
		assert Field.valid(field);
		assert field == removed || board[field] == null;
		assert 
			field == removed ||
			activeColor == white && rank(field) == rank(a3) && board[Field.up(field)] != null && board[Field.up(field)].type() == WhitePawn||
			activeColor == black && rank(field) == rank(a6) && board[Field.down(field)] != null && board[Field.down(field)].type() == BlackPawn;
		
		enPassant = field;
	}

	public void activeColorWhite() {
		activeColor = white;
	}

	public void activeColorBlack() {
		activeColor = black;
	}

	public int enPassant() {
		return enPassant;
	}

	public int halfMoveClock() {
		return halfMoveClock;
	}

	public int fullMoveNumber() {
		return fullMoveNumber;
	}
	
	public void verify() {
		verifyWhiteKing();
		verifyBlackKing();
		verifyCastling();
	}

	private void verifyWhiteKing() {
		int count = 0;
		for(int i = 0; i < board.length; i++) 
			if (whiteKing(i))
				count++;
		
		if (count != 1)
			throwIAE("There must be exactly one white king but %s were found", count);
	}

	private void verifyBlackKing() {
		int count = 0;
		for(int i = 0; i < board.length; i++) 
			if (blackKing(i))
				count++;
		
		if (count != 1)
			throwIAE("There must be exactly one black king but %s were found", count);
	}
	
	private void verifyCastling() {
		PositionCastlingRight castling = castlingRight();
		if (castling.white().kingside() && (!whiteKing(e1)|| !whiteRook(h1)))
			throwIAE("White kingside castling is not possible");
		if (castling.white().queenside() && (!whiteKing(e1) || !whiteRook(a1)))
			throwIAE("White queenside castling is not possible");
		if (castling.black().kingside() && (!blackKing(e8) || !blackRook(h8)))
			throwIAE("Black kingside castling is not possible");
		if (castling.black().queenside() && (!blackKing(e8) || !blackRook(a8)))
			throwIAE("Black queenside castling is not possible");
	}
	
	private boolean whiteKing(int field) {
		assert Field.valid(field);
		return !free(field) && piece(field).type() == WhiteKing;
	}
	
	private Piece piece(int field) {
		return board[field];
	}

	private boolean whiteRook(int field) {
		assert Field.valid(field);
		return !free(field) && piece(field).type() == WhiteRook;
	}
	
	private boolean blackKing(int field) {
		assert Field.valid(field);
		return !free(field) && piece(field).type() == BlackKing;
	}
	
	private boolean blackRook(int field) {
		assert Field.valid(field);
		return !free(field) && piece(field).type() == BlackRook;
	}
	
	private boolean free(int field) {
		assert Field.valid(field);
		return piece(field) == null;
	}
	
}
