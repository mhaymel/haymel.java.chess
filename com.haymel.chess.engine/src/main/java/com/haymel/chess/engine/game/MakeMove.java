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

import com.haymel.chess.engine.game.black.BlackMakeMove;
import com.haymel.chess.engine.game.white.WhiteMakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeMove {	//TODO unit test

	private final Game game;
	
	public MakeMove(Game game) {
		assert game != null;
		assert game.assertVerify();
		this.game = game;
	}
	
	public void makeMove(int move) {
		assert Move.validMove(move);
		assert game.assertVerify();
		assert 
			activeColor() == white && PieceType.white(game.piece(Move.from(move)).type()) || 
			activeColor() == black && PieceType.black(game.piece(Move.from(move)).type())      : "" + activeColor() + " " + Move.asString(move);
		
		switch(activeColor()) {
		case black:
			BlackMakeMove.makeMove(game, move);
			assert activeColor() == white;
			break;
		case white:
			WhiteMakeMove.makeMove(game, move);
			assert activeColor() == black;
			break;
		default:
			assert false : "unknown activeColor " + activeColor();
			break;
		}
		
		assert game.assertVerify();
	}

	private int activeColor() {
		return game.activeColor();
	}

	public void undoMove() {
		assert game.assertVerify();

		switch(activeColor()) {
		case black:
			WhiteMakeMove.undoMove(game);
			break;
		case white:
			BlackMakeMove.undoMove(game);
			break;
		default:
			assert false : "unknown activeColor " + activeColor();
			break;
		}

		assert game.assertVerify();
	}

}
