/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.Free;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.PieceList;

// https://www.chessprogramming.org/Simplified_Evaluation_Function
public class PieceValue {		//TODO unit test

	public static int pieceValue(int type) {
		switch(type) {
		case WhitePawn:
		case BlackPawn:
			return 100;
			
		case WhiteKnight:
		case BlackKnight:
			return 320;
		case WhiteBishop:
		case BlackBishop:
			return 330;

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
	
	public static int pieceValue(PieceList pieces) {
		int value = 0;
		int size = pieces.size();
		for(int i = 0; i < size; i++) 
			value += pieceValue(pieces.piece(i).type());
	
		return value;
	}
	
}
