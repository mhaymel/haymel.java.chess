/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;

public class ValidMovesWhite {		//TODO unit test

	private static final Move[] empty = new Move[0];
	
	private final Game game;
	
	public ValidMovesWhite(Game game) {
		assert game.activeColor() == ActiveColor.white;
		
		this.game = nonNull(game, "game");
	}
	
	public Move[] calculate() {
		
		List<Move> validMoves = new ArrayList<>();
		
		Moves moves = game.whiteMoves();
		if (moves == null)
			return empty;

		MakeMove makeMove = new MakeMove(game);
		
		for(Move move : moves.moves()) {
			makeMove.makeMove(move);
			if (!isInCheck())
				validMoves.add(move);
			makeMove.undoMove();
		}
		
		return empty;
	}

	private boolean isInCheck() {
		return game.blackMoves() == null;
	}
	
}
