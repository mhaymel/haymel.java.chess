/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

final class MakeWhiteEnpassantMove {

	static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).whitePawn();
		assert move.from().rank() == 4;
		assert game.piece(game.enPassant()).free();
		assert game.piece(game.enPassant().down()).blackPawn();
		assert game.piece(game.enPassant().down()) == move.capturedPiece();
		assert game.containsBlackPiece(move.capturedPiece());
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.clear(move.capturedPiece().field());
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()).free();
		assert game.piece(move.to()) == piece;
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.activeColor() == white; 
		assert game.piece(move.to()).whitePawn();
		assert game.piece(move.from()).free();
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.piece(game.enPassant().down()).free();
		assert game.assertVerify();
		
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).whitePawn();
		assert move.from().rank() == 4;
		assert game.piece(game.enPassant()).free();
		assert game.piece(game.enPassant().down()).blackPawn();
		assert game.piece(game.enPassant().down()) == move.capturedPiece();
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.assertVerify();
	}

}
