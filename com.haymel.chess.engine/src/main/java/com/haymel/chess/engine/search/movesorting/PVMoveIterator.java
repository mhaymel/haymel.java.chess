/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.enpassant;

import com.haymel.chess.engine.evaluation.PieceValue;
import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.PieceType;

public class PVMoveIterator implements MoveIterator { //TODO refactor, unit test

	private static final int statePv = 0;
	private static final int stateCaptures = 1;
	private static final int stateAll = 2;
	private static final int stateHistoryMove = 3;
	
	private static final int[] order = {
		statePv,
		stateCaptures,
		stateHistoryMove,
		stateAll 
	};
	
	private final Game game;
	private final Move[] moves;
	private final int count;
	private Move pv;
	private Move history;
	private int index = 0;
	private int state = 0;
	
	public PVMoveIterator(Game game, Move[] moves, int count, Move pv, Move history) { 
		assert game != null;
		assert moves != null;
		assert count > 0;
		assert count <= moves.length;
		
		this.game = game;
		this.moves = moves;
		this.count = count;
		this.pv = pv;			//pv can be null
		this.history = history;	//killer can be null
	}

	@Override
	public Move next() {
		while(state < order.length) {
			Move move = find();
			if (move != null)
				return move;
			state++;
			index = 0;
		}
		
		assert assertAllMoveUsed();
		
		return null;
	}
	
	private Move find() {
		switch(order[state]) {
		case statePv: 			return pv();
		case stateCaptures: 	return nextWinningCapture();
		case stateHistoryMove: 	return nextHistoryMove();
		case stateAll:			return nextAll();
		default:
			assert false;
		}
		return null;
	}

	private Move nextWinningCapture() {
		int i = 0;
		Move foundMove = null;
		int foundIndex = 0;
		int foundValue = Integer.MIN_VALUE;
		
		while(i < count) {
			Move move = move(i);
			if (move != null && move.capture()) {
				int value = captureValue(move);
				if (value > foundValue) {
					foundIndex = i;
					foundValue = value;
					foundMove = move;
				}
			}
			i++;
		}
		
		if (foundMove == null)
			return null;

		moveReset(foundIndex);
		return foundMove;
	}
	
	private int captureValue(Move move) {
		assert move.capture();
		
		int aggressorValue = PieceValue.pieceValue(game.piece(move.from()).type());
		int victimValue = PieceValue.pieceValue(victim(move));
		
		return victimValue - aggressorValue;
	}

	private Move nextAll() {
		while(index < count) {
			Move move = move(index);
			if (move != null) {
				moveReset(index);
				index++;
				return move;
			}
			index++;
		}
		return null;
	}

	private Move pv() {
		if (pv == null) 
			return null;
	
		try {
			return findAndMarkItAsUsed(pv);
		}
		finally {
			pv = null;
		}
	}

	private Move nextHistoryMove() {
		if (history == null) 
			return null;
	
		try {
			return findAndMarkItAsUsed(history);
		}
		finally {
			history = null;
		}
	}
	
	private Move findAndMarkItAsUsed(Move move) {
		for(int i = 0; i < count; i++) {
			Move m = move(i);
			if (m != null && m.from() == move.from() && m.to() == move.to()) {
				moveReset(i);
				return m;
			}
		}
		return null;
	}

	private Move move(int i) {
		return moves[i];
	}

	private void moveReset(int i) {
		moves[i] = null;
	}

	private boolean assertAllMoveUsed() {
		for(int i = 0; i < count; i++)
			assert move(i) == null : String.format("was not analyzed: %s", move(i));
		return true;
	}

	private int victim(Move move) {
		int type = 0;
		switch(move.type()) {
		case capture:
		case capturePromotionQueen:
		case capturePromotionRook:
		case capturePromotionBishop:
		case capturePromotionKnight:
		case captureKingMove:
		case captureRookMove:
			type = game.piece(move.to()).type();
			break;
		case enpassant:
			type = game.piece(game.activeColor() == ActiveColor.white ? down(game.enPassant()) : up(game.enPassant())).type();
			break;
		default:
			assert false;
		}

		assert PieceType.pieceTypeValid(type);
		
		return type;
	}
	
}
