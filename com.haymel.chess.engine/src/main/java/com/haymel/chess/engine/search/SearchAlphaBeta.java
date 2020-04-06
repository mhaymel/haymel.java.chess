/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.search.PieceValue.pieceValue;
import static com.haymel.chess.engine.search.SearchInfo.noopSearchInfo;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.moves.Moves;

public class SearchAlphaBeta {		//TODO refactor, unit test

	static final int MAX_VALUE =  1_000_000_100;
	static final int MIN_VALUE = - MAX_VALUE;
	
	private final Game game;
	private int maxDepth;
	private volatile boolean stop;
	private final SearchInfo info;
	private int maxSelDepth;
	private Move[] principalVariation;
	private final NodesCalculator nodesCalculator;	
	private final MakeMove makeMove;

	public SearchAlphaBeta(Game game) {
		this(game, noopSearchInfo, new NodesCalculator());
	}

	public SearchAlphaBeta(Game game, SearchInfo info) {
		this(game, info, new NodesCalculator());
	}
	
	public SearchAlphaBeta(Game game, SearchInfo info, NodesCalculator nodesCalculator) {
		this.game = nonNull(game, "game");
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
		maxDepth = depth;
		maxSelDepth = depth;
		this.principalVariation = principalVariation;
		return game.activeColor() == white ? white() : black();
	}
	
	public void stop() {
		stop = true;
	}
	
	private BestMove white() {
		int alpha = MIN_VALUE;
		BestMove bestMove = null;
		
		Moves moves = game.whiteMoves();			
		if (moves.kingCaptureCount() > 0)		
			return blackIsMate();
		
		final int depth = 0;
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;

		assert size > 0;

		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];

			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			
			Moves blackMoves = game.blackMoves();
			if (blackMoves.kingCaptureCount() == 0) {
				int score = black(blackMoves, depth + 1, alpha, MAX_VALUE, v);
				if (score > alpha) {
					alpha = score;
					bestMove = newBestMove(v, score);
					info.bestMoveConsumer(bestMove);
				}
			}

