/***************************************************
 * (c) Markus Heumel
 *
 * @date:	21.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.parameter;

final class IntParamImpl implements IntParam {

	private final int value;
	
	public IntParamImpl(int value) {
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
