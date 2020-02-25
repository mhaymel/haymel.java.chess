/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	21.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class IterativeSearch implements Search {

	private long nodeCount;
	private final Game game;
	private volatile boolean stop;
	private final IntConsumer depthConsumer;
	private SearchAlphaBeta2 search;
	
	public IterativeSearch(Game game, Consumer<CurrentMove> currentMoveConsumer, IntConsumer depthConsumer, Consumer<BestMove> bestMoveConsumer) {
		this.nodeCount = 0;
		this.game = nonNull(game, "game");
		this.stop = false;
		this.depthConsumer = nonNull(depthConsumer, "depthConsumer");
		search = new SearchAlphaBeta2(game, currentMoveConsumer, bestMoveConsumer);
	}
	
	@Override
	public Move execute(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		try {
			return doExecute(wtimeInMilliSeconds, btimeInMilliSeconds);
		}
		catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private Move doExecute(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		nodeCount = 0;
		stop = false;
		long maxCalcTime = new TimeCalculator(game, wtimeInMilliSeconds, btimeInMilliSeconds).value();
		long start = now();
		
		depthConsumer.accept(1);
		BestMove bestMove = search.execute(1);
		updateNodeCount();
		
		for(int depth = 2; ; depth++) {
			Move[] pv = new MovesFromVariant(bestMove.variant()).value();
			depthConsumer.accept(depth);
			bestMove = search.execute(depth, pv);
			updateNodeCount();
			if (bestMove == null)
				return null;
			
			if (stop)
				return bestMove.move();
			
			if (!timeLeft(maxCalcTime, now() - start, depth)) 
				return bestMove.move();
		}
	}

	@Override
	public synchronized long nodeCount() {
		return nodeCount + search.nodeCount();
	}

	@Override
	public void stop() {
		search.stop();
		stop = true;
	}

	private synchronized void updateNodeCount() {
		nodeCount += search.nodeCount();
		search.resetNodeCount();
	}

	private boolean timeLeft(long maxCalcTime, long elapsed, int depth) {
		System.out.println("elapsed: " + elapsed + "    maxCalcTime: " + maxCalcTime);
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
