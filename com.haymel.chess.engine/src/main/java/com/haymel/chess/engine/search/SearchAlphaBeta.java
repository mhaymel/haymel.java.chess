/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.PieceValue.pieceValue;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.moves.Moves;

public class SearchAlphaBeta {

	private static final int MIN_VALUE = Integer.MIN_VALUE;
	private static final int MAX_VALUE = Integer.MAX_VALUE;
	
	private final Game game;
	private int maxDepth;
	private BestMove bestMove;
	private volatile boolean stop;
	private final SearchInfo info;
	private int maxSelDepth;
	private Move[] principalVariation;
	private final NodesCalculator nodesCalculator;	
	private final MakeMove makeMove;
	
	public SearchAlphaBeta(Game game, SearchInfo info, NodesCalculator nodesCalculator) {
		this.game = nonNull(game, "game");
		this.bestMove = null;
		this.stop = false;
		this.info = nonNull(info, "info");
		this.nodesCalculator = nonNull(nodesCalculator, "nodesCalculator");
		this.makeMove = new MakeMove(game);
	}

	public BestMove execute(int depth) {
		info.searchDepth(depth);
		return execute(depth, null);
	}
	
	public BestMove execute(int depth, Move[] principalVariation) { 
		return doExecute(depth, principalVariation);
	}

	private BestMove doExecute(int depth, Move[] principalVariation) {
		stop = false;
		bestMove = null; 
		maxDepth = depth;
		maxSelDepth = depth;
		this.principalVariation = principalVariation;
		
		switch(game.activeColor()) {
		case black:
			black(MIN_VALUE, MAX_VALUE);
			break;
		case white:
			white(MIN_VALUE, MAX_VALUE);
			break;
		default:
			assert false;
		}
		
		return bestMove;			//TODO: do not return null!
	}
	
	public void stop() {
		stop = true;
	}
	
	private void white(int alpha, int beta) {
		Moves moves = game.whiteMoves();
		if (moves.kingCaptureCount() > 0)
			return;

		final int depth = 0;
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];

			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = black(depth + 1, alpha, beta, v);
			makeMove.undoMove();
			
			if (score >= beta) 
				return;
			
			if (score > alpha) {
				alpha = score;
				bestMove(v, alpha);
			}
		}
		
		return;
	}
	
	private int white(int depth, int alpha, int beta, Variant variant) {
		assert depth > 0: format("depth: %s, maxDepth: %s", depth, maxDepth);

		if (depth >= maxDepth)
			return whiteQuiet(depth, alpha, beta, variant);
		
		Moves moves = game.whiteMoves();
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth + 1;

	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];

			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = black(depth + 1, alpha, beta, v);
			makeMove.undoMove();
			
			if (score >= beta) 
				return beta;
			
			if (score > alpha) {
				alpha = score;
				if (!isBlackMate(score))
					variant.add(v);
			}
		}
		
		return alpha;
	}

	private int whiteQuiet(int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;
		
		if (depth > maxSelDepth)
			maxSelDepth = depth;
		
		Moves moves = game.whiteCaptureMoves();
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth + 1;
		
		int stand_pat = evaluate();
		if (stand_pat >= beta)
	        return beta;

	    if (alpha < stand_pat)
	        alpha = stand_pat;

	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			assert capture(move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = blackQuiet(depth + 1, alpha, beta, v);
			makeMove.undoMove();
			
			if (score >= beta) 
				return beta;
			
			if (score > alpha) {
				alpha = score;
				if (!isBlackMate(score))
					variant.add(v);
			}
		}
	    
		return alpha;
	}

	private boolean isBlackMate(int score) {
		return MIN_VALUE + 1 == score;
	}
	
	private void black(int alpha, int beta) {
		Moves moves = game.blackMoves();
		if (moves.kingCaptureCount() > 0)
			return;
		
		final int depth = 0;
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			
			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = white(depth + 1, alpha, beta, v);
			makeMove.undoMove();

			if (score <= alpha) 
				return;
			
			if (score < beta) {
				beta = score;
				bestMove(v, beta);
			}
		}
		
		return;
	}
	
	private int black(int depth, int alpha, int beta, Variant variant) {
		assert depth > 0: format("depth: %s, maxDepth: %s", depth, maxDepth);

		if (depth >= maxDepth)
			return blackQuiet(depth, alpha, beta, variant);
		
		Moves moves = game.blackMoves();
		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth - 1;
		
		if (depth >= maxDepth || stop)
			return evaluate();
		
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = white(depth + 1, alpha, beta, v);
			makeMove.undoMove();

			if (score <= alpha) 
				return alpha;
			
			if (score < beta) {
				beta = score;
				if (!isWhiteMate(score))
					variant.add(v);
			}
		}
		
		return beta;
	}

	private int blackQuiet(int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;
		
		if (depth > maxSelDepth) 
			maxSelDepth = depth;
		
		Moves moves = game.blackCaptureMoves();
		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth - 1;

		int stand_pat = evaluate();
		if (stand_pat <= alpha)
	        return alpha;
	    
		if (beta > stand_pat)
	        beta = stand_pat;
		
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;

		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			assert capture(move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = whiteQuiet(depth + 1, alpha, beta, v);
			makeMove.undoMove();

			if (score <= alpha) 
				return alpha;
			
			if (score < beta) {
				beta = score;
				if (!isWhiteMate(score))
					variant.add(v);
			}
		}
		
		return beta;
	}

	private boolean isWhiteMate(int score) {
		return MAX_VALUE - 1 == score;
	}
	
	private int evaluate() {
		if (nodesCalculator.inc())
			info.nodes(nodesCalculator);
		
		int whiteValue = pieceValues(game.whitePieces());
		int blackValue = pieceValues(game.blackPieces());
		
		return whiteValue - blackValue;
	}

	private void bestMove(Variant variant, int value) {
		bestMove = new BestMove(variant, value, maxDepth, maxSelDepth, nodesCalculator);
		info.bestMoveConsumer(bestMove);
	}

	private void currentMove(int size, int i, Move move) {
		info.currentMove(new AnalyzedMove(move, i + 1, size));
	}

	private boolean capture(Move move) {
		assert 
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion : move;
		return true;
	}
	
	private int pieceValues(PieceList pieces) {
		int value = 0;
		int size = pieces.size();
		for(int i = 0; i < size; i++) 
			value += pieceValue(pieces.piece(i).type());
		
		return value;
	}
	
	private Move principal(int depth) {
		if (principalVariation == null)
			return null;
		
		assert depth < principalVariation.length;
		
		try {
			return principalVariation[depth];
		}
		finally {
			if (depth + 1 == principalVariation.length)
				principalVariation = null;
		}
	}

}
