/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public final class MakeBlackKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		game.pushCastlingRight();
		game.castlingRight().black().disable();
		MakeBlackMove.doMake(game, move);

		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();

		MakeBlackMove.doUndo(game, move);
		game.popCastlingRight();

		assert game.assertVerify();
	}

}
