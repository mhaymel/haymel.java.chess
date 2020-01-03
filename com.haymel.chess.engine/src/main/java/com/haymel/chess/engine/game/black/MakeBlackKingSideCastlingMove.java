/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackKingSideCastlingMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert move.from() == e8;
		assert move.to() == g8;
		assert game.piece(e8).blackKing();
		assert !game.piece(e8).moved();
		assert game.piece(h8).blackRook();
		assert !game.piece(h8).moved();
		assert game.piece(f8).free();
		assert game.piece(g8).free();

		Piece king = game.piece(e8);
		Piece rook = game.piece(h8);
		
		game.clear(e8);
		king.field(g8);
		king.setMoved(true);
		game.place(king);
		
		game.clear(h8);
		rook.field(f8);
		rook.setMoved(true);
		game.place(rook);
		
		game.push(move, false);
		game.incHalfMoveClock();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(e8).free();
		assert game.piece(h8).free();
		assert game.piece(g8).blackKing();
		assert game.piece(g8).moved();
		assert game.piece(f8).blackRook();
		assert game.piece(f8).moved();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(e8).free();
		assert game.piece(h8).free();
		assert game.piece(g8).blackKing();
		assert game.piece(g8).moved();
		assert game.piece(f8).blackRook();
		assert game.piece(f8).moved();

		Piece king = game.piece(g8);
		Piece rook = game.piece(f8);
		
		game.clear(g8);
		king.field(e8);
		king.setMoved(false);
		game.place(king);
		
		game.clear(f8);
		rook.field(h8);
		rook.setMoved(false);
		game.place(rook);
		
		assert game.activeColor() == black;
		assert move.from() == e8;
		assert move.to() == g8;
		assert game.piece(e8).blackKing();
		assert !game.piece(e8).moved();
		assert game.piece(h8).blackRook();
		assert !game.piece(h8).moved();
		assert game.piece(f8).free();
		assert game.piece(g8).free();
		assert game.assertVerify();
	}

}
