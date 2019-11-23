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

	private final int value;
	
	public UnsignedInt(int seconds) {
		this.value = greaterEqualZero(seconds, "seconds");
	}
	
	@Override
	public boolean defined() {
		return true;
	}

	@Override
	public int value() {
		return value;
	}

}
