/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelNullPointerException;

public class FieldsFromMoveStringTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new FieldsFromMoveString(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new FieldsFromMoveString("");
	}

	@Test
	public void testValidValues() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("e2e4");
		assertThat(fieldFromMoveString.from(), is(e2));
		assertThat(fieldFromMoveString.to(), is(e4));
	}

}
