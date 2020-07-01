/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Moves;

class PVMoveIteratorNoHistoryCreator implements MoveIteratorCreator {	//TODO unit test

	private final Game game;

	public PVMoveIteratorNoHistoryCreator(Game game) {
		assert game != null;
		this.game = game;
	}
	
	@Override
	public MoveIterator create(Moves moves, int count, int pv, int history) {
		assert moves != null;
		assert count > 0;
		
		return new PVMoveIterator(game, moves, count, pv, 0);
	}

}
