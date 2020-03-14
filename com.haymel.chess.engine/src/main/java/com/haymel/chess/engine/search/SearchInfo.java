/***************************************************
 * (c) Markus Heumel
 *
 * @date:	28.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class SearchInfo {		//TODO rename, refactor, unit test

	private final Consumer<AnalyzedMove> currentMoveConsumer;
	private final Consumer<BestMove> bestMoveConsumer;
	private final IntConsumer depthConsumer;
	private final Consumer<Nodes> nodesConsumer;

	public SearchInfo(Consumer<AnalyzedMove> currentMoveConsumer, Consumer<BestMove> bestMoveConsumer, IntConsumer depthConsumer, Consumer<Nodes> nodesConsumer) {
		this.currentMoveConsumer = nonNull(currentMoveConsumer, "currentMoveConsumer");
		this.bestMoveConsumer = nonNull(bestMoveConsumer, "bestMoveConsumer");
		this.depthConsumer = nonNull(depthConsumer, "depthConsumer");
		this.nodesConsumer = nonNull(nodesConsumer, "nodesConsumer");
	}
	
	public void currentMove(AnalyzedMove move) {
		currentMoveConsumer.accept(move);
	}

	public void bestMoveConsumer(BestMove move) {
		bestMoveConsumer.accept(move);
	}

	public void searchDepth(int depth) {
		depthConsumer.accept(depth);
	}
	
	public void nodes(Nodes nodes) {
		nodesConsumer.accept(nodes);
	}
	
}
