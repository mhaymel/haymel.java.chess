/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	21.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.chess.engine.search.SearchInfo.noopSearchInfo;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.MovesFromVariant;
import com.haymel.chess.engine.search.NodesCalculator;
import com.haymel.chess.engine.search.SearchAlphaBeta;
import com.haymel.chess.engine.search.SearchInfo;
import com.haymel.chess.engine.search.TimeCalculator;
import com.haymel.chess.engine.search.movesorting.PVMoveIteratorCreator;

public class IterativeSearch implements Search {  	//TODO unit test

	private final Game game;
	private volatile boolean stop;
	private SearchAlphaBeta search;
	
	public IterativeSearch(Game game) {
		this(game, noopSearchInfo);
	}
	
	public IterativeSearch(Game game, SearchInfo info) {
		this(game, info, new NodesCalculator());
	}
	
	public IterativeSearch(Game game, SearchInfo info, NodesCalculator nodesCalculator) {
		this.game = nonNull(game, "game");
		this.stop = false;
		search = new SearchAlphaBeta(game, info, nodesCalculator, new PVMoveIteratorCreator(game));
	}
	
	@Override
	public BestMove execute(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		stop = false;
		long maxCalcTime = new TimeCalculator(game, wtimeInMilliSeconds, btimeInMilliSeconds).value();
		long start = now();
		
		BestMove bestMove = search.execute(1);
		if (bestMove.mateOrStalemate())
			return bestMove;
		
		for(int depth = 2; ; depth++) {
			Move[] pv = new MovesFromVariant(bestMove.variant()).value();
			bestMove = search.execute(depth, pv);
			
			if (stop)
				return bestMove;
			
			if (!timeLeft(maxCalcTime, now() - start, depth)) 
				return bestMove;
		}
	}

	@Override
	public void stop() {
		search.stop();
		stop = true;
	}

	private boolean timeLeft(long maxCalcTime, long elapsed, int depth) {
		if (elapsed >= maxCalcTime)
			return false;
		
		if (depth == 1)
			return true;
		
		if (elapsed == 0)
			return true;
		
		double factor = Math.pow(elapsed, 1.0/depth);
		
		return maxCalcTime / elapsed > factor;
	}

	private static long now() {
		return System.currentTimeMillis();
	}

}
