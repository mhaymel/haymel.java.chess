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

import com.haymel.chess.engine.game.Position;

class HalfmoveClock {

	private final Position position;
	private final String halfMoveClock; 
	
	HalfmoveClock(Position position, String halfmoveClock) {
		this.position = nonNull(position, "position");
		this.halfMoveClock = verify(halfmoveClock);
	}
	
	void execute() {
		position.halfMoveClock(parseInt(halfMoveClock));
	}

	private static String verify(String halfMoveClock) {
		nonEmpty(halfMoveClock, "halfMoveClock");

		try {
			parseInt(halfMoveClock);
		}
		catch(NumberFormatException e) {
			throwIAE("halfMoveClock must be an numeric value but is '%s'", halfMoveClock);
		}
		
		return halfMoveClock;
	}
	
}
