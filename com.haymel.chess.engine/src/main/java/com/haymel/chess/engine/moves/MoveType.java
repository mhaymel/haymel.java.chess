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
	normalKingMove,
	normalRookMove,
	pawn,
	pawnDoubleStep,
	kingsideCastling,
	queensideCastling,
	enpassant,
	capture,
	captureKingMove,
	captureRookMove,
	promotion,
	capturePromotion;
	
	public static final boolean capture(MoveType type) {
		switch(type) {
		case capture:
		case enpassant:
		case capturePromotion:
		case captureKingMove:
		case captureRookMove:
			return true;
		default:
			return false;
		}
	}
	
}
