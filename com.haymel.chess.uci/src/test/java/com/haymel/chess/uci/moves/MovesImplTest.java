/***************************************************
 * (c) Markus Heumel
 *
 * @date:	25.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.moves;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uci.moves.MovesImpl;

public class MovesImplTest {

	@Test
	public void toStringForEmptyList() {
		assertThat(new MovesImpl().toString(), is("MovesImpl()"));
	}

	@Test
	public void toStringForSingleMove() {
		assertThat(new MovesImpl().add("e2e4").toString(), is("MovesImpl(e2e4)"));
	}
	
	@Test
	public void toStringForTwoMoves() {
		MovesImpl moves = new MovesImpl().add("e2e4").add("d7d5");
		assertThat(moves.toString(), is("MovesImpl(e2e4 d7d5)"));
	}
}
