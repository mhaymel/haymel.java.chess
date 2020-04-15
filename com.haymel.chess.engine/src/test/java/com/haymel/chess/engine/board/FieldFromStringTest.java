/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class FieldFromStringTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new FieldFromString(null);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new FieldFromString("");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithWrongFirstCharacterThrowsException() {
		new FieldFromString("k1");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithWrongSecondCharacterThrowsException() {
		new FieldFromString("a9");
	}
	
	@Test
	public void testValidFields() {
		test("e2", e2);
		test("e4", e4);
		test("a1", a1);
		test("h1", h1);
		test("h8", h8);
	}
	
	private void test(String s, int expectedField) {
		int field = new FieldFromString(s).value();
		assertThat(field, is(expectedField));
	}

}
