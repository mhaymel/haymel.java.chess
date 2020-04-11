/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Move;

public class SimpleMoveIterator implements MoveIterator { //TODO unit test

	private final Move[] moves;
	private final int start;
	private final int count;
	private int index;
	
	public SimpleMoveIterator(Move[] moves, int start, int count) { 
		assert moves != null;
		assert start >= 0 && start < moves.length;
		assert count > 0;
		assert start + count <= moves.length;
		
		this.moves = moves;
		this.start = start;
		this.count = count;
		this.index = 0;
	}

	@Override
	public Move next() {
		if (index >= count)
			return null;
		
		return moves[start + index++];
	}
}
