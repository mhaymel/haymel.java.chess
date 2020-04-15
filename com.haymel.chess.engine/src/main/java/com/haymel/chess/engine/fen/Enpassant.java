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
import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

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

		int field = new FieldFromString(fieldAsString).value();
		
		if (game.activeColor() == white)
			handleWhite(field);
		else 
			handleBlack(field);
		
		if (game.activeColor() == white && rank(field) != rank(a6))
			throwIAE("wrong enpassant field '%s' for active color white", fieldAsString(field));
			
		if (game.activeColor() == black && rank(field) != rank(a3))
			throwIAE("wrong enpassant field '%s' for active color white", fieldAsString(field));

	}

	private void handleBlack(int field) {
		if (rank(field) != rank(a3))
			throwIAE("wrong enpassant field '%s' for black", fieldAsString(field));

		game.activeColorWhite();
		game.enPassant(field);
		game.activeColorBlack();	
	}

	private void handleWhite(int field) {
		if (rank(field) != rank(a6))
			throwIAE("wrong enpassant field '%s' for white", fieldAsString(field));

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
