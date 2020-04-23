/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

class PVMoveIteratorNoHistoryCreator implements MoveIteratorCreator {	//TODO unit test

	private final Game game;

	public PVMoveIteratorNoHistoryCreator(Game game) {
		assert game != null;
		this.game = game;
	}
	
	@Override
	public MoveIterator create(Move[] moves, int start, int count, Move pv, Move history) {
		assert moves != null;
		assert start >= 0 && start < moves.length;
		assert count > 0;
		assert start + count <= moves.length;
		
		return new PVMoveIterator(game, moves, start, count, pv, null);
	}

}
