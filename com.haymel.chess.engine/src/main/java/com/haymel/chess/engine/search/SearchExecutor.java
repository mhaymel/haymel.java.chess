/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	18.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;
import static java.lang.Long.MAX_VALUE;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

import com.haymel.chess.engine.moves.Move;

public class SearchExecutor {

	private static final long nodesPerSecCalcPeriodInMillis = 100;
	private final Search search;
	private final Consumer<Move> bestMove;
	private final Consumer<NodeStatistics> nodeStatistic;
	private final ExecutorService executor;
	private final ScheduledExecutorService scheduledExecutor;
	private Future<?> submit;
	private ScheduledFuture<?> scheduledFuture;
	private long lastNodeCount;
	
	public SearchExecutor(Search search, Consumer<Move> bestMove, Consumer<NodeStatistics> nodeStatistic) {
		this.search = nonNull(search, "search");
		this.bestMove = nonNull(bestMove, "bestMove");
		this.nodeStatistic = nonNull(nodeStatistic, "nodeStatistic");
		this.executor = Executors.newFixedThreadPool(1);
		this.scheduledExecutor = Executors.newScheduledThreadPool(1);
	}
	
	public synchronized void go(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		stop();
		
		lastNodeCount = 0;
		submit = executor.submit(search(wtimeInMilliSeconds, btimeInMilliSeconds));
		scheduledFuture = scheduledExecutor.scheduleAtFixedRate(nodesPerSecondsearch(), 1000, nodesPerSecCalcPeriodInMillis, MILLISECONDS);
	}
	
	public synchronized void stop() {
		stopNodesPerSecond();
		stopSearch();
	}

	private void stopSearch() {
		if (submit == null || submit.isDone()) 
			return;
		
		search.stop();
		try {
			submit.get(MAX_VALUE, TimeUnit.HOURS);
		} 
		catch (InterruptedException | ExecutionException | TimeoutException e) {		// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		submit = null;
	}
	
	private void stopNodesPerSecond() {
		if (scheduledFuture == null || scheduledFuture.isDone())
			return;
		
		scheduledFuture.cancel(false);
		scheduledFuture = null;
	}

	private Runnable search(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		return () -> {
			bestMove.accept(search.execute(wtimeInMilliSeconds, btimeInMilliSeconds));
			stopNodesPerSecond();
		};
	}
	
	private Runnable nodesPerSecondsearch() {
		return () -> {
			try {
				long currentNodeCount = search.nodeCount();
				long diff = currentNodeCount - lastNodeCount;
				assert diff >= 0;
				lastNodeCount = currentNodeCount;
				long nodesPerSecond = (long)(diff/(nodesPerSecCalcPeriodInMillis/1000.0));
				nodeStatistic.accept(new NodeStatistics(currentNodeCount, nodesPerSecond));
			}
			catch(Throwable e) {
				System.out.println(e);
				throw e;
			}
		};
	}
	
}
