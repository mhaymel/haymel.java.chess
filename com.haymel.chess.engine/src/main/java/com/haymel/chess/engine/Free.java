/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

public final class Free implements BoardElement {

	public static final Free free = new Free();
	
	private Free() {
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
		return true;
	}
	
	@Override
	public String toString() {
		return ".";
	}

}
