/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	23.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.util.Require.nonNull;

import java.util.function.Consumer;

public class NodeStatistics {		//TODO refactor, unit test

	private static final int reportsPerSecond = 5;
	
	private final Consumer<NodeStatistics> consumer;
	private long count;
	private long lastTimestamp;
	private long lastCount;
	private long nps = 100_000L;
	
	public NodeStatistics(Consumer<NodeStatistics> consumer) {
		this.consumer = nonNull(consumer,  "npsConsumer");
		this.count = 0;
		this.lastTimestamp = now();
		this.lastCount = 0;
	}
	
	public void inc() {
		count++;
		
		if (count % (nps / reportsPerSecond) != 0)
			return;
		
		long now = now();

		if (now == lastTimestamp)
			return;
		
		nps = (count - lastCount)*1000 / (now - lastTimestamp);
		lastTimestamp = now;
		lastCount = count;
		consumer.accept(this);
	}
	
	public long count() {
		return count;
	}

	public long nps() {
		return nps;
	}
	
	private static long now() {
		return System.currentTimeMillis();
	}
	
}
