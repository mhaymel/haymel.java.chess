/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

final class MakeWhiteMove {

	static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert game.piece(move.from()).white();
		assert game.piece(move.to()).free();
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		
		game.push(move, moved);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()).free();
		assert game.piece(move.to()).white();
		assert game.assertVerify();
	}

	static void undo(Game game, Move move, boolean moved) {
		assert game.assertVerify();
		assert game.piece(move.to()).white();
		assert game.piece(move.from()).free();

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		piece.setMoved(moved);
		game.place(piece);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(move.to()).free();
		assert game.piece(move.from()).white();
		assert game.assertVerify();
	}

}
