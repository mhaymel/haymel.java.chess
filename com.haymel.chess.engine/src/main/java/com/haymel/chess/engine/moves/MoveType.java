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
	promotionQueen,
	promotionRook,
	promotionBishop,
	promotionKnight,
	capturePromotionQueen,
	capturePromotionRook,
	capturePromotionBishop,
	capturePromotionKnight,
	
}
