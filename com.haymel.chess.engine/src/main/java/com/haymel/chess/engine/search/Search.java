/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	18.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;

public interface Search {

	Move execute(int wtimeInMilliSeconds, int btimeInMilliSeconds);

	long nodeCount();
	
	void stop();
	
}
