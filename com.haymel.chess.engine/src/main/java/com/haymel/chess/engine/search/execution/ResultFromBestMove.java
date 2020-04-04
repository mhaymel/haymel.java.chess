/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.MovesFromVariant;
import com.haymel.chess.engine.search.Variant;
import com.haymel.chess.engine.search.result.Normal;
import com.haymel.chess.engine.search.result.Result;

class ResultFromBestMove {			//TODO unit test

	private final ActiveColor activeColor;
	private final BestMove bestMove;
	
	ResultFromBestMove(ActiveColor activeColor, BestMove bestMove) {
		this.activeColor = nonNull(activeColor, "activeColor");
		this.bestMove = nonNull(bestMove, "bestMove");
	}
	
	Result value() {
		Variant variant = bestMove.variant();
		return new Normal(bestMove.value(), new MovesFromVariant(variant).value());
	}
	
	
	
}
