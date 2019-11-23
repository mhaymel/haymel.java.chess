/***************************************************
 * (c) Markus Heumel
 *
 * @date:	21.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd;

final class IntParamImpl implements IntParam {

	private final long value;
	
	public IntParamImpl(long value) {
		this.value = value;
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
