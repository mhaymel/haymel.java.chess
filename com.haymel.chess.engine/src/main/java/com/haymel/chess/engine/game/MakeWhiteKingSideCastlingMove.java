/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

final class MakeWhiteKingSideCastlingMove {

	static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert move.from() == e1;
		assert move.to() == g1;
		assert game.piece(e1).whiteKing();
		assert !game.piece(e1).moved();
		assert game.piece(h1).whiteRook();
		assert !game.piece(h1).moved();
		assert game.piece(f1).free();
		assert game.piece(g1).free();

		Piece king = game.piece(e1);
		Piece rook = game.piece(h1);
		
		game.clear(e1);
		king.field(g1);
		king.setMoved(true);
		game.place(king);
		
		game.clear(h1);
		rook.field(f1);
		rook.setMoved(true);
		game.place(rook);
		
		game.push(move, false);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(e1).free();
		assert game.piece(h1).free();
		assert game.piece(g1).whiteKing();
		assert game.piece(g1).moved();
		assert game.piece(f1).whiteRook();
		assert game.piece(f1).moved();
		assert game.assertVerify();
	}

	static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.piece(e1).free();
		assert game.piece(h1).free();
		assert game.piece(g1).whiteKing();
		assert game.piece(f1).whiteRook();

		Piece king = game.piece(g1);
		Piece rook = game.piece(f1);
		
		game.clear(g1);
		king.field(e1);
		king.setMoved(false);
		game.place(king);
		
		game.clear(f1);
		rook.field(h1);
		rook.setMoved(false);
		game.place(rook);
		
		assert game.activeColor() == white;
		assert move.from() == e1;
		assert move.to() == g1;
		assert game.piece(e1).whiteKing();
		assert !game.piece(e1).moved();
		assert game.piece(h1).whiteRook();
		assert !game.piece(h1).moved();
		assert game.piece(f1).free();
		assert game.piece(g1).free();
		assert game.assertVerify();
	}

}
