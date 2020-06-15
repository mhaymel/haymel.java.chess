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
	private final int count;
	private int index;
	
	public SimpleMoveIterator(Move[] moves, int count) { 
		assert moves != null;
		assert count > 0;
		assert count <= moves.length;
		
		this.moves = moves;
		this.count = count;
		this.index = 0;
	}

	@Override
	public Move next() {
		if (index >= count)
			return null;
		
		return moves[index++];
	}
}
