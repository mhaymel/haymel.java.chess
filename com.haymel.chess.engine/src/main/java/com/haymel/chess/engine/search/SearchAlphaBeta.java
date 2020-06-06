/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.evaluation.PieceValue.pieceValue;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.search.SearchInfo.noopSearchInfo;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.black.BlackMakeMove;
import com.haymel.chess.engine.game.white.WhiteMakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.search.movesorting.MoveIterator;
import com.haymel.chess.engine.search.movesorting.MoveIteratorCreator;
import com.haymel.chess.engine.search.movesorting.PVMoveIteratorCreator;

public class SearchAlphaBeta {		//TODO refactor, unit test

	static final int MAX_VALUE =  1_001_000;
	static final int MIN_VALUE = - MAX_VALUE;
	
	private final Game game;
	private int maxDepth;
	private volatile boolean stop;
	private final SearchInfo info;
	private int maxSelDepth;
	private Move[] principalVariation;
	private final NodesCalculator nodesCalculator;	
	private final MoveIteratorCreator moveIteratorCreator;
	private final Move[] history = new Move[1000];
	private long stopSearchAt;
	private final Hashtable hashtable = new Hashtable();
	
	public SearchAlphaBeta(Game game) {
		this(game, noopSearchInfo, new NodesCalculator(), new PVMoveIteratorCreator(game));
	}

	public SearchAlphaBeta(Game game, SearchInfo info) {
		this(game, info, new NodesCalculator(), new PVMoveIteratorCreator(game));
	}

	public SearchAlphaBeta(Game game, SearchInfo info, MoveIteratorCreator moveIteratorCreator) {
		this(game, info, new NodesCalculator(), moveIteratorCreator);
	}
	
	public SearchAlphaBeta(Game game, SearchInfo info, NodesCalculator nodesCalculator, MoveIteratorCreator moveIteratorCreator) {
		this.game = nonNull(game, "game");
		this.stop = false;
		this.info = nonNull(info, "info");
		this.nodesCalculator = nonNull(nodesCalculator, "nodesCalculator");
		this.moveIteratorCreator = nonNull(moveIteratorCreator, "moveIteratorCreator");
	}

	public BestMove execute(int depth) {
		return execute(depth, Long.MAX_VALUE);
	}
	
	public BestMove execute(int depth, long stopSearchAt) {
		return execute(depth, null, stopSearchAt);
	}
	
	public BestMove execute(int depth, Move[] principalVariation, long stopSearchAt) { 
		info.searchDepth(depth);
		return doExecute(depth, principalVariation, stopSearchAt);
	}

	private BestMove doExecute(int depth, Move[] principalVariation, long stopSearchAt) {
		stop = false;
		maxDepth = depth;
		maxSelDepth = depth;
		this.principalVariation = principalVariation;
		this.stopSearchAt = stopSearchAt;
		
		clearHistory();
		return game.activeColor() == white ? white() : black();
	}
	
