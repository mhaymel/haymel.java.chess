/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.util.Require.nonNull;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.BlackMoves;
import com.haymel.chess.engine.moves.white.WhiteMoves;
import com.haymel.chess.engine.piece.PieceType;

public class Search {

	private final Game game;
	private int maxDepth;
	private Move bestMove;
	
	public Search(Game game) {
		this.game = nonNull(game, "game");
		this.bestMove = null; 
	}
	
	public Move execute(int depth) {
		this.bestMove = null; 
		maxDepth = depth;
		int value = 0;
		switch(game.activeColor()) {
		case black:
			value = black(0);
			break;
		case white:
			value = white(0);
			break;
		default:
			assert false;
		}
		
//		out.println(format("bestmove: %s, value: %s", bestMove, value));
		return bestMove;
	}

	private int white(int depth) {
		Board board = game.board();
		PieceList pieces = game.whitePieces();
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(board, moves);
		whiteMoves.generate(pieces, game.enPassant());
		
		if (moves.kingCaptureCount() > 0)
			return MAX_VALUE - depth;
		
		if (depth == maxDepth)
			return evaluate(game) + moves.size();
		
		int maxValue = MIN_VALUE + depth;
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);

//			if (depth == 0)
//				out.println(format("%s: current: %s, best: %s, value: %s", size - i, move.toString(), bestMove, maxValue));
		
			makeMove.makeMove(move);
			int currentValue = black(depth + 1);
			
			if (currentValue > maxValue) {
				maxValue = currentValue;
				if (depth == 0) {
					bestMove = move;
				}
			}
			
			makeMove.undoMove();
		}
		
		return maxValue;
	}

	private int black(int depth) {
		Board board = game.board();
		PieceList pieces = game.blackPieces();
		Moves moves = new Moves();
		BlackMoves blackMoves = new BlackMoves(board, moves);
		blackMoves.generate(pieces, game.enPassant());

		if (moves.kingCaptureCount() > 0)
			return MIN_VALUE + depth;
		
		if (depth == maxDepth)
			return evaluate(game) - moves.size();
		
		int minValue = MAX_VALUE - depth;
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			
//			if (depth == 0)
//				out.println(format("%s: current: %s, best: %s, value: %s", size - i, move.toString(), bestMove, minValue));
			
			makeMove.makeMove(move);
			
			int currentValue = white(depth + 1);
			
			if (currentValue < minValue) {
				minValue = currentValue;
				if (depth == 0) {
					bestMove = move;
				}
			}
						
			white(depth + 1);
			makeMove.undoMove();
		}
		
		return minValue;
	}
	
	private int evaluate(Game game) {
		return pieceValues(game.whitePieces(), game.blackPieces());
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
