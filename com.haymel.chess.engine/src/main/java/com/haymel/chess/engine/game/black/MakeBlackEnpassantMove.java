/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackEnpassantMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).blackPawn();
		assert move.from().rank() == 3;
		assert game.piece(game.enPassant()).free();
		assert game.piece(game.enPassant().up()).white();
		assert game.piece(game.enPassant().up()) == move.capturedPiece();
		assert game.containsWhitePiece(move.capturedPiece());
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.clear(move.capturedPiece().field());
		game.removeWhite(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()).free();
		assert game.piece(move.to()) == piece;
		assert !game.containsWhitePiece(move.capturedPiece());
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.activeColor() == black; 
		assert game.piece(move.to()).blackPawn();
		assert game.piece(move.from()).free();
		assert !game.containsWhitePiece(move.capturedPiece());
		assert game.piece(game.enPassant().up()).free();
		assert game.assertVerify();
		
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		game.addWhite(move.capturedPiece());
		game.place(move.capturedPiece());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).blackPawn();
		assert move.from().rank() == 3;
		assert game.piece(game.enPassant()).free();
		assert game.piece(game.enPassant().up()).whitePawn();
		assert game.piece(game.enPassant().up()) == move.capturedPiece();
		assert game.containsWhitePiece(move.capturedPiece());
		assert game.assertVerify();
	}

}
