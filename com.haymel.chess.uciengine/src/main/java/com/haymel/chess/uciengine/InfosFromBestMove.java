/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.Variant;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.result.Infos;

class InfosFromBestMove {
	
	private final int activeColor; 
	private final BestMove bestmove;
	
	InfosFromBestMove(int activeColor, BestMove bestmove) {
		this.activeColor = activeColor;
		this.bestmove = nonNull(bestmove, "bestmove");
	}
	
	Infos value() {
		Infos infos = new Infos();
		
		score(infos);
		
		infos
			.depth(bestmove.depth())
			.seldepth(bestmove.selDepth())
			.nodes(bestmove.nodes().count())
			.nps(bestmove.nodes().nps())
			.pv(movesFrom(bestmove.variant()));
			
		return infos;
	}

	private void score(Infos infos) {
		if (bestmove.futureMate()) 
			infos.scoremate(movesTillMate());
		else
			infos.scorecp(score());
	}
	
	private int movesTillMate() {
		int moves = bestmove.pliesTillMate() / 2 + 1;
		return activeColorWillBeMate() ? -moves : moves;
	}

	private boolean activeColorWillBeMate() {
		assert bestmove.futureMate();

		return 
			(activeColor == white && bestmove.whiteWillBeMate()) || 
			(activeColor == black && bestmove.blackWillBeMate()); 
	}

	private int score() {
		assert !bestmove.futureMate();
	
		return activeColor == white 
			? bestmove.value() 
			: - bestmove.value();
	}
	
	private static Moves movesFrom(Variant variant) {
		return new MovesFromVariant(variant).value();
	}

}
