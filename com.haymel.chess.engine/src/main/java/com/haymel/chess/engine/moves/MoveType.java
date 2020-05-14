/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

public class MoveType {

	public static final int normal				= 1;
	public static final int normalKingMove		= 2;
	public static final int normalRookMove		= 3;
	public static final int pawn				= 4;
	public static final int pawnDoubleStep		= 5;
	public static final int kingsideCastling	= 6;
	public static final int queensideCastling	= 7;
	public static final int enpassant			= 8;
	public static final int capture				= 9;
	public static final int captureKingMove		= 10;
	public static final int captureRookMove		= 11;
	public static final int promotion			= 12;
	public static final int capturePromotion	= 13;
	
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
		case promotion:			
		case capturePromotion:
			return true;
		}
		return false;
	}
	
}
