/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.h8;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public final class MakeWhiteCaptureKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
	
		game.pushCastlingRight();
		game.castlingRight().white().disable();
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		MakeWhiteCaptureMove.doMake(game, move);

		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();

		MakeWhiteCaptureMove.doUndo(game, move);
		game.popCastlingRight();
		
		assert game.assertVerify();
	}

}
