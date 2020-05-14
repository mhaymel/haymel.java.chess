/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteCaptureMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		game.pushCastlingRight();
		doMake(game, move);
		
		assert game.assertVerify();
	}
	
	static void doMake(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.activeColor() == white; 
		assert move.type() == capture || move.type() == captureKingMove || move.type() == captureRookMove;
		assert game.piece(move.from()).white();
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().black();
		assert game.containsBlackPiece(move.capturedPiece());
		assert !game.piece(move.to()).blackKing();
		assert game.containsWhitePiece(game.piece(move.from()));
		
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == piece;
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.containsWhitePiece(piece);
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();

		doUndo(game, move);
		game.popCastlingRight();
		
		assert game.assertVerify();
	}
	
	static void doUndo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert move.type() == capture || move.type() == captureKingMove || move.type() == captureRookMove;
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
		assert move.capturedPiece().black();
		assert !game.containsBlackPiece(move.capturedPiece());

		Piece piece = game.piece(move.to());
		piece.field(move.from());
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
	}

}
