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

public class FieldFromStringTest {

	@Test
	public void test() {
		test("e2", e2);
		test("e4", e4);
		test("a1", a1);
		test("h1", h1);
		test("h8", h8);
	}
	
	private void test(String s, Field f) {
		Field field = new FieldFromString(s).value();
		assertThat(field, is(f));
	}

}
