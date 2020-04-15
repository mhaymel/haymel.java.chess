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

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.BlackMoves;
import com.haymel.chess.engine.moves.white.WhiteMoves;

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
		PieceList pieces = game.whitePieces();
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(game.pieces());
		whiteMoves.generate(pieces, game.enPassant(), moves);
		
		if (moves.kingCaptureCount() > 0)
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
		
			Move move = moves.move(i);
			makeMove.makeMove(move);
			black(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
			
			if (depth == 0)
				System.out.println("     " + count);
		}
	}
	
	private void black(int depth) {
		PieceList pieces = game.blackPieces();
		Moves moves = new Moves();
		BlackMoves blackMoves = new BlackMoves(game.pieces());
		blackMoves.generate(pieces, game.enPassant(), moves);

		if (moves.kingCaptureCount() > 0)
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
			
			Move move = moves.move(i);
			makeMove.makeMove(move);
			white(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
		}
	}

}
