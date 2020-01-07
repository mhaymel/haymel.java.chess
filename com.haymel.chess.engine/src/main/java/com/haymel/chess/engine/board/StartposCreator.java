/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

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

import com.haymel.chess.engine.piece.Piece;

public class StartposCreator {		//TODO unit test
	
	public final Board board;
	
	public StartposCreator(Board board) {
		assert board != null;
		this.board = board;
	}
	
	public void execute() {
		clearBoard();
		placeWhitePieces();
		placeBlackPieces();
	}

	private void clearBoard() {
		board.reset();
	}

	private void placeWhitePieces() {
		place(a2, new Piece(WhitePawn));
		place(b2, new Piece(WhitePawn));
		place(c2, new Piece(WhitePawn));
		place(d2, new Piece(WhitePawn));
		place(e2, new Piece(WhitePawn));
		place(f2, new Piece(WhitePawn));
		place(g2, new Piece(WhitePawn));
		place(h2, new Piece(WhitePawn));
		
		place(a1, new Piece(WhiteRook)).setMoved(false);
		place(b1, new Piece(WhiteKnight));
		place(c1, new Piece(WhiteBishop));
		place(d1, new Piece(WhiteQueen));
		place(e1, new Piece(WhiteKing)).setMoved(false);
		place(f1, new Piece(WhiteBishop));
		place(g1, new Piece(WhiteKnight));
		place(h1, new Piece(WhiteRook)).setMoved(false);
	}

	private void placeBlackPieces() {
		place(a7, new Piece(BlackPawn));
		place(b7, new Piece(BlackPawn));
		place(c7, new Piece(BlackPawn));
		place(d7, new Piece(BlackPawn));
		place(e7, new Piece(BlackPawn));
		place(f7, new Piece(BlackPawn));
		place(g7, new Piece(BlackPawn));
		place(h7, new Piece(BlackPawn));

		place(a8, new Piece(BlackRook)).setMoved(false);
		place(b8, new Piece(BlackKnight));
		place(c8, new Piece(BlackBishop));
		place(d8, new Piece(BlackQueen));
		place(e8, new Piece(BlackKing)).setMoved(false);
		place(f8, new Piece(BlackBishop));
		place(g8, new Piece(BlackKnight));
		place(h8, new Piece(BlackRook)).setMoved(false);
	}

	private Piece place(Field f, Piece piece) {
		piece.field(f);
		board.place(piece);
		return piece;
	}
		
}
