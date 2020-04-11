/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class SortedMoveIteratorCreator implements MoveIteratorCreator {	//TODO unit test

	private final Game game;
	
	public SortedMoveIteratorCreator(Game game) {
		assert game != null;
		this.game = game;
	}
	
	@Override
	public MoveIterator create(Move[] moves, int start, int count, Move pv) {
		Move[] sortedMoves = new SortMoves(game, moves, count, pv).sort();
		
		return new SimpleMoveIterator(sortedMoves, start, count);
	}

}
