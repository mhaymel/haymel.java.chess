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
	pawn,
	pawnDoubleStep,
	kingsideCastling,
	queensideCastling,
	enpassant,
	capture,
	promotion,
	capturePromotion;
	
}
