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
import com.haymel.chess.engine.moves.Move;
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
		
		if (moves.kingCaptureCount() > 0)
			return;
		
		if (depth == maxDepth) {
			nodes++;
			return;
		}
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			makeMove.makeMove(move);
			black(depth + 1);
			makeMove.undoMove();
		}
	}

	private void black(int depth) {
		Moves moves = game.blackMoves();

		if (moves.kingCaptureCount() > 0)
			return;
		
		if (depth == maxDepth) {
			nodes++;
			return;
		}
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			makeMove.makeMove(move);
			white(depth + 1);
			makeMove.undoMove();
		}
	}
	
}
