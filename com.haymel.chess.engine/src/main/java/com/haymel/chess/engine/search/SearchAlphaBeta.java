/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.PieceType;

public class SearchAlphaBeta {

	private final Game game;
	private int maxDepth;
	private Move bestMove;
	private volatile boolean stop;
	private final AtomicLong nodeCount;
	private final Consumer<CurrentMove> currentMoveConsumer;
	
	public SearchAlphaBeta(Game game, Consumer<CurrentMove> currentMoveConsumer) {
		this.game = nonNull(game, "game");
		this.bestMove = null;
		this.stop = false;
		this.nodeCount = new AtomicLong(0);
		this.currentMoveConsumer = nonNull(currentMoveConsumer, "currentMoveConsumer");
	}
	
	public BestMove execute(int depth) {
		stop = false;
		nodeCount.getAndSet(0);
		bestMove = null; 
		maxDepth = depth;
		int value = 0;
		switch(game.activeColor()) {
		case black:
			value = black(0, MIN_VALUE, MAX_VALUE);
			break;
		case white:
			value = white(0, MIN_VALUE, MAX_VALUE);
			break;
		default:
			assert false;
		}
		
		return null;
		//return new BestMove(bestMove, value);
	}

	public void stop() {
		stop = true;
	}
	
	public long nodeCount() {
		return nodeCount.get();
	}

	public void resetNodeCount() {
		nodeCount.getAndSet(0);
	}

	private int white(int depth, int alpha, int beta) {
		assert depth <= maxDepth : format("depth: %s, maxDepth: %s", depth, maxDepth);
		
		if (stop) 
			return alpha;
		
		if (depth >= maxDepth)
			return whiteQuiet(depth, alpha, beta);
		
		Moves moves = game.whiteMoves();
		
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth;
				
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);

			if (depth == 0)
				currentMove(size, i, move);
			
			makeMove.makeMove(move);
			int score = black(depth + 1, alpha, beta);
			makeMove.undoMove();
			
			if (score >= beta) 
				return beta;
			
			if (score > alpha) {
				alpha = score;
				if (depth == 0) {
					bestMove = move;
				}
			}
		}
		
		return alpha;
	}

	private void currentMove(int size, int i, Move move) {
		currentMoveConsumer.accept(new CurrentMove(move, i + 1, size));
	}

	private int whiteQuiet(int depth, int alpha, int beta) {
		assert depth >= maxDepth;
		
		if (stop) 
			return alpha;
		
		Moves moves = game.whiteCaptureMoves();
		
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth;
		
		nodeCount.incrementAndGet();
		
		int stand_pat = evaluate(game);
	    
		if( stand_pat >= beta )
	        return beta;

	    if( alpha < stand_pat )
	        alpha = stand_pat;

		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			assert capture(move);
			
			if (depth == 0)
				currentMove(size, i, move);
		
			makeMove.makeMove(move);
			int score = blackQuiet(depth + 1, alpha, beta);
			makeMove.undoMove();
			
			if (score >= beta) 
				return beta;
			
			if (score > alpha) {
				alpha = score;
				if (depth == 0) 
					bestMove = move;
			}
		}
	    
		return alpha;
	}

	private int black(int depth, int alpha, int beta) {
		assert depth <= maxDepth : format("depth: %s, maxDepth: %s", depth, maxDepth);

		if (stop) 
			return beta;

		if (depth >= maxDepth)
			return blackQuiet(depth, alpha, beta);
		
		Moves moves = game.blackMoves();

		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth;
		
		if (depth == maxDepth)
			return evaluate(game);
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			
			if (depth == 0)
				currentMove(size, i, move);
			
			makeMove.makeMove(move);
			int score = white(depth + 1, alpha, beta);
			makeMove.undoMove();

			if (score <= alpha) 
				return alpha;
			
			if (score < beta) {
				beta = score;
				if (depth == 0) {
					bestMove = move;
				}
			}
		}
		
		return beta;
	}

	private int blackQuiet(int depth, int alpha, int beta) {
		assert depth >= maxDepth;

		if (stop) 
			return beta;
		
		Moves moves = game.blackCaptureMoves();

		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth;

		int stand_pat = evaluate(game);

		nodeCount.incrementAndGet();
		
		if (stand_pat <= alpha)
	        return alpha;
	    
		if (beta > stand_pat)
	        beta = stand_pat;
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			
			if (depth == 0)
				currentMove(size, i, move);
			
			makeMove.makeMove(move);
			int score = whiteQuiet(depth + 1, alpha, beta);
			makeMove.undoMove();

			if (score <= alpha) 
				return alpha;
			
			if (score < beta) {
				beta = score;
				if (depth == 0) {
					bestMove = move;
				}
			}
		}
		
		return beta;
	}
	
	private boolean capture(Move move) {
		assert 
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion : move;
		return true;
	}

	private int evaluate(Game game) {
		return pieceValues(game.whitePieces(), game.blackPieces());
	}

	private int pieceValues(PieceList whitePieces, PieceList blackPieces) {
		
		PieceValue.pieceValue(whitePieces);
		PieceValue.pieceValue(blackPieces);
		int value = 0;
		int size = whitePieces.size();
		for(int i = 0; i < size; i++) {
			switch(whitePieces.piece(i).type()) {
			case WhitePawn:
				value += 100;
				break;
			case WhiteKnight:
				value += 300;
				break;
			case WhiteBishop:
				value += 300;
				break;
			case WhiteQueen:
				value += 1200;
				break;
			case WhiteRook:
				value += 500;
				break;

			case WhiteKing:
				break;
				
			case BlackBishop:
			case BlackKing:
			case BlackKnight:
			case BlackPawn:
			case BlackQueen:
			case BlackRook:
			case Border:
			case Free:
			default:
				assert false;
			}
		}
		
		size = blackPieces.size();
		for(int i = 0; i < size; i++) {
			PieceType type = blackPieces.piece(i).type();
			switch(type) {
			case BlackPawn:
				value -= 100;
				break;
			case BlackKnight:
				value -= 300;
				break;
			case BlackBishop:
				value -= 300;
				break;
			case BlackRook:
				value -= 500;
				break;
			case BlackQueen:
				value -= 1200;
				break;

			case BlackKing:
				break;

			case WhiteBishop:
			case WhiteKnight:
			case WhitePawn:
			case WhiteQueen:
			case WhiteRook:
			case WhiteKing:
			case Free:
			case Border:
			default:
				assert false : type;
			}
		}
		
		return value;
	}

}
