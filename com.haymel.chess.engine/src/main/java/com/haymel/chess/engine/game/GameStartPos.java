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

import com.haymel.chess.engine.board.Field;
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
		add(WhitePawn, a2);
		add(WhitePawn, b2);
		add(WhitePawn, c2);
		add(WhitePawn, d2);
		add(WhitePawn, e2);
		add(WhitePawn, f2);
		add(WhitePawn, g2);
		add(WhitePawn, h2);
		add(WhiteRook, a1);
		add(WhiteKnight, b1);
		add(WhiteBishop, c1);
		add(WhiteQueen, d1);
		add(WhiteKing, e1);
		add(WhiteBishop, f1);
		add(WhiteKnight, g1);
		add(WhiteRook, h1);
	}

	private void addBlackPieces() {
		add(PieceType.BlackPawn, a7);
		add(PieceType.BlackPawn, b7);
		add(PieceType.BlackPawn, c7);
		add(PieceType.BlackPawn, d7);
		add(PieceType.BlackPawn, e7);
		add(PieceType.BlackPawn, f7);
		add(PieceType.BlackPawn, g7);
		add(PieceType.BlackPawn, h7);
		add(PieceType.BlackRook, a8);
		add(PieceType.BlackKnight, b8);
		add(PieceType.BlackBishop, c8);
		add(PieceType.BlackQueen, d8);
		add(PieceType.BlackKing, e8);
		add(PieceType.BlackBishop, f8);
		add(PieceType.BlackKnight, g8);
		add(PieceType.BlackRook, h8);
	}
	
	private void add(PieceType type, Field field) {
		Piece piece = new Piece(type);
		piece.setMoved(false);
		piece.field(field);
		game.addWhite(piece);
		game.place(piece);
	}
	
}
