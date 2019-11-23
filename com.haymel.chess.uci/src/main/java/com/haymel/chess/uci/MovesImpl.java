/***************************************************
 * (c) Markus Heumel
 *
 * @date:	22.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import java.util.ArrayList;
import java.util.List;

public class MovesImpl implements Moves {

	public static final MovesImpl emptyMoves = new MovesImpl();
	
	private final List<String> moves;
	
	public MovesImpl() {
		moves = new ArrayList<>();
	}
	
	public MovesImpl add(String move) {
		moves.add(move);
		return this;
	}
	
	@Override
	public List<String> value() {
		return moves;
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof MovesImpl))
			return false;
		
		return ((MovesImpl)obj).value().equals(moves);
	}
	
}
