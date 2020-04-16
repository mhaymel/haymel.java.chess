/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.04.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchAlphaBeta.blackMate;
import static com.haymel.chess.engine.search.SearchAlphaBeta.whiteMate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BestMoveTest {

	private static final int aDepth = 10;
	private static final int aSelDepth = 15;
	
	@Test
	public void futureMateReturnsFalseForValuesInsideTheThresholds() {
		assertThat(bestMove(0).futureMate(), is(false));
		assertThat(bestMove(-1000).futureMate(), is(false));
		assertThat(bestMove(1000).futureMate(), is(false));
	}

	@Test
	public void futureMateReturnsTrueForValuesOutsideTheThresholds() {
		assertThat(bestMove(blackMate()).futureMate(), is(true));
		assertThat(bestMove(blackMate(1)).futureMate(), is(true));
		assertThat(bestMove(blackMate(100)).futureMate(), is(true));

		assertThat(bestMove(whiteMate()).futureMate(), is(true));
		assertThat(bestMove(whiteMate(1)).futureMate(), is(true));
		assertThat(bestMove(whiteMate(100)).futureMate(), is(true));
	}

	@Test
	public void pliesTillMateReturnCorrectValue() {
		assertThat(bestMove(blackMate()).pliesTillMate(), is(0));
		assertThat(bestMove(blackMate(1)).pliesTillMate(), is(1));
		assertThat(bestMove(blackMate(100)).pliesTillMate(), is(100));

		assertThat(bestMove(whiteMate()).pliesTillMate(), is(0));
		assertThat(bestMove(whiteMate(1)).pliesTillMate(), is(1));
		assertThat(bestMove(whiteMate(100)).pliesTillMate(), is(100));
	}

	@Test
	public void testWhiteMate() {
		assertThat(bestMove(whiteMate(1)).whiteWillBeMate(), is(true));
		assertThat(bestMove(whiteMate(2)).whiteWillBeMate(), is(true));
		assertThat(bestMove(blackMate(1)).whiteWillBeMate(), is(false));
		assertThat(bestMove(100).whiteWillBeMate(), is(false));
		assertThat(bestMove(0).whiteWillBeMate(), is(false));
	}

	@Test
	public void testBlackMate() {
		assertThat(bestMove(blackMate(1)).blackWillBeMate(), is(true));
		assertThat(bestMove(blackMate(2)).blackWillBeMate(), is(true));
		assertThat(bestMove(whiteMate(1)).blackWillBeMate(), is(false));
		assertThat(bestMove(100).blackWillBeMate(), is(false));
		assertThat(bestMove(0).blackWillBeMate(), is(false));
	}
	
	private static BestMove bestMove(int value) {
		return new BestMove(null, value, aDepth, aSelDepth, new DummyNodes());
	}
	
}
