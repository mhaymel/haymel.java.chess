/***************************************************
 * (c) Markus Heumel
 *
 * @date:	28.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;
import static java.lang.System.out;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.haymel.chess.engine.moves.StringFromMove;

public class SearchInfoImpl implements SearchInfo {		//TODO rename, refactor, unit test

	public static final SearchInfoImpl sysoutSearchInfo = 
		new SearchInfoImpl(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());

	public static final SearchInfoImpl sysoutSearchInfoNoCurrentMoveNoNodeStatistics = 
			new SearchInfoImpl(x -> {}, bestMoveConsumer(), depthConsumer(), x -> {});
	
	private final Consumer<AnalyzedMove> currentMoveConsumer;
	private final Consumer<BestMove> bestMoveConsumer;
	private final IntConsumer depthConsumer;
	private final Consumer<Nodes> nodesConsumer;

	public SearchInfoImpl(Consumer<AnalyzedMove> currentMoveConsumer, Consumer<BestMove> bestMoveConsumer, IntConsumer depthConsumer, Consumer<Nodes> nodesConsumer) {
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

	public static Consumer<Nodes> nodeStatisticsConsumer() {
		return (ns) -> out.println("nodes: " + ns.count() + ", nps: " + ns.nps());
	}

	public static IntConsumer depthConsumer() {
		return (int depth) -> out.println("depth: " + depth);
	}

	public static Consumer<BestMove> bestMoveConsumer() {
		return (bm) -> out.println("bestmove: " + asString(bm.variant()) + ", value: " + bm.value());
	}

	public static Consumer<AnalyzedMove> currentMoveConsumer() {
		return (cm) -> 
			out.println(format("%s: %s/%s", asString(cm.move()), cm.moveNumber(), cm.numberOfPossibleMoves())); 
	}
	
	private static String asString(int move) {
		return new StringFromMove(move).value();
	}
	
	private static String asString(Variant variant) {
		StringBuffer sb = new StringBuffer();
		sb.append(asString(variant.move()));
		
		if (variant.moves() != null)
			for(int move: variant.moves()) 
				sb.append(" ").append(asString(move));
		
		return sb.toString();
	}
	
}

