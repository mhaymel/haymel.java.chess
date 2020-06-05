/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	19.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.PieceType;

public final class WhiteMakeMove {	//TODO unit test

	public static void makeMove(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert PieceType.white(game.piece(move.from()).type());
		
		switch(move.type()) {
		case MoveType.normal:
			MakeWhiteMove.make(game, move);
			break;
		case MoveType.normalRookMove:
			MakeWhiteRookMove.make(game, move);
			break;
		case MoveType.normalKingMove:
			MakeWhiteKingMove.make(game, move);
			break;
		case MoveType.pawn:
			MakeWhitePawnMove.make(game, move);
			break;
		case MoveType.pawnDoubleStep:
			MakeWhitePawnDoubleStepMove.make(game, move);
			break;
		case MoveType.capture:
			MakeWhiteCaptureMove.make(game, move);		
			break;
		case MoveType.captureRookMove:
			MakeWhiteCaptureRookMove.make(game, move);		
			break;
		case MoveType.captureKingMove:
			MakeWhiteCaptureKingMove.make(game, move);		
			break;
		case MoveType.capturePromotion:
			MakeWhiteCapturePromotionMove.make(game, move);		
			break;
		case MoveType.enpassant:
			MakeWhiteEnpassantMove.make(game, move);
			break;
		case MoveType.kingsideCastling:
			MakeWhiteKingSideCastlingMove.make(game, move);
			break;
		case MoveType.queensideCastling:
			MakeWhiteQueenSideCastlingMove.make(game, move);
			break;
		case MoveType.promotion:
			MakeWhitePromotionMove.make(game, move);
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}
		
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}

	public static void undoMove(Game game) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		
		Move move = game.pop();
		
		switch(move.type()) {
		case MoveType.normal:
			MakeWhiteMove.undo(game, move);
			break;
		case MoveType.normalRookMove:
			MakeWhiteRookMove.undo(game, move);
			break;
		case MoveType.normalKingMove:
			MakeWhiteKingMove.undo(game, move);
			break;
		case MoveType.pawn:
			MakeWhitePawnMove.undo(game, move);
			break;
		case MoveType.pawnDoubleStep:
			MakeWhitePawnDoubleStepMove.undo(game, move);
			break;
		case MoveType.capture:
			MakeWhiteCaptureMove.undo(game, move);		
			break;
		case MoveType.captureRookMove:
			MakeWhiteCaptureRookMove.undo(game, move);		
			break;
		case MoveType.captureKingMove:
			MakeWhiteCaptureKingMove.undo(game, move);		
			break;
		case MoveType.capturePromotion:
			MakeWhiteCapturePromotionMove.undo(game, move);
			break;
		case MoveType.enpassant:
			MakeWhiteEnpassantMove.undo(game, move);
			break;
		case MoveType.kingsideCastling:
			MakeWhiteKingSideCastlingMove.undo(game, move);
			break;
		case MoveType.queensideCastling:
			MakeWhiteQueenSideCastlingMove.undo(game, move);
			break;
		case MoveType.promotion:
			MakeWhitePromotionMove.undo(game, move);
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}
		
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
}
