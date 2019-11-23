/***************************************************
 * (c) Markus Heumel
 *
 * @date:	21.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

final class IntParamSpecified implements IntParam {

	private final int value;
	
	public IntParamSpecified(int value) {
		this.value = value;
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
