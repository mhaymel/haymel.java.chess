/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.search.PieceValue;

public class PVMoveIterator implements MoveIterator { //TODO refactor, unit test

	private static final int statePv = 0;
	private static final int stateCaptures = 1;
	private static final int stateNormal = 2;
	
	private static final int[] order = {
		statePv,
		stateCaptures,
		stateNormal 
	};
	
	private final Game game;
	private final Move[] moves;
	private final int start;
	private final int count;
	private Move pv;
	private int index = 0;
	private int state = 0;
	
	public PVMoveIterator(Game game, Move[] moves, int start, int count, Move pv) { 
		assert game != null;
		assert moves != null;
		assert start >= 0 && start < moves.length;
		assert count > 0;
		assert start + count <= moves.length;
		
		this.game = game;
		this.moves = moves;
		this.start = start;
		this.count = count;
		this.pv = pv;		//pv can be null
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
		case stateNormal:		return nextNormal();
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
		
		int aggressorValue = pieceValue(game.piece(move.from()));
		int victimValue = pieceValue(move.capturedPiece());
		
		return victimValue - aggressorValue;
	}

	private static int pieceValue(Piece piece) {
		return PieceValue.pieceValue(piece.type());
	}

	private Move nextNormal() {
		while(index < count) {
			Move move = move(index);
			if (move != null && !move.capture()) {
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
			return findPv();
		}
		finally {
			pv = null;
		}
	}

	private Move findPv() {
		for(int i = 0; i < count; i++) {
			Move m = move(i);
			if (m != null && m.from() == pv.from() && m.to() == pv.to()) {
				moveReset(i);
				return m;
			}
		}
		return null;
	}

	private Move move(int i) {
		return moves[i + start];
	}

	private void moveReset(int i) {
		moves[i + start] = null;
	}

	private boolean assertAllMoveUsed() {
		for(int i = 0; i < count; i++)
			assert move(i) == null : String.format("was not analyzed: %s", move(i));
		return true;
	}
	
}
