/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

final class MakeWhiteQueenSideCastlingMove {

	static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert move.from() == e1;
		assert move.to() == c1;
		assert game.piece(e1).whiteKing();
		assert !game.piece(e1).moved();
		assert game.piece(a1).whiteRook();
		assert !game.piece(a1).moved();
		assert game.piece(b1).free();
		assert game.piece(c1).free();
		assert game.piece(d1).free();

		Piece king = game.piece(e1);
		Piece rook = game.piece(a1);
		
		game.clear(e1);
		king.field(c1);
		king.setMoved(true);
		game.place(king);
		
		game.clear(a1);
		rook.field(d1);
		rook.setMoved(true);
		game.place(rook);
		
		game.push(move, false);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(e1).free();
		assert game.piece(a1).free();
		assert game.piece(c1).whiteKing();
		assert game.piece(c1).moved();
		assert game.piece(d1).whiteRook();
		assert game.piece(d1).moved();
		assert game.assertVerify();
	}

	static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(e1).free();
		assert game.piece(a1).free();
		assert game.piece(c1).whiteKing();
		assert game.piece(c1).moved();
		assert game.piece(d1).whiteRook();
		assert game.piece(d1).moved();

		Piece king = game.piece(c1);
		Piece rook = game.piece(d1);
		
		game.clear(c1);
		king.field(e1);
		king.setMoved(false);
		game.place(king);
		
		game.clear(d1);
		rook.field(a1);
		rook.setMoved(false);
		game.place(rook);
		
		assert game.activeColor() == white;
		assert game.piece(e1).whiteKing();
		assert !game.piece(e1).moved();
		assert game.piece(a1).whiteRook();
		assert !game.piece(a1).moved();
		assert game.piece(b1).free();
		assert game.piece(c1).free();
		assert game.piece(d1).free();
		assert game.assertVerify();
	}

}
