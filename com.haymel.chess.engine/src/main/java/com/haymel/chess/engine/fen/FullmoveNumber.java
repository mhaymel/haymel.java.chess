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

class FullmoveNumber {

	private final Game game;
	private final String fullmoveNumber; 
	
	FullmoveNumber(Game game, String fullmoveNumber) {
		this.game = nonNull(game, "game");
		this.fullmoveNumber = verify(fullmoveNumber);
	}
	
	void execute() {
		game.fullMoveNumber(parseInt(fullmoveNumber));
	}

	private static String verify(String fullmoveNumber) {
		nonEmpty(fullmoveNumber, "fullmoveNumber");

		try {
			if (parseInt(fullmoveNumber) < 1)
				throwIAE("fullmoveNumber must be greater than 0 but is '%s'", fullmoveNumber); 
		}
		catch(NumberFormatException e) {
			throwIAE("fullmoveNumber must be an numeric value but is '%s'", fullmoveNumber);
		}
		
		return fullmoveNumber;
	}
	
}
