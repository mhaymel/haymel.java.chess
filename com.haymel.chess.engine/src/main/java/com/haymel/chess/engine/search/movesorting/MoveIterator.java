/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import com.haymel.chess.engine.moves.Move;

public interface MoveIterator {

	Move next();
	
}
