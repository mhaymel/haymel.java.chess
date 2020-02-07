/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	31.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;
import com.haymel.util.Require;

public class MovesFromVariant {

	private final Variant variant;
	
	public MovesFromVariant(Variant variant) {
		this.variant = Require.nonNull(variant, "variant");
	}
	
	public Move[] value() {
		int size = variant.size(); 
		Move[] moves = new Move[size];
		
		moves[0] = variant.move();
		
		Move[] src = variant.moves();
		if (size > 1)
			System.arraycopy(src, 0, moves, 1, src.length);
		
		return moves;
	}
	
}
