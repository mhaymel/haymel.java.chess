/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.FieldFromString;
import com.haymel.chess.engine.game.Game;

class Enpassant {

	private final Game game;
	private final String fieldAsString; 
	
	Enpassant(Game game, String fieldAsString) {
		this.game = nonNull(game, "game");
		this.fieldAsString = verify(fieldAsString);
	}
	
	void execute() {
		game.resetEnPassant();
		if (fieldAsString.equals("-"))
			return;

		Field field = new FieldFromString(fieldAsString).value();
		
		if (game.activeColor() == white)
			handleWhite(field);
		else 
			handleBlack(field);
		
		if (game.activeColor() == white && field.rank() != a6.rank())
			throwIAE("wrong enpassant field '%s' for active color white", field);
			
		if (game.activeColor() == black && field.rank() != a3.rank())
			throwIAE("wrong enpassant field '%s' for active color white", field);

	}

	private void handleBlack(Field field) {
		if (field.rank() != a3.rank())
			throwIAE("wrong enpassant field '%s' for black", field);

		game.activeColorWhite();
		game.enPassant(field);
		game.activeColorBlack();	
	}

	private void handleWhite(Field field) {
		if (field.rank() != a6.rank())
			throwIAE("wrong enpassant field '%s' for white", field);

		game.activeColorBlack();	
		game.enPassant(field);
		game.activeColorWhite();
	}

	private static String verify(String fieldAsString) {
		nonEmpty(fieldAsString, "fieldAsString");
		if (fieldAsString.length() > 2)
			return throwIAE("enpassant fieldAsString in FEN must have a length between 1 and 2 but is '%s'", fieldAsString);
		
		return fieldAsString;
	}
	
}
