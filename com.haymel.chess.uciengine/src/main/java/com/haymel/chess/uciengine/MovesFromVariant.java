/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.StringFromMove;
import com.haymel.chess.engine.search.Variant;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.moves.MovesImpl;

public class MovesFromVariant {		//TODO unit test

	private final Variant variant;
	
	public MovesFromVariant(Variant variant) {
		this.variant = nonNull(variant, "variant");
	}
	
	public Moves value() {
		MovesImpl moves = new MovesImpl();
		moves.add(asString(variant.move()));
		
		Move[] m = variant.moves();
		if (m == null)
			return moves;
		
		for(int i = 0; i < m.length; i++)
			moves.add(asString(m[i]));
		
		return moves;
	}

	private String asString(Move move) {
		return new StringFromMove(move).value();
	}
	
}
