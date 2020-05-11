/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.castling;

public class PositionCastlingRight {	//todo unit test

	private final CastlingRight white = new CastlingRight();
	private final CastlingRight black = new CastlingRight();
	
	public CastlingRight white() {
		return white;
	}
	
	public CastlingRight black() {
		return black;
	}

	public void rights(PositionCastlingRight positionCastlingRights) {
		white.rights(positionCastlingRights.white());
		black.rights(positionCastlingRights.black());
		
	}
	
}
