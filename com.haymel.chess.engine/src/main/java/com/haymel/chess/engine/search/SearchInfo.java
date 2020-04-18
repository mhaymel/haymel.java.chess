/***************************************************
 * (c) Markus Heumel
 *
 * @date:	18.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

public interface SearchInfo {		

	static final SearchInfoImpl noopSearchInfo = new SearchInfoImpl(x -> {}, x -> {}, x -> {}, x -> {});

	default void currentMove(AnalyzedMove move) { }
	default void bestMoveConsumer(BestMove move) { }
	default void searchDepth(int depth) { }
	default void nodes(Nodes nodes) { }
	
}

