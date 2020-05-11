/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public final class MakeWhiteKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		game.pushCastlingRight();
		game.castlingRight().white().disable();
		MakeWhiteMove.doMake(game, move);

		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();

		MakeWhiteMove.doUndo(game, move);
		game.popCastlingRight();

		assert game.assertVerify();
	}

}
