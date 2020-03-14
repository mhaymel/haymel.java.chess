/***************************************************
 * (c) Markus Heumel
 *
 * @date:	03.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import com.haymel.chess.engine.moves.Move;

public interface Info { 

	void currentlyAnalyzed(Move move, int moveNumber,  int numberOfPossibleMoves);
	void newBestMoveFound(BestMove move);
	void nodes(Nodes nodes);
	void depth(int depth);
	
};