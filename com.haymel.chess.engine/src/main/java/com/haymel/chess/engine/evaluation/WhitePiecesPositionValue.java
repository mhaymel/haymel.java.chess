/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	24.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.piece.PieceType.pieceTypeValid;
import static com.haymel.chess.engine.piece.PieceType.white;

import com.haymel.chess.engine.piece.PieceType;

public class WhitePiecesPositionValue implements PiecePositionValue {

	static final int[] pawn = {
		0,  0,  0,  0,  0,  0,  0,  0,
		50, 50, 50, 50, 50, 50, 50, 50,
		10, 10, 20, 30, 30, 20, 10, 10,
		 5,  5, 10, 25, 25, 10,  5,  5,
		 0,  0,  0, 20, 20,  0,  0,  0,
		 5, -5,-10,  0,  0,-10, -5,  5,
		 5, 10, 10,-20,-20, 10, 10,  5,
		 0,  0,  0,  0,  0,  0,  0,  0
	};
	
	private static final int[] pawnPST = new PieceSquareTableFromArray(pawn).value();
			
	public int value(int type, int field) {
		assert pieceTypeValid(type);
		assert white(type);
		assert valid(field);
		
		switch(type) {
		case PieceType.WhitePawn:			return pawnPST[field];
		case PieceType.WhiteRook:			
		case PieceType.WhiteKnight:
		case PieceType.WhiteBishop:
		case PieceType.WhiteQueen:
	    case PieceType.WhiteKing:
	    	return 0;
	    default:
	    	assert false;
		}
		
		return 0;
	}
	
}
