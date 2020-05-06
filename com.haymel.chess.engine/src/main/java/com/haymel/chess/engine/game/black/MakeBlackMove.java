/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal || move.type() == pawnDoubleStep;
		assert game.piece(move.from()).black();
		assert game.piece(move.to()) == null;
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		
		game.push(move, moved);
		
		if (move.type() == pawnDoubleStep)
			game.enPassant(down(move.from()));

		if (piece.blackPawn())
			game.resetHalfMoveClock();
		else
			game.incHalfMoveClock();
		
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).black();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move, boolean moved) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal || move.type() == pawnDoubleStep;
		assert game.piece(move.to()).black();
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		game.place(piece);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).black();
		assert game.assertVerify();
	}

}
