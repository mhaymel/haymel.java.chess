/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static com.haymel.util.exception.HaymelException.throwHE;

import com.haymel.chess.uci.moves.Moves;

public interface SearchmovesParam {

	static final SearchmovesParam undefined = new SearchmovesParam() {};

	default boolean defined() { return false; }
	default Moves moves() { return throwHE("value is undefined"); }
	
}
