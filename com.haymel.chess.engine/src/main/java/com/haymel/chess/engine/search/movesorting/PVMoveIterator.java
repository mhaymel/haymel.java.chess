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
import com.haymel.chess.engine.moves.Moves;
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
	private final Moves moves;
	private final int count;
	private int pv;
	private int history;
	private int index = 0;
	private int state = 0;
	
	public PVMoveIterator(Game game, Moves moves, int count, int pv, int history) { 
		assert game != null;
		assert moves != null;
		assert count > 0;
		assert pv == 0 || Move.validMove(pv);
		assert history == 0 || Move.validMove(history);
		
		this.game = game;
		this.moves = moves;
		this.count = count;
		this.pv = pv;			//pv can be null
		this.history = history;	//killer can be null
	}

	@Override
	public int next() {
		if (index >= count)
			return 0;
		
		assert index < moves.size();
		assert state < order.length;
		
		while(state < order.length) {
			if (find()) {
				return moves.move(index++);
			}
			state++;
		}
		
		assert false; 
		return 0;
	}
	
	private boolean find() {
		switch(order[state]) {
		case statePv: 				return pv();
		case stateWinningCaptures: 	return nextWinningCapture();
		case stateCaptures: 		return nextCapture();
		case stateHistoryMove: 		return nextHistoryMove();
		case stateNonCapture: 		return nextNonCapture();
		case stateAll:				return true;
		default:
			assert false;
		}
		return false;
	}

	private boolean nextWinningCapture() {
		boolean found = false;
		int foundValue = Integer.MIN_VALUE;

		for(int i = index; i < count; i++) {
			int move = moves.move(i);
		
			if (Move.capture(move)) {
				int aggressorValue = PieceValue.pieceValue(game.piece(Move.from(move)).type());
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
		
		return found;
	}

	private boolean nextNonCapture() {
		for(int i = index; i < count; i++) {
			if (!Move.capture(moves.move(i))) {
				swap(i);
				return true;
			}
		}
		return false;
	}

	
	private boolean nextCapture() {
		boolean found = false;
		int foundValue = Integer.MIN_VALUE;

		for(int i = index; i < count; i++) {
			int move = moves.move(i);
		
			if (Move.capture(move)) {
				int aggressorValue = PieceValue.pieceValue(game.piece(Move.from(move)).type());
				int victimValue = PieceValue.pieceValue(victim(move));
				int value = victimValue - aggressorValue;
				if (value > foundValue) {
					swap(i);
					found = true;
					foundValue = value;
				}
			}
		}
		
		return found;
	}

	private boolean pv() {
		if (pv == 0) 
			return false;
	
		try {
			return findAndMarkItAsUsed(pv);
		}
		finally {
			pv = 0;
		}
	}

	private boolean nextHistoryMove() {
		if (history == 0) 
			return false;
	
		if (findAndMarkItAsUsed(history)) {
			history = 0;
			return true;
		} 
		
		history = 0;
		return false;
		
	}
	
	private boolean findAndMarkItAsUsed(int move) {
		for(int i = index; i < count; i++) {
			int m = moves.move(i);
			if (Move.from(m) == Move.from(move) && Move.to(m) == Move.to(move)) {
				swap(i);
				return true;
			}
		}
		return false;
	}

	private void swap(int i) {
		moves.swap(i, index);
	}

	private int victim(int move) {
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
			type = game.piece(game.activeColor() == ActiveColor.white ? down(game.enPassant()) : up(game.enPassant())).type();
			break;
		default:
			assert false;
		}

		assert PieceType.pieceTypeValid(type);
		
		return type;
	}
	
}
