/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	18.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import com.haymel.chess.engine.search.result.Result;

public interface Search {

	Result execute(int wtimeInMilliSeconds, int btimeInMilliSeconds);

	void stop();
	
}
