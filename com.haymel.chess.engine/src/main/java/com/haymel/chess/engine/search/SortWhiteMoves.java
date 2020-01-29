/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.PieceValue.pieceValue;
import static com.haymel.util.Require.nonNull;

import java.util.Arrays;
import java.util.Comparator;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class SortWhiteMoves {

	private final Move[] moves;
	
	public SortWhiteMoves(Move[] moves) {
		this.moves = nonNull(moves, "moves");
	}
	
	public Move[] sort() {
		Comparator<Move> comperator = (a, b) -> compare(a, b);
		Arrays.sort(moves, comperator );
		return moves;
	}

	private static int compare(Move a, Move b) {
		if (isCapture(a)) {
			if (isCapture(b)) {
				return compare(a.capturedPiece(), b.capturedPiece()); 
			}
			else 
				return -1;
		}
		else {
			if (isCapture(b)) {
				return 1; 
			}
			else 
				return -1;
			
		}
	}

	private static int compare(Piece p1, Piece p2) {
		return pieceValue(p1.type()) - pieceValue(p2.type());
	}

	private static boolean isCapture(Move move) {
		return  
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion;
	}
	
	
}
