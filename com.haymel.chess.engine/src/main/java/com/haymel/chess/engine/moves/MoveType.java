/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

public class MoveType {

	public static final int normal					= 1;
	public static final int normalKingMove			= 2;
	public static final int normalRookMove			= 3;
	public static final int pawn					= 4;
	public static final int pawnDoubleStep			= 5;
	public static final int kingsideCastling		= 6;
	public static final int queensideCastling		= 7;
	public static final int enpassant				= 8;
	public static final int capture					= 9;
	public static final int captureKingMove			= 10;
	public static final int captureRookMove			= 11;
	public static final int promotionQueen			= 12;
	public static final int promotionRook			= 13;
	public static final int promotionBishop			= 14;
	public static final int promotionKnight			= 15;
	public static final int capturePromotionQueen	= 16;
	public static final int capturePromotionRook	= 17;
	public static final int capturePromotionBishop	= 18;
	public static final int capturePromotionKnight	= 19;
	
	public static boolean validMoveType(int type) {
		switch(type) {
		case normal:				
		case normalKingMove:		
		case normalRookMove:		
		case pawn:				
		case pawnDoubleStep:		
		case kingsideCastling:	
		case queensideCastling:	
		case enpassant:			
		case capture:				
		case captureKingMove:		
		case captureRookMove:		
		case promotionQueen:			
		case promotionRook:
		case promotionBishop:
		case promotionKnight:
		case capturePromotionQueen:
		case capturePromotionRook:
		case capturePromotionBishop:
		case capturePromotionKnight:
			return true;
		}
		return false;
	}
	
}
