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

public class FullmoveNumberTest {

	private Position position;
	
	@Before
	public void setup() {
		position = new Position();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new FullmoveNumber(null, "1");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new FullmoveNumber(position, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new FullmoveNumber(position, "");
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void fullmoveNumberZeroThrowsException() {
		new FullmoveNumber(position, "0");
	}

	@Test
	public void fullmoveNumber() {
		new FullmoveNumber(position, "1").execute();
		assertThat(position.fullMoveNumber(), is(1));

		new FullmoveNumber(position, "17").execute();
		assertThat(position.fullMoveNumber(), is(17));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalValueAsHalfmoveThrowsException() {
		new FullmoveNumber(position, "a3").execute();
	}

}
