/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.greaterEqualZero;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

public class BestMove {		//TODO unit test

	private final Variant variant;
	private final int value;
	private final int depth;
	private final int selDepth;
	
	public BestMove(Variant variant, int value, int depth, int selDepth) {
		this.variant = nonNull(variant, "variant");
		this.value = value;
		this.depth = greaterEqualZero(depth, "depth");
		this.selDepth = greaterEqualZero(selDepth, "selDepth");
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
	
	public int depth() {
		return depth;
	}
	
	public int selDepth() {
		return selDepth;
	}
	
}
