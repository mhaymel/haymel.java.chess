/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MoveTest {

	@Test
	public void fromAndToReturnValuesSetByConstructor() {
		Move move = new Move(e2, e4, false);
		assertThat(move.from(), is(e2));
		assertThat(move.to(), is(e4));
	}
	
	@Test
	public void testToString() {
		assertThat(new Move(a1, b2, false).toString(), is("a1-b2"));
	}
	
	@Test
	public void testToStringOfCapture() {
		assertThat(new Move(a1, b2, true).toString(), is("a1xb2"));
	}

	
}
