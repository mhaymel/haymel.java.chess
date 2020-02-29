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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class SearchExecutor {	//TODO unit test, refactor

	private final Search search;
	private final Consumer<BestMove> bestMove;
	private final ExecutorService executor;
	private Future<?> submit;
	
	public SearchExecutor(Search search, Consumer<BestMove> bestMove) {
		this.search = nonNull(search, "search");
		this.bestMove = nonNull(bestMove, "bestMove");
		this.executor = Executors.newFixedThreadPool(1);
	}
	
	public synchronized void go(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		stop();
		submit = executor.submit(search(wtimeInMilliSeconds, btimeInMilliSeconds));
	}
	
	public synchronized void stop() {
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
	
	private Runnable search(int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		return () -> {
			bestMove.accept(search.execute(wtimeInMilliSeconds, btimeInMilliSeconds));
		};
	}
	
}
