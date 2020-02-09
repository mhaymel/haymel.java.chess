/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.fen;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.game.Game;

public class FENCreator {		//TODO implement, unit test
	
	private final Game game;
	private final String fen;
	
	public FENCreator(Game game, String fen) {
		this.game = nonNull(game, "game");
		this.fen = nonEmpty(fen, "fen");
	}

	public void execute() {
		game.reset();
		
		
	}
	
	
}
