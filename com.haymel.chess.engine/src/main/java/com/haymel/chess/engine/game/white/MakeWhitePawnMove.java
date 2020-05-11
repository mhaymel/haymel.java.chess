/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhitePawnMove {		//TODO unit test

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == pawn;
		assert game.piece(move.from()).whitePawn();
		assert game.piece(move.to()) == null;
		assert rank(move.from()) != 0;
		assert rank(move.to()) != 7;
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		
		game.push(move);
		
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(move.to()).white();
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).white();
		assert game.assertVerify();
	}

}
