/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackQueenSideCastlingMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert move.from() == e8;
		assert move.to() == c8;
		assert game.piece(e8).blackKing();
		assert !game.piece(e8).moved();
		assert game.piece(a8).blackRook();
		assert !game.piece(a8).moved();
		assert game.piece(b8) == null;
		assert game.piece(c8) == null;
		assert game.piece(d8) == null;

		Piece king = game.piece(e8);
		Piece rook = game.piece(a8);
		
		game.clear(e8);
		king.field(c8);
		king.setMoved(true);
		game.place(king);
		
		game.clear(a8);
		rook.field(d8);
		rook.setMoved(true);
		game.place(rook);
		
		game.push(move, false);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(e8) == null;
		assert game.piece(a8) == null;
		assert game.piece(c8).blackKing();
		assert game.piece(c8).moved();
		assert game.piece(d8).blackRook();
		assert game.piece(d8).moved();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(e8) == null;
		assert game.piece(a8) == null;
		assert game.piece(c8).blackKing();
		assert game.piece(c8).moved();
		assert game.piece(d8).blackRook();
		assert game.piece(d8).moved();

		Piece king = game.piece(c8);
		Piece rook = game.piece(d8);
		
		game.clear(c8);
		king.field(e8);
		king.setMoved(false);
		game.place(king);
		
		game.clear(d8);
		rook.field(a8);
		rook.setMoved(false);
		game.place(rook);
		
		assert game.activeColor() == black;
		assert game.piece(e8).blackKing();
		assert !game.piece(e8).moved();
		assert game.piece(a8).blackRook();
		assert !game.piece(a8).moved();
		assert game.piece(b8) == null;
		assert game.piece(c8) == null;
		assert game.piece(d8) == null;
		assert game.assertVerify();
	}

}
