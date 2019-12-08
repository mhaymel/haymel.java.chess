/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

public final class Border implements BoardElement {

	public static final Border border = new Border();
	
	private Border() {
	}
	
	@Override
	public boolean white() {
		return false;
	}

	@Override
	public boolean black() {
		return false;
	}

	@Override
	public boolean free() {
		return false;
	}

}
