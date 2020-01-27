/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.PieceType;

public class SearchAlphaBeta2 {

	private static final int MIN_VALUE = Integer.MIN_VALUE;
	private static final int MAX_VALUE = Integer.MAX_VALUE;
	
	private final Game game;
	private int maxDepth;
	private BestMove bestMove;
	private volatile boolean stop;
	private final AtomicLong nodeCount;
	private final Consumer<CurrentMove> currentMoveConsumer;
	private final Consumer<BestMove> bestMoveConsumer;
	
	public SearchAlphaBeta2(Game game, Consumer<CurrentMove> currentMoveConsumer, Consumer<BestMove> bestMoveConsumer) {
		this.game = nonNull(game, "game");
		this.bestMove = null;
		this.stop = false;
		this.nodeCount = new AtomicLong(0);
		this.currentMoveConsumer = nonNull(currentMoveConsumer, "currentMoveConsumer");
		this.bestMoveConsumer = nonNull(bestMoveConsumer, "bestMoveConsumer");
	}
	
	public BestMove execute(int depth) {
		try {
			return doExecute(depth);
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			throw e;
		}
	}

	private BestMove doExecute(int depth) {
		stop = false;
		nodeCount.getAndSet(0);
		bestMove = null; 
		maxDepth = depth;
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
		
		return bestMove;
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

	private void white(int alpha, int beta) {
		Moves moves = game.whiteMoves();
		if (moves.kingCaptureCount() > 0)
			return;

		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);

			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = black(1, alpha, beta, v);
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
		assert depth <= maxDepth : format("depth: %s, maxDepth: %s", depth, maxDepth);
		
		Moves moves = game.whiteMoves();
		
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth;

		if (depth >= maxDepth || stop)
			return evaluate();
	
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);

			if (depth == 0)
				currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = black(depth + 1, alpha, beta, v);
			makeMove.undoMove();
			
			if (score >= beta) 
				return beta;
			
			if (score > alpha) {
				alpha = score;
				variant.add(v);
			}
		}
		
		return alpha;
	}

	private void black(int alpha, int beta) {
		Moves moves = game.blackMoves();
		if (moves.kingCaptureCount() > 0)
			return;
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			
			currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = white(1, alpha, beta, v);
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
		assert depth <= maxDepth : format("depth: %s, maxDepth: %s", depth, maxDepth);

		Moves moves = game.blackMoves();

		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth;
		
		if (depth >= maxDepth || stop)
			return evaluate();
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			
			if (depth == 0)
				currentMove(size, i, move);
			
			Variant v = new Variant(move);
			makeMove.makeMove(move);
			int score = white(depth + 1, alpha, beta, v);
			makeMove.undoMove();

			if (score <= alpha) 
				return alpha;
			
			if (score < beta) {
				beta = score;
				variant.add(v);
			}
		}
		
		return beta;
	}

	private int evaluate() {
		nodeCount.incrementAndGet();
		return pieceValues(game.whitePieces(), game.blackPieces());
	}

	private void bestMove(Variant variant, int value) {
		bestMove = new BestMove(variant, value);
		bestMoveConsumer.accept(bestMove);
	}

	private void currentMove(int size, int i, Move move) {
		currentMoveConsumer.accept(new CurrentMove(move, i + 1, size));
	}

	private int pieceValues(PieceList whitePieces, PieceList blackPieces) {
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
