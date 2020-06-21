/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Moves;

class SimpleMoveIteratorCreator implements MoveIteratorCreator {	//TODO unit test

	@Override
	public MoveIterator create(Moves moves, int count, int pv, int killer) {
		return new SimpleMoveIterator(moves, count);
	}

}
