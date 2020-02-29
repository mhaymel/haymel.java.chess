/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	17.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.greaterEqualZero;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;

public class BestMove {		//TODO rename, refactor, unit test

	private final Variant variant;
	private final int value;
	private final int depth;
	private final int selDepth;
	private final NodeStatistics nodeStatistics;
	
	public BestMove(Variant variant, int value, int depth, int selDepth, NodeStatistics nodeStatistics) {
		this.variant = nonNull(variant, "variant");
		this.value = value;
		this.depth = greaterEqualZero(depth, "depth");
		this.selDepth = greaterEqualZero(selDepth, "selDepth");
		this.nodeStatistics = nonNull(nodeStatistics, "nodeStatistics");
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
	
	public NodeStatistics nodeStatistics() {
		return nodeStatistics;
	}
	
}
