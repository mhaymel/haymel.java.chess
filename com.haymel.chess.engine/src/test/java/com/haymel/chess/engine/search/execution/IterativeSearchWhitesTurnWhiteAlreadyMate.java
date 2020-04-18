/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	12.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.execution;

import static com.haymel.chess.engine.search.SearchAlphaBeta.whiteMate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.search.BestMove;

public class IterativeSearchWhitesTurnWhiteAlreadyMate {

	private static final String fen = "3k4/8/8/8/8/8/5PPP/3r2K1 b - - 0 1";
	
	@Test
	public void test() {
		Game game = new GameFromFEN(fen).execute();
		
		IterativeSearch search = new IterativeSearch(game);
		BestMove bestMove = search.execute(10_000, 10_000);
		
		assertThat(bestMove.value(), is(whiteMate()));
		assertThat(bestMove.variant(), is(nullValue()));
		assertThat(bestMove.mateOrStalemate(), is(true));
		assertThat(bestMove.mateOrStalemate(), is(true));
	}

}
