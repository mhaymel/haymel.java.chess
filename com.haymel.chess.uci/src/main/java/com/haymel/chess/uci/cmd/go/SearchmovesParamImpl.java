/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import com.haymel.chess.uci.Moves;
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