	private void clearHistory() {
		for(int i = 0; i < history.length; i++)
			history[i] = null;
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
		
		MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), null);
		
		Move move;
		int i = 0;
		while((move = moveIter.next()) != null) {
			currentMove(moves.size(), i, move);
			i++;
			
			Variant v = new Variant(move);
			WhiteMakeMove.makeMove(game, move);
			
			Moves blackMoves = (depth + 1) < maxDepth ? game.blackMoves() : game.blackCaptureMoves();
			if (blackMoves.kingCaptureCount() == 0) {
				int score = black(blackMoves, depth + 1, alpha, MAX_VALUE, v);
				if (score > alpha) {
					alpha = score;
					bestMove = newBestMove(v, score);
					info.bestMoveConsumer(bestMove);
					hashtable.put(game.hash(), move);
				}
			}

			WhiteMakeMove.undoMove(game);
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
		
		MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), history[depth]);

		int validMovesCount = 0;
		Move move;
		while((move = moveIter.next()) != null) {
			Variant v = new Variant(move);
			WhiteMakeMove.makeMove(game, move);

			Moves blackMoves = (depth + 1) < maxDepth ? game.blackMoves() : game.blackCaptureMoves();
			if (blackMoves.kingCaptureCount() == 0) {
				validMovesCount++;
				int score = black(blackMoves, depth + 1, alpha, beta, v);
				WhiteMakeMove.undoMove(game);
				
				if (score >= beta) {
					if (!move.capture())
						history[depth] = move;

					hashtable.put(game.hash(), move);
					return beta;
				}
				
				if (score > alpha) {
					alpha = score;
					variant.add(v);
					hashtable.put(game.hash(), move);
				}
			}
			else
				WhiteMakeMove.undoMove(game);
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
		
		int positionValue = evaluate();
		if (positionValue >= beta)
	        return beta;

		if (alpha - positionValue > pieceValue(WhiteQueen))
			return positionValue;

		if (positionValue > alpha)
	        alpha = positionValue;

		int validMovesCount = 0;
		
		if (moves.size() > 0) {
			MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), null);
			Move move;
			while((move = moveIter.next()) != null) {
				assert move.capture();
	
				if (pieceValue(move.capturedPiece().type()) <= alpha - positionValue)
					continue;
	
				Variant v = new Variant(move);
				WhiteMakeMove.makeMove(game, move);
				
				Moves blackMoves = game.blackCaptureMoves();
				if (blackMoves.kingCaptureCount() == 0) {
					validMovesCount++;
					int score = blackQuiet(blackMoves, depth + 1, alpha, beta, v);
					WhiteMakeMove.undoMove(game);
						
					if (score >= beta) 
						return beta;
					
					if (score > alpha) {
						alpha = score;
						variant.add(v);
					}
				}
				else
					WhiteMakeMove.undoMove(game);
			}
		}
		
		if (validMovesCount == 0 && positionValue >= alpha && whiteIsInCheck()) {
			Moves whiteMoves = game.whiteMoves();
			assert !whiteMoves.kingCaptured();
			int size = whiteMoves.size();
			for(int i = 0; i < size; i++) {
				Move move = whiteMoves.move(i);
				if (!move.capture()) {
					WhiteMakeMove.makeMove(game, move);
					if (!whiteIsInCheck()) {
						WhiteMakeMove.undoMove(game);
						return alpha;
					}
					WhiteMakeMove.undoMove(game);
				}
			}
			
			return whiteMate(depth);
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

		MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), null);
		
		Move move;
		int i = 0;
		while((move = moveIter.next()) != null) {
			currentMove(moves.size(), i, move);
			i++;

			Variant v = new Variant(move);
			BlackMakeMove.makeMove(game, move);
			
			Moves whiteMoves = (depth + 1) < maxDepth ? game.whiteMoves() : game.whiteCaptureMoves();
			if (whiteMoves.kingCaptureCount() == 0) {
				int score = white(whiteMoves, depth + 1, MIN_VALUE, beta, v);
				if (score < beta) {
					beta = score;
					bestMove = newBestMove(v, score);
					info.bestMoveConsumer(bestMove);
					hashtable.put(game.hash(), move);
				}
			}
			
			BlackMakeMove.undoMove(game);
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
		
		MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), history[depth]);

		int validMovesCount = 0;
		Move move;
		while((move = moveIter.next()) != null) {
			Variant v = new Variant(move);
			BlackMakeMove.makeMove(game, move);
			
			Moves whiteMoves = (depth + 1) < maxDepth ? game.whiteMoves() : game.whiteCaptureMoves();
			if (whiteMoves.kingCaptureCount() == 0) {
				validMovesCount++;
				int score = white(whiteMoves, depth + 1, alpha, beta, v);
				BlackMakeMove.undoMove(game);

				if (score <= alpha) { 
					if (!move.capture())
						history[depth] = move;
					hashtable.put(game.hash(), move);
					return alpha;
				}
				
				if (score < beta) {
					beta = score;
					variant.add(v);
					hashtable.put(game.hash(), move);
				}
			}
			else
				BlackMakeMove.undoMove(game);

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
		
		int positionValue = evaluate();
		if (positionValue <= alpha)
	        return alpha;
	    
		if (positionValue - beta >= pieceValue(BlackQueen))
			return positionValue;

		if (positionValue < beta) 
	        beta = positionValue;
	
		int validMovesCount = 0;

		if (moves.size() > 0) {
		    MoveIterator moveIter = moveIteratorCreator.create(moves.moves(), 0, moves.size(), principal(depth), null);
			Move move;
			while((move = moveIter.next()) != null) {
				assert move.capture();
				
				if (pieceValue(move.capturedPiece().type()) <= positionValue - beta)
					continue;
	
				Variant v = new Variant(move);
				BlackMakeMove.makeMove(game, move);
	
				Moves whiteMoves = game.whiteCaptureMoves();
				if (whiteMoves.kingCaptureCount() == 0) {
					validMovesCount++;
					int score = whiteQuiet(whiteMoves, depth + 1, alpha, beta, v);
					BlackMakeMove.undoMove(game);
	
					if (score <= alpha)
						return alpha;
					
					if (score < beta) {
						beta = score;
						variant.add(v);
					}
				}
				else
					BlackMakeMove.undoMove(game);
			}
		}
		
		if (validMovesCount == 0 && positionValue <= beta && blackIsInCheck()) {
			Moves blackMoves = game.blackMoves();
			assert !blackMoves.kingCaptured();
			int size = blackMoves.size();
			for(int i = 0; i < size; i++) {
				Move move = blackMoves.move(i);
				if (!move.capture()) {
					BlackMakeMove.makeMove(game, move);
					if (!blackIsInCheck()) {
						BlackMakeMove.undoMove(game);
						return beta;
					}
					BlackMakeMove.undoMove(game);
				}
			}
			
			return blackMate(depth);
		}

		return beta;
	}

	private int evaluate() {
		if (nodesCalculator.inc()) {
			info.nodes(nodesCalculator);
			checkTimeout();
		}
		
		return game.evaluate();
	}

	private void checkTimeout() {
		if (now() > stopSearchAt)
			throw new SearchTimeout();
		
	}

	private static long now() {
		return System.currentTimeMillis();
	}
	
	private void currentMove(int size, int i, Move move) {
		info.currentMove(new AnalyzedMove(move, i + 1, size));
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

	public static int blackMate() {
		return blackMate(0);
	}

	public static int blackMate(int depth) {
		return MAX_VALUE - depth;
	}

	public static int whiteMate() {
		return whiteMate(0);
	}
	
	public static int whiteMate(int depth) {
		return MIN_VALUE + depth;
	}
	
}
