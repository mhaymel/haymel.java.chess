/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.evaluation.PieceValue.pieceValue;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.search.SearchInfo.noopSearchInfo;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.black.BlackMakeMove;
import com.haymel.chess.engine.game.white.WhiteMakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.PieceType;
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
	private int[] principalVariation;
	private final NodesCalculator nodesCalculator;	
	private final MoveIteratorCreator moveIteratorCreator;
	private final int[] history = new int[1000];
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
	
	public BestMove execute(int depth, int[] principalVariation, long stopSearchAt) { 
		info.searchDepth(depth);
		return doExecute(depth, principalVariation, stopSearchAt);
	}

	private BestMove doExecute(int depth, int[] principalVariation, long stopSearchAt) {
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
			history[i] = 0;
	}

	public void stop() {
		stop = true;
	}
	
	private BestMove white() {
		int alpha = MIN_VALUE;
		BestMove bestMove = null;
		
		Moves moves = game.whiteMoves();			
		if (moves == null)		
			return blackIsMate();
		
		final int depth = 0;
		
		MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), 0);
		
		int move;
		int i = 0;
		while((move = moveIter.next()) != 0) {
			currentMove(moves.size(), i, move);
			i++;
			
			Variant v = new Variant(move);
			WhiteMakeMove.makeMove(game, move);
			
			
			if (!whiteIsInCheck()) {
				int score = black(depth + 1, alpha, MAX_VALUE, v);
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
	
	private int white(int depth, int alpha, int beta, Variant variant) {
		assert depth >= 1: format("depth: %s, maxDepth: %s", depth, maxDepth);
		assert game.whiteMoves() != null;
		
		if (depth >= maxDepth)
			return whiteQuiet(depth, alpha, beta, variant);
		
		Moves moves = game.whiteMoves();
		MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), history[depth]);

		int validMovesCount = 0;
		int move;
		while((move = moveIter.next()) != 0) {
			Variant v = new Variant(move);
			WhiteMakeMove.makeMove(game, move);

			if (!whiteIsInCheck()) {
				validMovesCount++;
				int score = black(depth + 1, alpha, beta, v);
				WhiteMakeMove.undoMove(game);
				
				if (score >= beta) {
					if (!Move.capture(move))
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

	private int whiteQuiet(int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;
		assert game.whiteMoves() != null;
		
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
		
		Moves moves = game.whiteCaptureMoves();	
		if (moves.size() > 0) {
			MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), 0);
			int move;
			while((move = moveIter.next()) != 0) {
				assert Move.capture(move);
				
				if (pieceValue(victimOfWhiteType(move)) <= alpha - positionValue)
					continue;
	
				Variant v = new Variant(move);
				WhiteMakeMove.makeMove(game, move);
				
				if (!whiteIsInCheck()) {
					validMovesCount++;
					int score = blackQuiet(depth + 1, alpha, beta, v);
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
			assert whiteMoves != null;
			int size = whiteMoves.size();
			for(int i = 0; i < size; i++) {
				int move = whiteMoves.move(i);
				if (!Move.capture(move)) {
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
		if (moves == null)
			return whiteIsMate();

		final int depth = 0;

		MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), 0);
		
		int move;
		int i = 0;
		while((move = moveIter.next()) != 0) {
			currentMove(moves.size(), i, move);
			i++;

			Variant v = new Variant(move);
			BlackMakeMove.makeMove(game, move);
			
			if (!blackIsInCheck()) {
				int score = white(depth + 1, MIN_VALUE, beta, v);
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
	
	private int black(int depth, int alpha, int beta, Variant variant) {
		assert depth > 0: format("depth: %s, maxDepth: %s", depth, maxDepth);
		assert game.blackMoves() != null;

		if (depth >= maxDepth)
			return blackQuiet(depth, alpha, beta, variant);
		
		if (depth >= maxDepth || stop)
			return evaluate();
		
		Moves moves = game.blackMoves();
		MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), history[depth]);

		int validMovesCount = 0;
		int move;
		while((move = moveIter.next()) != 0) {
			Variant v = new Variant(move);
			BlackMakeMove.makeMove(game, move);
			
			if (!blackIsInCheck()) {
				validMovesCount++;
				int score = white(depth + 1, alpha, beta, v);
				BlackMakeMove.undoMove(game);

				if (score <= alpha) { 
					if (!Move.capture(move))
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

	private int blackQuiet(int depth, int alpha, int beta, Variant variant) {
		assert depth >= maxDepth;
		assert game.blackMoves() != null;
		
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

		Moves moves = game.blackCaptureMoves();
		if (moves.size() > 0) {
		    MoveIterator moveIter = moveIteratorCreator.create(moves, moves.size(), principal(depth), 0);
			int move;
			while((move = moveIter.next()) != 0) {
				assert Move.capture(move);
				
				if (pieceValue(victimOfBlackType(move)) <= positionValue - beta)
					continue;
	
				Variant v = new Variant(move);
				BlackMakeMove.makeMove(game, move);
	
				if (!blackIsInCheck()) {
					validMovesCount++;
					int score = whiteQuiet(depth + 1, alpha, beta, v);
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
			assert blackMoves != null;
			int size = blackMoves.size();
			for(int i = 0; i < size; i++) {
				int move = blackMoves.move(i);
				if (!Move.capture(move)) {
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
	
	private void currentMove(int size, int i, int move) {
		Move.validMove(move);
		info.currentMove(new AnalyzedMove(move, i + 1, size));
	}

	private int principal(int depth) {
		if (principalVariation == null)
			return 0;
		
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
		assert 
			!WhiteInCheck.whiteIsInCheck(game.whitePieces().king().field(), game.pieces()) 
			|| 
				(game.blackCaptureMoves() == null 
				 && 
				 game.blackMoves() == null);

		return WhiteInCheck.whiteIsInCheck(game.whitePieces().king().field(), game.pieces());
	}
	
	private boolean blackIsInCheck() {
		assert 
		!BlackInCheck.blackIsInCheck(game.blackPieces().king().field(), game.pieces()) 
		|| 
			(game.whiteCaptureMoves() == null 
			 && 
			 game.whiteMoves() == null);

		return BlackInCheck.blackIsInCheck(game.blackPieces().king().field(), game.pieces());
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
	
	private int victimOfWhiteType(int move) {
		int type = 0;
		switch(Move.type(move)) {
		case capture:
		case capturePromotionQueen:
		case capturePromotionRook:
		case capturePromotionBishop:
		case capturePromotionKnight:
		case captureKingMove:
		case captureRookMove:
			type = game.piece(Move.to(move)).type();
			break;
		case enpassant:
			type = game.piece(down(game.enPassant())).type();
			break;
		default:
			assert false;
		}

		assert PieceType.pieceTypeValid(type);
		assert PieceType.black(type);
		assert type != BlackKing;
		
		return type;
	}

	private int victimOfBlackType(int move) {
		int type = 0;
		switch(Move.type(move)) {
		case capture:
		case capturePromotionQueen:
		case capturePromotionRook:
		case capturePromotionBishop:
		case capturePromotionKnight:
		case captureKingMove:
		case captureRookMove:
			type = game.piece(Move.to(move)).type();
			break;
		case enpassant:
			type = game.piece(Field.up(game.enPassant())).type();
			break;
		default:
			assert false;
		}

		assert PieceType.pieceTypeValid(type);
		assert PieceType.white(type);
		assert type != WhiteKing;
		
		return type;
	}

}
