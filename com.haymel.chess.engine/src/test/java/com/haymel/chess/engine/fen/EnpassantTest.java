/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.removed;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Position;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class EnpassantTest {

	private Position position;
	
	@Before
	public void setup() {
		position = new Position();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new Enpassant(null, "a3");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new Enpassant(position, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new Enpassant(position, "");
	}

	@Test
	public void noEnpassantFieldWhite() {
		position.activeColorWhite();
		new Enpassant(position, "-").execute();
		assertThat(position.enPassant(), is(removed));
	}

	@Test
	public void noEnpassantFieldBlack() {
		position.activeColorBlack();
		new Enpassant(position, "-").execute();
		assertThat(position.enPassant(), is(removed));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalCharacterInFieldThrowsException() {
		new Enpassant(position, "x7").execute();
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalDigitInFieldThrowsException() {
		new Enpassant(position, "e9").execute();
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalWhiteEnpassantFieldThrowsException() {
		new Enpassant(position, "a4").execute();
	}

}
