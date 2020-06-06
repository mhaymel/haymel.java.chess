/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	19.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static java.lang.String.format;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackMakeMove {	//TODO unit test

	public static void makeMove(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert PieceType.black(game.piece(move.from()).type());
		
		switch(move.type()) {
		case MoveType.normal:
			MakeBlackMove.make(game, move);
			break;
		case MoveType.normalRookMove:
			MakeBlackRookMove.make(game, move);
			break;
		case MoveType.normalKingMove:
			MakeBlackKingMove.make(game, move);
			break;
		case MoveType.pawn:
			MakeBlackPawnMove.make(game, move);
			break;
		case MoveType.pawnDoubleStep:
			MakeBlackPawnDoubleStepMove.make(game, move);
			break;
		case MoveType.capture:
			MakeBlackCaptureMove.make(game, move);		
			break;
		case MoveType.captureRookMove:
			MakeBlackCaptureRookMove.make(game, move);		
			break;
		case MoveType.captureKingMove:
			MakeBlackCaptureKingMove.make(game, move);		
			break;
		case MoveType.capturePromotionQueen:
			MakeBlackCapturePromotionMove.make(game, move, BlackQueen);			
			break;
		case MoveType.capturePromotionRook:
			MakeBlackCapturePromotionMove.make(game, move, BlackRook);			
			break;
		case MoveType.capturePromotionBishop:
			MakeBlackCapturePromotionMove.make(game, move, BlackBishop);			
			break;
		case MoveType.capturePromotionKnight:
			MakeBlackCapturePromotionMove.make(game, move, BlackKnight);			
			break;
		case MoveType.enpassant:
			MakeBlackEnpassantMove.make(game, move);
			break;
		case MoveType.promotionQueen:
			MakeBlackPromotionMove.make(game, move, BlackQueen);		
			break;
		case MoveType.promotionRook:
			MakeBlackPromotionMove.make(game, move, BlackRook);		
			break;
		case MoveType.promotionBishop:
			MakeBlackPromotionMove.make(game, move, BlackBishop);		
			break;
		case MoveType.promotionKnight:
			MakeBlackPromotionMove.make(game, move, BlackKnight);		
			break;
		case MoveType.kingsideCastling:
			MakeBlackKingSideCastlingMove.make(game, move);
			break;
		case MoveType.queensideCastling:
			MakeBlackQueenSideCastlingMove.make(game, move);
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}

		assert game.activeColor() == white : format("move %s", move);
		assert game.assertVerify();
	}

	public static void undoMove(Game game) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		
		Move move = game.pop();
		
		switch(move.type()) {
		case MoveType.normal:
			MakeBlackMove.undo(game, move);
			break;
		case MoveType.normalRookMove:
			MakeBlackRookMove.undo(game, move);
			break;
		case MoveType.normalKingMove:
			MakeBlackKingMove.undo(game, move);
			break;
		case MoveType.pawn:
			MakeBlackPawnMove.undo(game, move);
			break;
		case MoveType.pawnDoubleStep:
			MakeBlackPawnDoubleStepMove.undo(game, move);
			break;
		case MoveType.capture:
			MakeBlackCaptureMove.undo(game, move);		
			break;
		case MoveType.captureRookMove:
			MakeBlackCaptureRookMove.undo(game, move);		
			break;
		case MoveType.captureKingMove:
			MakeBlackCaptureKingMove.undo(game, move);		
			break;
		case MoveType.capturePromotionQueen:
		case MoveType.capturePromotionRook:
		case MoveType.capturePromotionBishop:
		case MoveType.capturePromotionKnight:
			MakeBlackCapturePromotionMove.undo(game, move);			
			break;
		case MoveType.enpassant:
			MakeBlackEnpassantMove.undo(game, move);
			break;
		case MoveType.kingsideCastling:
			MakeBlackKingSideCastlingMove.undo(game, move);
			break;
		case MoveType.queensideCastling:
			MakeBlackQueenSideCastlingMove.undo(game, move);
			break;
		case MoveType.promotionQueen:
		case MoveType.promotionRook:
		case MoveType.promotionBishop:
		case MoveType.promotionKnight:
			MakeBlackPromotionMove.undo(game, move);			
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}
		
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}

}
