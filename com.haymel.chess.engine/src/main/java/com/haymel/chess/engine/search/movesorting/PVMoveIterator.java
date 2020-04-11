/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;

public class PVMoveIterator implements MoveIterator { //TODO unit test

	private static final int statePv = 0;
	private static final int stateWinningCaputres = 1;
	private static final int stateNormal = 2;
	private static final int stateLosingCapture = 3;
	
	private static final int[] order = {
		statePv,
		stateWinningCaputres,
		stateNormal, 
		stateLosingCapture
	};
	
	private final Move[] moves;
	private final int start;
	private final int count;
	private Move pv;
	private int index = 0;
	private int state = 0;
	
	public PVMoveIterator(Move[] moves, int start, int count, Move pv) { 
		assert moves != null;
		assert start >= 0 && start < moves.length;
		assert count > 0;
		assert start + count <= moves.length;
		
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
		case statePv: 				return pv();
		case stateWinningCaputres: 	return nextWinningCapture();
		case stateNormal:			return nextNormal();
		case stateLosingCapture:	return nextLosingCapture();
		default:
			assert false;
		}
		return null;
	}

	private Move nextWinningCapture() {
		for(int i = 0; i < count; i++) {
			Move move = move(i);
			if (move != null && isCapture(move)) {
				moveReset(i);
				return move;
			}
		}
		return null;
	}
	
	private Move nextNormal() {
		while(index < count) {
			Move move = move(index);
			if (move != null && !isCapture(move)) {
				moveReset(index);
				index++;
				return move;
			}
			index++;
		}
		return null;
	}

	private Move nextLosingCapture() {
		while(index < count) {
			Move move = move(index);
			if (move != null) {
				assert isCapture(move);
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
			assert move(i) == null;
		return true;
	}
	
	private static boolean isCapture(Move move) {
		return  
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion;
	}
	
}
