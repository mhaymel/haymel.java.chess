/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.castling;

public class CastlingRight {	//TODO unit test

	private boolean kingside;
	private boolean queenside;
	
	public CastlingRight(boolean kingside, boolean queenside) {
		this.kingside = kingside;
		this.queenside = queenside;
	}
	
	public CastlingRight() {
		this(false, false);
	}
	
	public boolean kingside() {
		return kingside;
	}
	
	public boolean queenside() {
		return queenside;
	}

	public void enableKingside() {
		kingside = true;
	}

	public void disableKingside() {
		kingside = false;
	}

	public void enableQueenside() {
		queenside = true;
	}
	
	public void disableQueenside() {
		queenside = false;
	}

	public void rights(CastlingRight right) {
		kingside = right.kingside;
		queenside = right.queenside;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (queenside)
			sb.append('q');
		
		if (kingside)
			sb.append('k');

		return sb.toString();
	}

	public void disable() {
		kingside = false;
		queenside = false;
	}

}
