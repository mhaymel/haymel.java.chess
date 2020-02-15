/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;
import static java.lang.Integer.parseInt;

import com.haymel.chess.engine.game.Game;

class HalfmoveClock {

	private final Game game;
	private final String halfmoveClock; 
	
	HalfmoveClock(Game game, String halfmoveClock) {
		this.game = nonNull(game, "game");
		this.halfmoveClock = verify(halfmoveClock);
	}
	
	void execute() {
		game.halfMoveClock(parseInt(halfmoveClock));
	}

	private static String verify(String halfmoveClock) {
		nonEmpty(halfmoveClock, "halfmoveClock");

		try {
			parseInt(halfmoveClock);
		}
		catch(NumberFormatException e) {
			throwIAE("halfmoveClock must be an numeric value but is '%s'", halfmoveClock);
		}
		
		return halfmoveClock;
	}
	
}
