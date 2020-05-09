/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal;
		assert game.piece(move.from()).black();
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		
		game.push(move, moved);
		
		if (piece.blackPawn() && move.from() - move.to() == Field.up*2)
			game.enPassant(down(move.from()));

		if (piece.blackPawn() && enpassant(move))
			game.clear(move.capturedPiece().field());
		
		if (move.capturedPiece() != null)
			game.removeWhite(move.capturedPiece());	
		
		if (piece.blackPawn() || move.capturedPiece() != null)
			game.resetHalfMoveClock();
		else
			game.incHalfMoveClock();
		
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == piece;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move, boolean moved) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal;
		assert game.piece(move.to()).black();
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		game.place(piece);
	
		if (move.capturedPiece() != null) {
			game.addWhite(move.capturedPiece());
			game.place(move.capturedPiece());
		}
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert game.piece(move.from()).black();
		assert game.assertVerify();
	}
	
	private static boolean enpassant(Move move) {
		return move.capturedPiece() != null && up(move.to()) == move.capturedPiece().field(); 
	}

}
