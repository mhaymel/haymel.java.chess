/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

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

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.pieces.Piece;
import com.haymel.chess.engine.pieces.black.BlackBishop;
import com.haymel.chess.engine.pieces.black.BlackKing;
import com.haymel.chess.engine.pieces.black.BlackKnight;
import com.haymel.chess.engine.pieces.black.BlackPawn;
import com.haymel.chess.engine.pieces.black.BlackQueen;
import com.haymel.chess.engine.pieces.black.BlackRook;
import com.haymel.chess.engine.pieces.white.WhiteBishop;
import com.haymel.chess.engine.pieces.white.WhiteKing;
import com.haymel.chess.engine.pieces.white.WhiteKnight;
import com.haymel.chess.engine.pieces.white.WhitePawn;
import com.haymel.chess.engine.pieces.white.WhiteQueen;
import com.haymel.chess.engine.pieces.white.WhiteRook;

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
		place(a2, new WhitePawn());
		place(b2, new WhitePawn());
		place(c2, new WhitePawn());
		place(d2, new WhitePawn());
		place(e2, new WhitePawn());
		place(f2, new WhitePawn());
		place(g2, new WhitePawn());
		place(h2, new WhitePawn());
		
		placeRook(a1, new WhiteRook());
		place(b1, new WhiteKnight());
		place(c1, new WhiteBishop());
		place(d1, new WhiteQueen());
		placeKing(e1, new WhiteKing());
		place(f1, new WhiteBishop());
		place(g1, new WhiteKnight());
		placeRook(h1, new WhiteRook());
	}

	private void placeBlackPieces() {
		place(a7, new BlackPawn());
		place(b7, new BlackPawn());
		place(c7, new BlackPawn());
		place(d7, new BlackPawn());
		place(e7, new BlackPawn());
		place(f7, new BlackPawn());
		place(g7, new BlackPawn());
		place(h7, new BlackPawn());

		placeRook(a8, new BlackRook());
		place(b8, new BlackKnight());
		place(c8, new BlackBishop());
		place(d8, new BlackQueen());
		placeKing(e8, new BlackKing());
		place(f8, new BlackBishop());
		place(g8, new BlackKnight());
		placeRook(h8, new BlackRook());
	}


	private void placeRook(Field f, WhiteRook rook) {
		place(f, rook);
		rook.setMoved(false);
	}

	private void placeKing(Field f, WhiteKing king) {
		place(f, king);
		king.setMoved(false);
	}

	private void placeRook(Field f, BlackRook rook) {
		place(f, rook);
		rook.setMoved(false);
	}
	
	private void placeKing(Field f, BlackKing king) {
		place(f, king);
		king.setMoved(false);
	}

	private void place(Field f, Piece piece) {
		piece.field(f);
		board.place(piece);
	}
		
}
