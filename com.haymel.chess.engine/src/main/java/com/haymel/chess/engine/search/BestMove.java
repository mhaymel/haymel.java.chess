/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

public class BestMove {		//TODO unit test

	private final Variant variant;
	private final int value;
	
	public BestMove(Variant variant, int value) {
		this.variant = nonNull(variant, "variant");
		this.value = value;
	}
	
	public Move move() {
		return variant.move();
	}
	
	public Variant variant() {
		return variant;
	}
	
	public int value() {
		return value;
	}
	
}
