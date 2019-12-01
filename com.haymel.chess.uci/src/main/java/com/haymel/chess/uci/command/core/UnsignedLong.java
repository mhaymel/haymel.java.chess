/***************************************************
 * (c) Markus Heumel
 *
 * @date:	27.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.core;

import static com.haymel.util.Require.greaterEqualZero;

public final class UnsignedLong implements LongParam {

	private final long value;
	
	public UnsignedLong(long seconds) {
		this.value = greaterEqualZero(seconds, "seconds");
	}
	
	@Override
	public boolean defined() {
		return true;
	}

	@Override
	public long value() {
		return value;
	}

}
