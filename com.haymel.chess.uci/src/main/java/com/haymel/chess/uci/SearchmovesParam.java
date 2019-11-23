/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.exception.HaymelException.throwHE;

public interface SearchmovesParam {

	static final SearchmovesParam undefined = new SearchmovesParam() {};

	default boolean defined() { return false; }
	default Moves value() { return throwHE("value is undefined"); }
	
}
