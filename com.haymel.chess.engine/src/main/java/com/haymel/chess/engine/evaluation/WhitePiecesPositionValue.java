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

//https://www.chessprogramming.org/Simplified_Evaluation_Function
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
	
	static final int[] king = {
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-20,-30,-30,-40,-40,-30,-30,-20,
		-10,-20,-20,-20,-20,-20,-20,-10,
		 20, 20,  0,  0,  0,  0, 20, 20,
		 20, 30, 10,  0,  0, 10, 30, 20
	};

	static final int[] rook = {
		  0,  0,  0,  0,  0,  0,  0,  0,
		  5, 10, 10, 10, 10, 10, 10,  5,
		 -5,  0,  0,  0,  0,  0,  0, -5,
		 -5,  0,  0,  0,  0,  0,  0, -5,
		 -5,  0,  0,  0,  0,  0,  0, -5,
		 -5,  0,  0,  0,  0,  0,  0, -5,
		 -5,  0,  0,  0,  0,  0,  0, -5,
		  0,  0,  0,  5,  5,  0,  0,  0
	};

	static final int[] queen = {
		-20,-10,-10, -5, -5,-10,-10,-20,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-10,  0,  5,  5,  5,  5,  0,-10,
		 -5,  0,  5,  5,  5,  5,  0, -5,
		  0,  0,  5,  5,  5,  5,  0, -5,
		-10,  5,  5,  5,  5,  5,  0,-10,
		-10,  0,  5,  0,  0,  0,  0,-10,
		-20,-10,-10, -5, -5,-10,-10,-20
	};
	
	static final int[] knight = {
		-50,-40,-30,-30,-30,-30,-40,-50,
		-40,-20,  0,  0,  0,  0,-20,-40,
		-30,  0, 10, 15, 15, 10,  0,-30,
		-30,  5, 15, 20, 20, 15,  5,-30,
		-30,  0, 15, 20, 20, 15,  0,-30,
		-30,  5, 10, 15, 15, 10,  5,-30,
		-40,-20,  0,  5,  5,  0,-20,-40,
		-50,-40,-30,-30,-30,-30,-40,-50,			
	};

	static final int[] bishop = {
		-20,-10,-10,-10,-10,-10,-10,-20,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-10,  0,  5, 10, 10,  5,  0,-10,
		-10,  5,  5, 10, 10,  5,  5,-10,
		-10,  0, 10, 10, 10, 10,  0,-10,
		-10, 10, 10, 10, 10, 10, 10,-10,
		-10,  5,  0,  0,  0,  0,  5,-10,
		-20,-10,-10,-10,-10,-10,-10,-20,
	};
	
	private static final int[] pawnPST = new PieceSquareTableFromArray(pawn).value();
	private static final int[] kingPST = new PieceSquareTableFromArray(king).value();
	private static final int[] rookPST = new PieceSquareTableFromArray(rook).value();
	private static final int[] queenPST = new PieceSquareTableFromArray(queen).value();
	private static final int[] knightPST = new PieceSquareTableFromArray(knight).value();
	private static final int[] bishopPST = new PieceSquareTableFromArray(bishop).value();
			
	public int value(int type, int field) {
		assert pieceTypeValid(type);
		assert white(type);
		assert valid(field);
		
		switch(type) {
		case PieceType.WhitePawn:	return pawnPST[field];
		case PieceType.WhiteRook:	return rookPST[field];		
		case PieceType.WhiteKnight:	return knightPST[field];
		case PieceType.WhiteBishop: return bishopPST[field];
		case PieceType.WhiteQueen:	return queenPST[field];
	    case PieceType.WhiteKing:	return kingPST[field];
	    default:
	    	assert false;
		}
		
		return 0;
	}
	
}
