/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Position;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class HalfmoveClockTest {

	private Position position;
	
	@Before
	public void setup() {
		position = new Position();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new HalfmoveClock(null, "0");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new HalfmoveClock(position, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new HalfmoveClock(position, "");
	}

	@Test
	public void halfmoveClock() {
		new HalfmoveClock(position, "0").execute();
		assertThat(position.halfMoveClock(), is(0));

		new HalfmoveClock(position, "17").execute();
		assertThat(position.halfMoveClock(), is(17));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalValueAsHalfmoveThrowsException() {
		new HalfmoveClock(position, "a3").execute();
	}

}
