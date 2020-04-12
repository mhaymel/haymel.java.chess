/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	18.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import com.haymel.chess.engine.search.BestMove;

public interface Search {

	BestMove execute(int wtimeInMilliSeconds, int btimeInMilliSeconds);

	void stop();
	
}
