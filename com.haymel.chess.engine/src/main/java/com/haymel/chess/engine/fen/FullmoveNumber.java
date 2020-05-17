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

class FullmoveNumber {

	private final Position position;
	private final String fullMoveNumber; 
	
	FullmoveNumber(Position position, String fullMoveNumber) {
		this.position = nonNull(position, "position");
		this.fullMoveNumber = verify(fullMoveNumber);
	}
	
	void execute() {
		position.fullMoveNumber(parseInt(fullMoveNumber));
	}

	private static String verify(String fullMoveNumber) {
		nonEmpty(fullMoveNumber, "fullMoveNumber");

		try {
			if (parseInt(fullMoveNumber) < 1)
				throwIAE("fullMoveNumber must be greater than 0 but is '%s'", fullMoveNumber); 
		}
		catch(NumberFormatException e) {
			throwIAE("fullMoveNumber must be an numeric value but is '%s'", fullMoveNumber);
		}
		
		return fullMoveNumber;
	}
	
}
