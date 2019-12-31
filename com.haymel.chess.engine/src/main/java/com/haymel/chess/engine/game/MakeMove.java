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
			activeColor() == black && game.piece(move.from()).black();
		
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
		case pawnDoubleStep:
			break;
		case capture:
			MakeWhiteCaptureMove.make(game, move);		
			break;
		case capturePromotion:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			MakeWhiteKingSideCastlingMove.make(game, move);
			break;
		case queensideCastling:
			MakeWhiteQueenSideCastlingMove.make(game, move);
			break;
		case promotion:
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
			MakeWhiteMove.undo(game, undo.move(), undo.moved());
			break;
		case pawnDoubleStep:
			break;
		case capture:
			MakeWhiteCaptureMove.undo(game, undo.move(), undo.moved());		
			break;
		case capturePromotion:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			MakeWhiteKingSideCastlingMove.undo(game, undo.move());
			break;
		case queensideCastling:
			MakeWhiteQueenSideCastlingMove.undo(game, undo.move());
			break;
		case promotion:
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
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotion:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case promotion:
			break;
		case queensideCastling:
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}

		assert activeColor() == white; 
	}

	private void undoBlackMove() {
		assert activeColor() == white; 
		
		Undo undo = game.pop();
		
		switch(undo.move().type()) {
		case normal:
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotion:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case queensideCastling:
			break;
		case promotion:
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor() == black; 
	}

}
