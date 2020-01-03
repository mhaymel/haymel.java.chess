/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteCaptureMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == capture;
		assert game.piece(move.from()).white();
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().black();
		assert game.containsBlackPiece(move.capturedPiece());
		assert !game.piece(move.to()).blackKing();
		assert game.containsWhitePiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		game.removeBlack(move.capturedPiece());
		game.push(move, moved);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()).free();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == piece;
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move, boolean moved) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert move.type() == capture;
		assert game.piece(move.from()).free();
		assert game.piece(move.to()).white();
		assert move.capturedPiece().black();
		assert !game.containsBlackPiece(move.capturedPiece());

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		game.place(piece);

		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white;
		assert game.piece(move.from()).white();
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().black();
		assert game.containsBlackPiece(move.capturedPiece());
		assert !game.piece(move.to()).blackKing();
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

}
