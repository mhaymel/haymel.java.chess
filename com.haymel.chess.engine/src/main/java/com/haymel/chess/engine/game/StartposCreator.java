/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
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
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class StartposCreator {		//TODO unit test
	
	public final Game game;
	
	public StartposCreator(Game game) {
		assert game != null;
		this.game = game;
	}
	
	public void execute() {
		resetBoard();
		placeWhitePieces();
		placeBlackPieces();
	}

	private void resetBoard() {
		game.reset();
	}

	private void placeWhitePieces() {
		place(a2, WhitePawn);
		place(b2, WhitePawn);
		place(c2, WhitePawn);
		place(d2, WhitePawn);
		place(e2, WhitePawn);
		place(f2, WhitePawn);
		place(g2, WhitePawn);
		place(h2, WhitePawn);
		
		place(a1, WhiteRook).setMoved(false);
		place(b1, WhiteKnight);
		place(c1, WhiteBishop);
		place(d1, WhiteQueen);
		place(e1, WhiteKing).setMoved(false);
		place(f1, WhiteBishop);
		place(g1, WhiteKnight);
		place(h1, WhiteRook).setMoved(false);
	}

	private void placeBlackPieces() {
		place(a7, BlackPawn);
		place(b7, BlackPawn);
		place(c7, BlackPawn);
		place(d7, BlackPawn);
		place(e7, BlackPawn);
		place(f7, BlackPawn);
		place(g7, BlackPawn);
		place(h7, BlackPawn);

		place(a8, BlackRook).setMoved(false);
		place(b8, BlackKnight);
		place(c8, BlackBishop);
		place(d8, BlackQueen);
		place(e8, BlackKing).setMoved(false);
		place(f8, BlackBishop);
		place(g8, BlackKnight);
		place(h8, BlackRook).setMoved(false);
	}

	private Piece place(Field field, PieceType type) {
		Piece piece = new Piece(type, field);
		if (piece.white())
			game.addWhite(piece);
		else if (piece.black())
			game.addBlack(piece);
		else
			assert false : piece.toString();
			
		game.place(piece);
		return piece;
	}
		
}
