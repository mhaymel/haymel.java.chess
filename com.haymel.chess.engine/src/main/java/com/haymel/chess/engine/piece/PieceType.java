/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

public class PieceType {

	public static final int WhitePawn = 1;
	public static final int WhiteRook = 2;
	public static final int WhiteKnight = 3;
	public static final int WhiteBishop = 4;
	public static final int WhiteQueen = 5;
	public static final int WhiteKing = 6;

	public static final int BlackPawn = 7;
	public static final int BlackRook = 8;
	public static final int BlackKnight = 9;	
	public static final int BlackBishop = 10;
	public static final int BlackQueen = 11;
	public static final int BlackKing = 12;

	public static final int Border = 13;
	public static final int Free = 14;

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
}
        	
        	
        	