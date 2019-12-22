/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.moves.Moves;

public class WhiteMoves {

	private final Board board;
	private final Moves moves;
	
	private WhiteKingMoves kingMoves;
	
	public WhiteMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
		this.kingMoves = new WhiteKingMoves(board, moves);
	}
	
}
