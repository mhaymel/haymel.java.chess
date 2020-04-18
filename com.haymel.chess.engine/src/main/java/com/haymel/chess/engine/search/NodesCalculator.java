/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	23.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

public class NodesCalculator implements Nodes {		//TODO refactor, unit test

	private long count;
	private long lastTimestamp;
	private long lastCount;
	private long nps = 1L;
	private final double reportsPerSecond;

	public NodesCalculator() {
		this(0.25);
	}
	
	public NodesCalculator(double reportsPerSecond) {
		this.reportsPerSecond = reportsPerSecond;
		this.count = 0;
		this.lastTimestamp = now();
		this.lastCount = 0;
	}
	
	public boolean inc() {
		count++;
		
		long divider = (long)(nps / reportsPerSecond);
		if (divider == 0)
			return false;
		
		if (count % divider != 0)	
			return false;
		
		long now = now();

		if (now == lastTimestamp)
			return false;
		
		nps = (count - lastCount)*1000 / (now - lastTimestamp);
		lastTimestamp = now;
		lastCount = count;
		
		return true;
	}
	
	@Override
	public long count() {
		return count;
	}

	@Override
	public long nps() {
		return nps;
	}
	
	private static long now() {
		return System.currentTimeMillis();
	}
	
}
