/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.engine.board.Field;

public class FieldTest {

	@Test
	public void testToString() {
		assertThat(Field.a1.toString(), is("a1"));
		assertThat(Field.a2.toString(), is("a2"));
		assertThat(Field.a3.toString(), is("a3"));
		assertThat(Field.a4.toString(), is("a4"));
		assertThat(Field.a5.toString(), is("a5"));
		assertThat(Field.a6.toString(), is("a6"));
		assertThat(Field.a7.toString(), is("a7"));
		assertThat(Field.a8.toString(), is("a8"));

		assertThat(Field.b1.toString(), is("b1"));
		assertThat(Field.b2.toString(), is("b2"));
		assertThat(Field.b3.toString(), is("b3"));
		assertThat(Field.b4.toString(), is("b4"));
		assertThat(Field.b5.toString(), is("b5"));
		assertThat(Field.b6.toString(), is("b6"));
		assertThat(Field.b7.toString(), is("b7"));
		assertThat(Field.b8.toString(), is("b8"));
		
		assertThat(Field.c1.toString(), is("c1"));
		assertThat(Field.c2.toString(), is("c2"));
		assertThat(Field.c3.toString(), is("c3"));
		assertThat(Field.c4.toString(), is("c4"));
		assertThat(Field.c5.toString(), is("c5"));
		assertThat(Field.c6.toString(), is("c6"));
		assertThat(Field.c7.toString(), is("c7"));
		assertThat(Field.c8.toString(), is("c8"));
		
		assertThat(Field.d1.toString(), is("d1"));
		assertThat(Field.d2.toString(), is("d2"));
		assertThat(Field.d3.toString(), is("d3"));
		assertThat(Field.d4.toString(), is("d4"));
		assertThat(Field.d5.toString(), is("d5"));
		assertThat(Field.d6.toString(), is("d6"));
		assertThat(Field.d7.toString(), is("d7"));
		assertThat(Field.d8.toString(), is("d8"));
		
		assertThat(Field.e1.toString(), is("e1"));
		assertThat(Field.e2.toString(), is("e2"));
		assertThat(Field.e3.toString(), is("e3"));
		assertThat(Field.e4.toString(), is("e4"));
		assertThat(Field.e5.toString(), is("e5"));
		assertThat(Field.e6.toString(), is("e6"));
		assertThat(Field.e7.toString(), is("e7"));
		assertThat(Field.e8.toString(), is("e8"));
		
		assertThat(Field.f1.toString(), is("f1"));
		assertThat(Field.f2.toString(), is("f2"));
		assertThat(Field.f3.toString(), is("f3"));
		assertThat(Field.f4.toString(), is("f4"));
		assertThat(Field.f5.toString(), is("f5"));
		assertThat(Field.f6.toString(), is("f6"));
		assertThat(Field.f7.toString(), is("f7"));
		assertThat(Field.f8.toString(), is("f8"));
		
		assertThat(Field.g1.toString(), is("g1"));
		assertThat(Field.g2.toString(), is("g2"));
		assertThat(Field.g3.toString(), is("g3"));
		assertThat(Field.g4.toString(), is("g4"));
		assertThat(Field.g5.toString(), is("g5"));
		assertThat(Field.g6.toString(), is("g6"));
		assertThat(Field.g7.toString(), is("g7"));
		assertThat(Field.g8.toString(), is("g8"));
		
		assertThat(Field.h1.toString(), is("h1"));
		assertThat(Field.h2.toString(), is("h2"));
		assertThat(Field.h3.toString(), is("h3"));
		assertThat(Field.h4.toString(), is("h4"));
		assertThat(Field.h5.toString(), is("h5"));
		assertThat(Field.h6.toString(), is("h6"));
		assertThat(Field.h7.toString(), is("h7"));
		assertThat(Field.h8.toString(), is("h8"));
	}

	@Test
	public void testFile() {
		assertThat(Field.a1.rank(), is(0));
		assertThat(Field.a2.rank(), is(1));
		assertThat(Field.a8.rank(), is(7));
	}
}
