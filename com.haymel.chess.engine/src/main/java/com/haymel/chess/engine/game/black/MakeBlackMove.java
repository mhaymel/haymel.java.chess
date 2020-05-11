/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.moves.MoveType.normalRookMove;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		doMake(game, move);
		
		assert game.assertVerify();
	}
	
	static void doMake(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.activeColor() == black; 
		assert move.type() == normal || move.type() == normalKingMove || move.type() == normalRookMove;
		assert game.piece(move.from()).black();
		assert !game.piece(move.from()).blackPawn();
		assert game.piece(move.to()) == null;
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.push(move);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).black();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();

		doUndo(game, move);
		
		assert game.assertVerify();
	}
	
	static void doUndo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.activeColor() == black; 
		assert move.type() == normal || move.type() == normalKingMove || move.type() == normalRookMove;
		assert game.piece(move.to()).black();
		assert !game.piece(move.to()).blackPawn();
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).black();
	}

}
