/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	30.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static java.lang.String.format;

import com.haymel.chess.engine.game.black.MakeBlackCaptureKingMove;
import com.haymel.chess.engine.game.black.MakeBlackCaptureMove;
import com.haymel.chess.engine.game.black.MakeBlackCapturePromotionMove;
import com.haymel.chess.engine.game.black.MakeBlackCaptureRookMove;
import com.haymel.chess.engine.game.black.MakeBlackEnpassantMove;
import com.haymel.chess.engine.game.black.MakeBlackKingMove;
import com.haymel.chess.engine.game.black.MakeBlackKingSideCastlingMove;
import com.haymel.chess.engine.game.black.MakeBlackMove;
import com.haymel.chess.engine.game.black.MakeBlackPawnDoubleStepMove;
import com.haymel.chess.engine.game.black.MakeBlackPawnMove;
import com.haymel.chess.engine.game.black.MakeBlackPromotionMove;
import com.haymel.chess.engine.game.black.MakeBlackQueenSideCastlingMove;
import com.haymel.chess.engine.game.black.MakeBlackRookMove;
import com.haymel.chess.engine.game.white.MakeWhiteCaptureKingMove;
import com.haymel.chess.engine.game.white.MakeWhiteCaptureMove;
import com.haymel.chess.engine.game.white.MakeWhiteCapturePromotionMove;
import com.haymel.chess.engine.game.white.MakeWhiteCaptureRookMove;
import com.haymel.chess.engine.game.white.MakeWhiteEnpassantMove;
import com.haymel.chess.engine.game.white.MakeWhiteKingMove;
import com.haymel.chess.engine.game.white.MakeWhiteKingSideCastlingMove;
import com.haymel.chess.engine.game.white.MakeWhiteMove;
import com.haymel.chess.engine.game.white.MakeWhitePawnDoubleStepMove;
import com.haymel.chess.engine.game.white.MakeWhitePawnMove;
import com.haymel.chess.engine.game.white.MakeWhitePromotionMove;
import com.haymel.chess.engine.game.white.MakeWhiteQueenSideCastlingMove;
import com.haymel.chess.engine.game.white.MakeWhiteRookMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeMove {	//TODO unit test

	private final Game game;
	
	public MakeMove(Game game) {
		assert game != null;
		assert game.assertVerify();
		this.game = game;
	}
	
	public void makeMove(Move move) {
		assert move != null;
		assert game.assertVerify();
		assert 
			activeColor() == white && PieceType.white(game.piece(move.from()).type()) || 
			activeColor() == black && PieceType.black(game.piece(move.from()).type())      : "" + activeColor() + " " + move.toString();
		
		switch(activeColor()) {
		case black:
			makeBlackMove(move);
			assert activeColor() == white;
			break;
		case white:
			makeWhiteMove(move);
			assert activeColor() == black;
			break;
		default:
			assert false : "unknown activeColor " + activeColor();
			break;
		}
		
		assert game.assertVerify();
	}

	private ActiveColor activeColor() {
		return game.activeColor();
	}

	public void undoMove() {
		assert game.assertVerify();

		switch(activeColor()) {
		case black:
			undoWhiteMove();
			break;
		case white:
			undoBlackMove();
			break;
		default:
			assert false : "unknown activeColor " + activeColor();
			break;
		}

		assert game.assertVerify();
	}
	
	private void makeWhiteMove(Move move) {
		assert activeColor() == white; 
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
		
		assert activeColor() == black; 
	}

	private void undoWhiteMove() {
		assert activeColor() == black; 
		
		Undo undo = game.pop();
		
		switch(undo.move().type()) {
		case MoveType.normal:
			MakeWhiteMove.undo(game, undo.move());
			break;
		case MoveType.normalRookMove:
			MakeWhiteRookMove.undo(game, undo.move());
			break;
		case MoveType.normalKingMove:
			MakeWhiteKingMove.undo(game, undo.move());
			break;
		case MoveType.pawn:
			MakeWhitePawnMove.undo(game, undo.move());
			break;
		case MoveType.pawnDoubleStep:
			MakeWhitePawnDoubleStepMove.undo(game, undo.move());
			break;
		case MoveType.capture:
			MakeWhiteCaptureMove.undo(game, undo.move());		
			break;
		case MoveType.captureRookMove:
			MakeWhiteCaptureRookMove.undo(game, undo.move());		
			break;
		case MoveType.captureKingMove:
			MakeWhiteCaptureKingMove.undo(game, undo.move());		
			break;
		case MoveType.capturePromotion:
			MakeWhiteCapturePromotionMove.undo(game, undo.move());
			break;
		case MoveType.enpassant:
			MakeWhiteEnpassantMove.undo(game, undo.move());
			break;
		case MoveType.kingsideCastling:
			MakeWhiteKingSideCastlingMove.undo(game, undo.move());
			break;
		case MoveType.queensideCastling:
			MakeWhiteQueenSideCastlingMove.undo(game, undo.move());
			break;
		case MoveType.promotion:
			MakeWhitePromotionMove.undo(game, undo.move());
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor() == white; 
	}
	
	private void makeBlackMove(Move move) {
		assert activeColor() == black; 
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
		case MoveType.capturePromotion:
			MakeBlackCapturePromotionMove.make(game, move);			
			break;
		case MoveType.enpassant:
			MakeBlackEnpassantMove.make(game, move);
			break;
		case MoveType.promotion:
			MakeBlackPromotionMove.make(game, move);		
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

		assert activeColor() == white : format("move %s", move); 
	}

	private void undoBlackMove() {
		assert activeColor() == white; 
		
		Undo undo = game.pop();
		
		switch(undo.move().type()) {
		case MoveType.normal:
			MakeBlackMove.undo(game, undo.move());
			break;
		case MoveType.normalRookMove:
			MakeBlackRookMove.undo(game, undo.move());
			break;
		case MoveType.normalKingMove:
			MakeBlackKingMove.undo(game, undo.move());
			break;
		case MoveType.pawn:
			MakeBlackPawnMove.undo(game, undo.move());
			break;
		case MoveType.pawnDoubleStep:
			MakeBlackPawnDoubleStepMove.undo(game, undo.move());
			break;
		case MoveType.capture:
			MakeBlackCaptureMove.undo(game, undo.move());		
			break;
		case MoveType.captureRookMove:
			MakeBlackCaptureRookMove.undo(game, undo.move());		
			break;
		case MoveType.captureKingMove:
			MakeBlackCaptureKingMove.undo(game, undo.move());		
			break;
		case MoveType.capturePromotion:
			MakeBlackCapturePromotionMove.undo(game, undo.move());			
			break;
		case MoveType.enpassant:
			MakeBlackEnpassantMove.undo(game, undo.move());
			break;
		case MoveType.kingsideCastling:
			MakeBlackKingSideCastlingMove.undo(game, undo.move());
			break;
		case MoveType.queensideCastling:
			MakeBlackQueenSideCastlingMove.undo(game, undo.move());
			break;
		case MoveType.promotion:
			MakeBlackPromotionMove.undo(game, undo.move());			
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor() == black; 
	}

}