			makeMove.undoMove();
		}

		if (bestMove != null) 	
			return bestMove;
		
		return whiteIsInCheck() ? whiteIsMate() : stalemate();
	}
	
	private int white(Moves moves, int depth, int alpha, int beta, Variant variant) {
		assert depth >= 1: format("depth: %s, maxDepth: %s", depth, maxDepth);
		assert game.whiteMoves().kingCaptureCount() == 0;
		assert moves.kingCaptureCount() == 0;
		
		if (depth >= maxDepth)
			return whiteQuiet(moves, depth, alpha, beta, variant);
		
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;

		assert size > 0;

		int validMovesCount = 0;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];

			Variant v = new Variant(move);
			makeMove.makeMove(move);

			Moves blackMoves = game.blackMoves();
			if (blackMoves.kingCaptureCount() == 0) {
				validMovesCount++;
				int score = black(blackMoves, depth + 1, alpha, beta, v);
				makeMove.undoMove();
				
				if (score >= beta) 
					return beta;
				
				if (score > alpha) {
					alpha = score;
					variant.add(v);
				}
			}
			else
				makeMove.undoMove();
		}

		return validMovesCount > 0
				? alpha
				: whiteIsInCheck() 
					? whiteMate(depth) 
					: 0;	//stalemate
	}

	private int whiteQuiet(Moves moves, int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;

		assert game.whiteMoves().kingCaptureCount() == 0;
		assert moves.kingCaptureCount() == 0;
		
		if (depth > maxSelDepth)
			maxSelDepth = depth;
		
		int stand_pat = evaluate();
		if (stand_pat >= beta)
	        return beta;

	    if (alpha < stand_pat)
	        alpha = stand_pat;

	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			if (!capture(move)) continue;

			assert capture(move);

			Variant v = new Variant(move);
			makeMove.makeMove(move);
			
			Moves blackMoves = game.blackCaptureMoves();
			if (blackMoves.kingCaptureCount() == 0) {
				int score = blackQuiet(blackMoves, depth + 1, alpha, beta, v);
				makeMove.undoMove();
				
				if (score >= beta) 
					return beta;
				
				if (score > alpha) {
					alpha = score;
					variant.add(v);
				}
			}
			else
				makeMove.undoMove();
		}

		return alpha;
	}

	private BestMove black() {
		int beta = MAX_VALUE;
		BestMove bestMove = null;
		
		Moves moves = game.blackMoves();
		if (moves.kingCaptureCount() > 0)
			return whiteIsMate();

		final int depth = 0;
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
	
		assert size > 0;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			
			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			
			Moves whiteMoves = game.whiteMoves();
			if (whiteMoves.kingCaptureCount() == 0) {
				int score = white(whiteMoves, depth + 1, MIN_VALUE, beta, v);
				if (score < beta) {
					beta = score;
					bestMove = new BestMove(v, score, maxDepth, maxSelDepth, nodesCalculator);
					info.bestMoveConsumer(bestMove);
				}
			}
			
			makeMove.undoMove();
		}
		
		if (bestMove != null) 	
			return bestMove;
		
		return blackIsInCheck() ? blackIsMate() : stalemate();
	}
	
	private int black(Moves moves, int depth, int alpha, int beta, Variant variant) {
		assert depth > 0: format("depth: %s, maxDepth: %s", depth, maxDepth);
		assert game.blackMoves().kingCaptureCount() == 0;
		assert moves.kingCaptureCount() == 0;

		if (depth >= maxDepth)
			return blackQuiet(moves, depth, alpha, beta, variant);
		
		if (depth >= maxDepth || stop)
			return evaluate();
		
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;
		
		assert size > 0;

		int validMovesCount = 0;
		
		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			
			Moves whiteMoves = game.whiteMoves();
			if (whiteMoves.kingCaptureCount() == 0) {
				validMovesCount++;
				int score = white(whiteMoves, depth + 1, alpha, beta, v);
				makeMove.undoMove();

				if (score <= alpha) 
					return alpha;
				
				if (score < beta) {
					beta = score;
					variant.add(v);
				}
			}
			else
				makeMove.undoMove();

		}

		return validMovesCount > 0
				? beta
				: blackIsInCheck() 
					? blackMate(depth) 
					: 0;	//stalemate
	}

	private int blackQuiet(Moves moves, int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;

		assert game.blackMoves().kingCaptureCount() == 0;
		assert moves.kingCaptureCount() == 0;
		
		if (depth > maxSelDepth) 
			maxSelDepth = depth;
		
		int stand_pat = evaluate();
		if (stand_pat <= alpha)
	        return alpha;
	    
		if (beta > stand_pat)
	        beta = stand_pat;
	
	    Move[] sortedMoves = new SortWhiteMoves(game, moves.moves(), principal(depth)).sort();
		int size = sortedMoves.length;

		for(int i = 0; i < size; i++) {
			Move move = sortedMoves[i];
			if (!capture(move)) continue;
			
			assert capture(move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);

			Moves whiteMoves = game.whiteCaptureMoves();
			if (whiteMoves.kingCaptureCount() == 0) {
				int score = whiteQuiet(whiteMoves, depth + 1, alpha, beta, v);
				makeMove.undoMove();

				if (score <= alpha) 
					return alpha;
				
				if (score < beta) {
					beta = score;
					variant.add(v);
				}
			}
			else
				makeMove.undoMove();
		}
		
		return beta;
	}

	private int evaluate() {
		if (nodesCalculator.inc())
			info.nodes(nodesCalculator);
		
		int whiteValue = pieceValues(game.whitePieces());
		int blackValue = pieceValues(game.blackPieces());
		
		return whiteValue - blackValue;
	}

	private void currentMove(int size, int i, Move move) {
		info.currentMove(new AnalyzedMove(move, i + 1, size));
	}

	private boolean capture(Move move) {
		return  
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion;
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

	private BestMove newBestMove(Variant v, int score) {
		return new BestMove(v, score, maxDepth, maxSelDepth, nodesCalculator);
	}

	private BestMove stalemate() {
		return newBestMove(null, 0);
	}

	private BestMove whiteIsMate() {
		return newBestMove(null, MIN_VALUE);
	}

	private BestMove blackIsMate() {
		return newBestMove(null, MAX_VALUE);
	}
	
	private boolean whiteIsInCheck() {
		return game.blackMoves().kingCaptureCount() > 0;
	}
	
	private boolean blackIsInCheck() {
		return game.whiteMoves().kingCaptureCount() > 0;
	}

	public static int blackMate(int depth) {
		return MAX_VALUE - depth;
	}

	public static int whiteMate(int depth) {
		return MIN_VALUE + depth;
	}
	
}
