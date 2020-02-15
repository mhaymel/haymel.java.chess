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

import com.haymel.chess.engine.game.Game;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class HalfmoveClockTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new HalfmoveClock(null, "0");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new HalfmoveClock(game, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new HalfmoveClock(game, "");
	}

	@Test
	public void halfmoveClock() {
		new HalfmoveClock(game, "0").execute();
		assertThat(game.halfMoveClock(), is(0));

		new HalfmoveClock(game, "17").execute();
		assertThat(game.halfMoveClock(), is(17));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalValueAsHalfmoveThrowsException() {
		new HalfmoveClock(game, "a3").execute();
	}

}
