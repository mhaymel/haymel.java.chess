/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Move;

class SimpleMoveIteratorCreator implements MoveIteratorCreator {	//TODO unit test

	@Override
	public MoveIterator create(Move[] move, int start, int count, Move pv, Move killer) {
		return new SimpleMoveIterator(move, start, count);
	}

}
