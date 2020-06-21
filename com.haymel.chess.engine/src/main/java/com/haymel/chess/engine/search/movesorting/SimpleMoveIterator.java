/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Moves;

public class SimpleMoveIterator implements MoveIterator { //TODO unit test

	private final Moves moves;
	private final int count;
	private int index;
	
	public SimpleMoveIterator(Moves moves, int count) { 
		assert moves != null;
		assert count > 0;
		
		this.moves = moves;
		this.count = count;
		this.index = 0;
	}

	@Override
	public int next() {
		if (index >= count)
			return 0;
		
		return moves.move(index++);
	}
}
