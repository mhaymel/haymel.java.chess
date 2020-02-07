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
import static java.lang.Integer.MAX_VALUE;

import java.util.Arrays;
import java.util.Comparator;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;

public class SortWhiteMoves {

	private final static int VALUE_PV = 0;
	private final static int VALUE_CAPTURE = 10_000;
	private final static int VALUE_DEFAULT = MAX_VALUE;
	
	private final Move[] moves;
	private final Move pv;
	
	public SortWhiteMoves(Move[] moves, Move pv) {
		this.moves = nonNull(moves, "moves");
		this.pv = pv;	// pv can be null
	}
	
	public Move[] sort() {
		Comparator<Move> comperator = (a, b) -> value(a) - value(b);
		Arrays.sort(moves, comperator );
		return moves;
	}

	private int value(Move move) {
		if (pv != null && pv.from() == move.from() && pv.to() == move.to())
			return VALUE_PV;
		
		if (isCapture(move))
			return VALUE_CAPTURE - pieceValue(move.capturedPiece().type());
		
		return VALUE_DEFAULT;
	}
	
	private static boolean isCapture(Move move) {
		return  
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion;
	}
	
}
