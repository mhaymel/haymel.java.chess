/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;

public class Variant {			//TODO unit test

	private final int move;
	private int[] moves;
	
	public Variant(int move) {
		Move.validMove(move);
		this.move = move;
	}
	
	public void add(Variant variant) {
		assert variant != null;

		int size = variant.size();
		moves = new int[size];

		assert variant.move != 0;
		moves[0] = variant.move;

		if (size > 1) 
			System.arraycopy(variant.moves, 0, moves, 1, variant.moves.length);
		
		assert verify();
	}

	public int move() {
		return move;
	}
	
	public int[] moves() {
		return moves;
	}
	
	public int size() {
		return (moves == null) ? 1 : moves.length + 1;
	}

	private boolean verify() {
		assert move != 0;
		assert moves != null;
		
		for(int i = 0; i < moves.length; i++)
			assert moves[i] != 0;
		
		return true;
	}

}
