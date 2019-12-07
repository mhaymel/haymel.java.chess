/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import com.haymel.chess.uci.moves.Moves;
import com.haymel.util.Require;

public class SearchmovesParamImpl implements SearchmovesParam {

	private final Moves moves;
	
	public boolean defined() { 
		return true; 
	}
	
	public SearchmovesParamImpl(Moves moves) {
		this.moves = Require.nonNull(moves, "moves");
	}
	
	public Moves moves() { 
		return moves;
	}
	
}
