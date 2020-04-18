/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	12.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.chess.engine.search.SearchAlphaBeta.blackMate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.search.BestMove;

public class IterativeSearchWhitesTurnBlackAlreadyMate {

	private static final String fen = "3R2k1/5ppp/8/8/8/8/8/6K1 w - - 0 1";
	
	@Test
	public void test() {
		Game game = new GameFromFEN(fen).execute();
		
		IterativeSearch search = new IterativeSearch(game);
		BestMove bestMove = search.execute(10_000, 10_000);
		
		assertThat(bestMove.value(), is(blackMate()));
		assertThat(bestMove.variant(), is(nullValue()));
		assertThat(bestMove.mateOrStalemate(), is(true));
	}

}
