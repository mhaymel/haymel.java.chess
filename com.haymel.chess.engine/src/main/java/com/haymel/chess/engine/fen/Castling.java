/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.castling.PositionCastlingRight;

class Castling {

	private final PositionCastlingRight castling;
	private final String castlingFen; 
	
	Castling(PositionCastlingRight castling, String castlingFen) {
		this.castling = nonNull(castling, "castling");
		this.castlingFen = verify(castlingFen);
	}
	
	void execute() {
		castling.black().disableKingside();
		castling.black().disableQueenside();
		castling.white().disableKingside();
		castling.white().disableQueenside();
		
		int len = castlingFen.length();
		for(int i = 0; i < len; i++) 
			handle(castlingFen.charAt(i));
	}

	private void handle(char c) {
		switch (c) {
		case 'K': 
			castling.white().enableKingside();
			break;

		case 'Q': 
			castling.white().enableQueenside();
			break;

		case 'k': 
			castling.black().enableKingside();
			break;
			
		case 'q': 
			castling.black().enableQueenside();
			break;

		case '-': 
			break;
			
		default:
			throwIAE("Illegal character '%s' in castling availability '%s'", c, castlingFen);
			break;
		}
	}

	private static String verify(String castling) {
		nonEmpty(castling, "castling");
		if (castling.length() > 4)
			return throwIAE("castling availability in FEN must have a length between 1 and 4 but is '%s'", castling);
		
		return castling;
	}
	
}
