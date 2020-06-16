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
	private static final int stateWinningCaptures = 1;
	private static final int stateCaptures = 2;
	private static final int stateAll = 3;
	private static final int stateHistoryMove = 4;
	private static final int stateNonCapture = 5;
	
	private static final int[] order = {
		statePv,
		stateWinningCaptures,
		stateHistoryMove,
		stateCaptures,
		stateNonCapture,
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
		if (index >= count)
			return null;
		
		assert index < moves.length;
		assert state < order.length;
		
		while(state < order.length) {
			Move move = find();
			if (move != null) {
				assert moves[index] == move;
				index++;
				return move;
			}
			state++;
		}
		
		assert false; 
		return null;
	}
	
	private Move find() {
		switch(order[state]) {
		case statePv: 				return pv();
		case stateWinningCaptures: 	return nextWinningCapture();
		case stateCaptures: 		return nextCapture();
		case stateHistoryMove: 		return nextHistoryMove();
		case stateNonCapture: 		return nextNonCapture();
		case stateAll:				return moves[index];
		default:
			assert false;
		}
		return null;
	}

	private Move nextWinningCapture() {
		boolean found = false;
		int foundValue = Integer.MIN_VALUE;

		for(int i = index; i < count; i++) {
			Move move = moves[i];
		
			if (move.capture()) {
				int aggressorValue = PieceValue.pieceValue(game.piece(move.from()).type());
				int victimValue = PieceValue.pieceValue(victim(move));
				if (aggressorValue < victimValue) {
					int value = victimValue - aggressorValue;
					if (value > foundValue) {
						swap(i);
						found = true;
						foundValue = value;
					}
				}
			}
		}
		
		if (found)
			return moves[index];

		return null;
	}

	private Move nextNonCapture() {
		for(int i = index; i < count; i++) {
			Move move = moves[i];
		
			if (!move.capture()) {
				swap(i);
				return move;
			}
		}
		return null;
	}

	
	private Move nextCapture() {
		boolean found = false;
		int foundValue = Integer.MIN_VALUE;

		for(int i = index; i < count; i++) {
			Move move = moves[i];
		
			if (move.capture()) {
				int aggressorValue = PieceValue.pieceValue(game.piece(move.from()).type());
				int victimValue = PieceValue.pieceValue(victim(move));
				int value = victimValue - aggressorValue;
				if (value > foundValue) {
					swap(i);
					found = true;
					foundValue = value;
				}
			}
		}
		
		if (found)
			return moves[index];

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
		for(int i = index; i < count; i++) {
			Move m = moves[i];
			if (m != null && m.from() == move.from() && m.to() == move.to()) {
				swap(i);
				return m;
			}
		}
		return null;
	}

	private void swap(int i) {
		Move tmp = moves[i];
		moves[i] = moves[index];
		moves[index] = tmp;
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
