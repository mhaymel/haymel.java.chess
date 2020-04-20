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
import static com.haymel.chess.engine.moves.MoveType.capture;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackCaptureMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == capture;
		assert game.piece(move.from()).black();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().white();
		assert game.containsWhitePiece(move.capturedPiece());
		assert !game.piece(move.to()).whiteKing();
		assert game.containsBlackPiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		game.removeWhite(move.capturedPiece());
		game.push(move, moved);
		game.resetHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == piece;
		assert !game.containsWhitePiece(move.capturedPiece());
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move, boolean moved) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert move.type() == capture;
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).black();
		assert move.capturedPiece().white();
		assert !game.containsWhitePiece(move.capturedPiece());

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		game.place(piece);

		game.addWhite(move.capturedPiece());
		game.place(move.capturedPiece());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black;
		assert game.piece(move.from()).black();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().white();
		assert game.containsWhitePiece(move.capturedPiece());
		assert !game.piece(move.to()).whiteKing();
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

}
