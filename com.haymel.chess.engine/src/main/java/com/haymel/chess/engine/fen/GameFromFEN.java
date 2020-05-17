/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import com.haymel.chess.engine.game.Game;

//	https://en.wikipedia.org/wiki/Forsyth-Edwards_Notation

public class GameFromFEN {
	
	private final PositionFromFEN fromFen;

	public static final Game gameFromInitialFen() {
		return new Game(PositionFromFEN.positionFromInitialFen());
	}
	
	public GameFromFEN(String fen) {
		this.fromFen = new PositionFromFEN(fen);
	}

	public Game value() {
		return new Game(fromFen.value());
	}
	
}
