/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class GameStartPos {	//TODO unit test

	private Game game;
	
	public Game startPos() {
		game = new Game();
		
		game.activeColorWhite();
		game.resetHalfMoveClock();
		game.resetEnPassant();
		game.resetFullMoveNumber();
		
		addWhitePieces();
		addBlackPieces();
		
		return game;
	}

	private void addWhitePieces() {
		addWhite(WhitePawn, a2);
		addWhite(WhitePawn, b2);
		addWhite(WhitePawn, c2);
		addWhite(WhitePawn, d2);
		addWhite(WhitePawn, e2);
		addWhite(WhitePawn, f2);
		addWhite(WhitePawn, g2);
		addWhite(WhitePawn, h2);
		addWhite(WhiteRook, a1);
		addWhite(WhiteKnight, b1);
		addWhite(WhiteBishop, c1);
		addWhite(WhiteQueen, d1);
		addWhite(WhiteKing, e1);
		addWhite(WhiteBishop, f1);
		addWhite(WhiteKnight, g1);
		addWhite(WhiteRook, h1);
	}

	private void addBlackPieces() {
		addBlack(PieceType.BlackPawn, a7);
		addBlack(PieceType.BlackPawn, b7);
		addBlack(PieceType.BlackPawn, c7);
		addBlack(PieceType.BlackPawn, d7);
		addBlack(PieceType.BlackPawn, e7);
		addBlack(PieceType.BlackPawn, f7);
		addBlack(PieceType.BlackPawn, g7);
		addBlack(PieceType.BlackPawn, h7);
		addBlack(PieceType.BlackRook, a8);
		addBlack(PieceType.BlackKnight, b8);
		addBlack(PieceType.BlackBishop, c8);
		addBlack(PieceType.BlackQueen, d8);
		addBlack(PieceType.BlackKing, e8);
		addBlack(PieceType.BlackBishop, f8);
		addBlack(PieceType.BlackKnight, g8);
		addBlack(PieceType.BlackRook, h8);
	}
	
	private void addWhite(PieceType type, int field) {
		Piece piece = piece(type, field);
		game.addWhite(piece);
		game.place(piece);
	}
	
	private void addBlack(PieceType type, int field) {
		Piece piece = piece(type, field);
		game.addBlack(piece);
		game.place(piece);
	}

	private Piece piece(PieceType type, int field) {
		Piece piece = new Piece(type, field);
		piece.setMoved(false);
		return piece;
	}
}
