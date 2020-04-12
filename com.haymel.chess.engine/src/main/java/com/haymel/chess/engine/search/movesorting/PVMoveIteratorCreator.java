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

public class PVMoveIteratorCreator implements MoveIteratorCreator {	//TODO unit test

	private final Game game;

	public PVMoveIteratorCreator(Game game) {
		assert game != null;
		this.game = game;
	}
	
	@Override
	public MoveIterator create(Move[] moves, int start, int count, Move pv) {
		assert moves != null;
		assert start >= 0 && start < moves.length;
		assert count > 0;
		assert start + count <= moves.length;
		
		return new PVMoveIterator(game, moves, start, count, pv);
	}

}
