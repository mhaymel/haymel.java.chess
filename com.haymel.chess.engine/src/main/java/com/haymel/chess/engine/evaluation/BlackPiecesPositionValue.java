/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.king;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.pawn;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.queen;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.rook;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.knight;
import static com.haymel.chess.engine.evaluation.WhitePiecesPositionValue.bishop;
import static com.haymel.chess.engine.piece.PieceType.black;
import static com.haymel.chess.engine.piece.PieceType.pieceTypeValid;

import com.haymel.chess.engine.piece.PieceType;

public class BlackPiecesPositionValue implements PiecePositionValue {

	private static final int[] pawnPST = new PieceSquareTableFromArray(pawn, true).value();
	private static final int[] kingPST = new PieceSquareTableFromArray(king, true).value();
	private static final int[] rookPST = new PieceSquareTableFromArray(rook, true).value();
	private static final int[] knightPST = new PieceSquareTableFromArray(knight, true).value();
	private static final int[] queenPST = new PieceSquareTableFromArray(queen, true).value();
	private static final int[] bishopPST = new PieceSquareTableFromArray(bishop, true).value();
			
	public int value(int type, int field) {
		assert pieceTypeValid(type);
		assert black(type);
		assert valid(field);
		
		switch(type) {
		case PieceType.BlackPawn:	return pawnPST[field];
		case PieceType.BlackRook:	return rookPST[field];		
		case PieceType.BlackKnight:	return knightPST[field];
		case PieceType.BlackBishop:	return bishopPST[field];
		case PieceType.BlackQueen:	return queenPST[field];
	    case PieceType.BlackKing:	return kingPST[field];
	    default:
	    	assert false;
		}
		
		return 0;
	}
	
}
