/***************************************************
 * (c) Markus Heumel
 *
 * @date:	22.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static java.lang.String.join;

import java.util.ArrayList;
import java.util.List;

import com.haymel.util.SimpleClassNameMixin;

public class MovesImpl implements Moves, SimpleClassNameMixin {

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

	@Override
	public int hashCode() {
		return moves.hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", simpleClassName(), join(" ", moves));
	}
	
}
