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
			activeColor() == white && game.piece(move.from()).white() || 
			activeColor() == black && game.piece(move.from()).black()      : "" + activeColor() + " " + move.toString();
		
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
		assert game.piece(move.from()).white();
		
		switch(move.type()) {
		case normal:
			MakeWhiteMove.make(game, move);
			break;
		case normalRookMove:
			MakeWhiteRookMove.make(game, move);
			break;
		case normalKingMove:
			MakeWhiteKingMove.make(game, move);
			break;
		case pawn:
			MakeWhitePawnMove.make(game, move);
			break;
		case pawnDoubleStep:
			MakeWhitePawnDoubleStepMove.make(game, move);
			break;
		case capture:
			MakeWhiteCaptureMove.make(game, move);		
			break;
		case captureRookMove:
			MakeWhiteCaptureRookMove.make(game, move);		
			break;
		case captureKingMove:
			MakeWhiteCaptureKingMove.make(game, move);		
			break;
		case capturePromotion:
			MakeWhiteCapturePromotionMove.make(game, move);		
			break;
		case enpassant:
			MakeWhiteEnpassantMove.make(game, move);
			break;
		case kingsideCastling:
			MakeWhiteKingSideCastlingMove.make(game, move);
			break;
		case queensideCastling:
			MakeWhiteQueenSideCastlingMove.make(game, move);
			break;
		case promotion:
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
		case normal:
			MakeWhiteMove.undo(game, undo.move());
			break;
		case normalRookMove:
			MakeWhiteRookMove.undo(game, undo.move());
			break;
		case normalKingMove:
			MakeWhiteKingMove.undo(game, undo.move());
			break;
		case pawn:
			MakeWhitePawnMove.undo(game, undo.move());
			break;
		case pawnDoubleStep:
			MakeWhitePawnDoubleStepMove.undo(game, undo.move());
			break;
		case capture:
			MakeWhiteCaptureMove.undo(game, undo.move());		
			break;
		case captureRookMove:
			MakeWhiteCaptureRookMove.undo(game, undo.move());		
			break;
		case captureKingMove:
			MakeWhiteCaptureKingMove.undo(game, undo.move());		
			break;
		case capturePromotion:
			MakeWhiteCapturePromotionMove.undo(game, undo.move());
			break;
		case enpassant:
			MakeWhiteEnpassantMove.undo(game, undo.move());
			break;
		case kingsideCastling:
			MakeWhiteKingSideCastlingMove.undo(game, undo.move());
			break;
		case queensideCastling:
			MakeWhiteQueenSideCastlingMove.undo(game, undo.move());
			break;
		case promotion:
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
		assert game.piece(move.from()).black();
		
		switch(move.type()) {
		case normal:
			MakeBlackMove.make(game, move);
			break;
		case normalRookMove:
			MakeBlackRookMove.make(game, move);
			break;
		case normalKingMove:
			MakeBlackKingMove.make(game, move);
			break;
		case pawn:
			MakeBlackPawnMove.make(game, move);
			break;
		case pawnDoubleStep:
			MakeBlackPawnDoubleStepMove.make(game, move);
			break;
		case capture:
			MakeBlackCaptureMove.make(game, move);		
			break;
		case captureRookMove:
			MakeBlackCaptureRookMove.make(game, move);		
			break;
		case captureKingMove:
			MakeBlackCaptureKingMove.make(game, move);		
			break;
		case capturePromotion:
			MakeBlackCapturePromotionMove.make(game, move);			
			break;
		case enpassant:
			MakeBlackEnpassantMove.make(game, move);
			break;
		case promotion:
			MakeBlackPromotionMove.make(game, move);		
			break;
		case kingsideCastling:
			MakeBlackKingSideCastlingMove.make(game, move);
			break;
		case queensideCastling:
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
		case normal:
			MakeBlackMove.undo(game, undo.move());
			break;
		case normalRookMove:
			MakeBlackRookMove.undo(game, undo.move());
			break;
		case normalKingMove:
			MakeBlackKingMove.undo(game, undo.move());
			break;
		case pawn:
			MakeBlackPawnMove.undo(game, undo.move());
			break;
		case pawnDoubleStep:
			MakeBlackPawnDoubleStepMove.undo(game, undo.move());
			break;
		case capture:
			MakeBlackCaptureMove.undo(game, undo.move());		
			break;
		case captureRookMove:
			MakeBlackCaptureRookMove.undo(game, undo.move());		
			break;
		case captureKingMove:
			MakeBlackCaptureKingMove.undo(game, undo.move());		
			break;
		case capturePromotion:
			MakeBlackCapturePromotionMove.undo(game, undo.move());			
			break;
		case enpassant:
			MakeBlackEnpassantMove.undo(game, undo.move());
			break;
		case kingsideCastling:
			MakeBlackKingSideCastlingMove.undo(game, undo.move());
			break;
		case queensideCastling:
			MakeBlackQueenSideCastlingMove.undo(game, undo.move());
			break;
		case promotion:
			MakeBlackPromotionMove.undo(game, undo.move());			
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor() == black; 
	}

}
