/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

public class Variant {			//TODO unit test

	private final Move move;
	private Move[] moves;
	
	public Variant(Move move) {
		this.move = nonNull(move, "move");
	}
	
	public void add(Variant variant) {
		assert variant != null;

		int size = variant.size();
		moves = new Move[size];

		assert variant.move != null;
		moves[0] = variant.move;

		if (size > 1) 
			System.arraycopy(variant.moves, 0, moves, 1, variant.moves.length);
		
		assert verify();
	}

	public Move move() {
		return move;
	}
	
	public Move[] moves() {
		return moves;
	}
	
	public int size() {
		return (moves == null) ? 1 : moves.length + 1;
	}

	private boolean verify() {
		assert move != null;
		assert moves != null;
		
		for(int i = 0; i < moves.length; i++)
			assert moves[i] != null;
		
		return true;
	}

}
