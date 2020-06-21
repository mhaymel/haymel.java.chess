/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	05.06.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;

public class Hashtable {							//TODO unit test

	private long count = 0;
	private long hit = 0;
	
//	private static final int size = 1_000_000_007;
	private static final int size = 1;
	private final int[] moves = new int[size];
	
	public int get(long hash) {
		count++;
		
		int move = moves[index(hash)];
		if (move != 0)
			hit++;
		
		Move.validMove(move);
		
		return move;
	}

	public void put(long hash, int move) {
		Move.validMove(move);
		moves[index(hash)] = move;
	}
	
	private int index(long hash) {
		return (int)(((hash % size) + size) % size);
	}
	
	public long count() {
		return count;
	}
	
	public long hit() {
		return hit;
	}
	
}
