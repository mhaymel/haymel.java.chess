/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Moves;

public class Perft {

	private final Game game;
	private int maxDepth;
	private long nodes;
	
	public Perft(Game game) {
		this.game = nonNull(game, "game");
	}
	
	public void execute(int depth) {
		nodes = 0;
		maxDepth = depth;
		switch(game.activeColor()) {
		case black:
			black(0);
			break;
		case white:
			white(0);
			break;
		default:
			assert false;
		}
	}

	public long nodes() {
		return nodes;
	}
	
	private void white(int depth) {
		Moves moves = game.whiteMoves();
		
		if (moves == null)
			return;
		
		if (depth == maxDepth) {
			nodes++;
			return;
		}
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			int move = moves.move(i);
			makeMove.makeMove(move);
			if (!whiteIsInCheck())
				black(depth + 1);
			makeMove.undoMove();
		}
	}

	private void black(int depth) {
		Moves moves = game.blackMoves();

		if (moves == null)
			return;
		
		if (depth == maxDepth) {
			nodes++;
			return;
		}
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			int move = moves.move(i);
			makeMove.makeMove(move);
			if (!blackIsInCheck())
				white(depth + 1);
			makeMove.undoMove();
		}
	}

	private boolean whiteIsInCheck() {
		return WhiteInCheck.whiteIsInCheck(game.whitePieces().king().field(), game.pieces());
	}

	private boolean blackIsInCheck() {
		return BlackInCheck.blackIsInCheck(game.blackPieces().king().field(), game.pieces());
	}
	
}
