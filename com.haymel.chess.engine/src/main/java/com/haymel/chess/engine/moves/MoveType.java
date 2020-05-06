/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

public enum MoveType {

	normal,
	pawnDoubleStep,
	kingsideCastling,
	queensideCastling,
	enpassant,
	capture,
	promotion,
	capturePromotion;
	
	public static final boolean capture(MoveType type) {
		switch(type) {
		case capture:
		case enpassant:
		case capturePromotion:
			return true;
		default:
			return false;
		}
	}
	
}
