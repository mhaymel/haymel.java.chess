/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

public class PieceType {

	public static final int white 			= 0b00100000;
	public static final int black 			= 0b01000000;
	public static final int special			= 0b10000000;

	public static final int WhitePawn = 	white | 1;
	public static final int WhiteRook = 	white | 2;
	public static final int WhiteKnight = 	white | 3;
	public static final int WhiteBishop = 	white | 4;
	public static final int WhiteQueen = 	white | 5;
	public static final int WhiteKing = 	white | 6;

	public static final int BlackPawn = 	black | 1;
	public static final int BlackRook = 	black | 2;
	public static final int BlackKnight = 	black | 3;	
	public static final int BlackBishop = 	black | 4;
	public static final int BlackQueen = 	black | 5;
	public static final int BlackKing = 	black | 6;

	public static final int Border = 		special | 13;
	public static final int Free = 			special | 14;

	public static boolean pieceTypeValid(int type) {
		switch(type) {
		case WhitePawn:
		case WhiteRook:
		case WhiteKnight:
		case WhiteBishop:
		case WhiteQueen:
	    case WhiteKing:
        case BlackPawn:
        case BlackRook:
        case BlackKnight:
        case BlackBishop:
        case BlackQueen:
        case BlackKing:
        case Border:
        case Free:
        	return true;
		}
		
		return false;
	}
	
	public static boolean white(int type) {
		assert pieceTypeValid(type);
		return (type & white) != 0; 
	}

	public static boolean black(int type) {
		assert pieceTypeValid(type);
		return (type & black) != 0; 
	}
}
        	
        	
        	