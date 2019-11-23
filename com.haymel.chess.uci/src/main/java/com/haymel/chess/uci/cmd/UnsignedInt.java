/***************************************************
 * (c) Markus Heumel
 *
 * @date:	21.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd;

import static com.haymel.util.Require.greaterEqualZero;

public final class UnsignedInt implements IntParam {

	private final long value;
	
	public UnsignedInt(long seconds) {
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
