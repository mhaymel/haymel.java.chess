/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	23.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.greaterEqualZero;

public class NodeStatistics {		//TODO unit test

	private final long nodeCount;
	private final long nodesPerSecond;
	
	public NodeStatistics(long nodeCount, long nodesPerSecond) {
		this.nodeCount = greaterEqualZero(nodeCount, "nodeCount");
		this.nodesPerSecond = greaterEqualZero(nodesPerSecond, "nodesPerSecond");
	}
	
	public long nodeCount() {
		return nodeCount;
	}
	
	public long nodesPerSecond() {
		return nodesPerSecond;
	}

}
