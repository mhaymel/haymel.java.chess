/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.pawn;
import static com.haymel.chess.engine.piece.PieceType.black;
import static com.haymel.chess.engine.piece.PieceType.pieceTypeValid;

import com.haymel.chess.engine.piece.PieceType;

public class BlackPiecesPositionValue implements PiecePositionValue {

	private static final int[] pawnPST = new PieceSquareTableFromArray(pawn, true).value();
			
	public int value(int type, int field) {
		assert pieceTypeValid(type);
		assert black(type);
		assert valid(field);
		
		switch(type) {
		case PieceType.BlackPawn:			return pawnPST[field];
		case PieceType.BlackRook:			
		case PieceType.BlackKnight:
		case PieceType.BlackBishop:
		case PieceType.BlackQueen:
	    case PieceType.BlackKing:
	    	return 0;
	    default:
	    	assert false;
		}
		
		return 0;
	}
	
}
