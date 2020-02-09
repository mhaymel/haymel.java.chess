/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.piece.PieceType;

public class PieceValue {		//TODO unit test

	public static int pieceValue(PieceType type) {
		switch(type) {
		case WhitePawn:
		case BlackPawn:
			return 100;
			
		case WhiteKnight:
		case BlackKnight:
		case WhiteBishop:
		case BlackBishop:
			return 300;

		case WhiteRook:
		case BlackRook:
			return 500;
			
		case WhiteQueen:
		case BlackQueen:
			return 1200;
		
		case WhiteKing:
		case BlackKing:
			return 0;

		case Border:
		case Free:
		default:
			assert false;
			return 0;
		}
	}
	
}
