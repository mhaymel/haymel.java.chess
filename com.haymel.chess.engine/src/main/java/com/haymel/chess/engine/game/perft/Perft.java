/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	05.01.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.perft;

import static com.haymel.util.Require.greaterEqualZero;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.util.concurrent.atomic.AtomicLong;

import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Moves;

public class Perft {

	private final Game game;
	private final int maxDepth;
	private final AtomicLong count = new AtomicLong();

	public Perft(Game game, int maxDepth) {
		this.game = nonNull(game, "game");
		this.maxDepth = greaterEqualZero(maxDepth, "maxDepth");
	}
	
	public void execute() {
		count.set(0);
		if (game.activeColor() == ActiveColor.white) 
			white(0);
		else
			black(0);
		System.out.println("nodes:  " + count.get());
	}

	private void white(int depth) {
		Moves moves = game.whiteMoves();
		
		if (moves == null)
			return;
		
		if (depth == maxDepth) {
			count.incrementAndGet();
			return;
		}
		
		int enPassant = game.enPassant();
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
		
			if (depth == 0)
				System.out.print(format("%s:  ", size - i));
		
			int move = moves.move(i);
			makeMove.makeMove(move);
			black(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
			
			if (depth == 0)
				System.out.println("     " + count);
		}
	}
	
	private void black(int depth) {
		Moves moves = game.blackMoves();

		if (moves == null)
			return;

		if (depth == maxDepth) {
			count.incrementAndGet();
			return;
		}
		
		int enPassant = game.enPassant();
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			
			if (depth == 1)
				System.out.print(format("%s ", size - i));
			
			int move = moves.move(i);
			makeMove.makeMove(move);
			white(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
		}
	}

}
