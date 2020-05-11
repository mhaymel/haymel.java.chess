/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public final class MakeWhiteCaptureRookMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		game.pushCastlingRight();
		switch(move.from()) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
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
