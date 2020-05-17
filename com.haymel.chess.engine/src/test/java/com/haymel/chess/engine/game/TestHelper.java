/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public interface TestHelper {

	static void undoMove(Game game) {
		new MakeMove(game).undoMove();
	}

	static void makeMove(Move move, Game game) {
		new MakeMove(game).makeMove(move);
	}

	static Game fromFen(String fen) {
		return new GameFromFEN(fen).value();		
	}
	
	static Move find(String move, Game game) {
		return nonNull(new MoveFinder(game.moves()).find(move), "move");
	}
	
}