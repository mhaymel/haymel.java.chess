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

public class FullmoveNumberTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new FullmoveNumber(null, "1");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new FullmoveNumber(game, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new FullmoveNumber(game, "");
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void fullmoveNumberZeroThrowsException() {
		new FullmoveNumber(game, "0");
	}

	@Test
	public void fullmoveNumber() {
		new FullmoveNumber(game, "1").execute();
		assertThat(game.fullMoveNumber(), is(1));

		new FullmoveNumber(game, "17").execute();
		assertThat(game.fullMoveNumber(), is(17));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalValueAsHalfmoveThrowsException() {
		new FullmoveNumber(game, "a3").execute();
	}

}
