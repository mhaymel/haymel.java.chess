package com.haymel.chess.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

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

		assertThat(Field.a1.toString(), is("a1"));
		assertThat(Field.a2.toString(), is("a2"));
		assertThat(Field.a3.toString(), is("a3"));
		assertThat(Field.a4.toString(), is("a4"));
		assertThat(Field.a5.toString(), is("a5"));
		assertThat(Field.a6.toString(), is("a6"));
		assertThat(Field.a7.toString(), is("a7"));
		assertThat(Field.a8.toString(), is("a8"));
	}

}
