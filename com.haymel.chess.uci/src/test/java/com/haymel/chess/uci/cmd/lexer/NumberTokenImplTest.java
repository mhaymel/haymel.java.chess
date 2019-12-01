/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class NumberTokenImplTest {

	@Test
	public void typeReturnsNumber() {
		assertThat(new NumberTokenImpl(0).type(), is(TokenType.number));
	}
	
	@Test
	public void stringReturnsCorrectValue() {
		assertThat(new NumberTokenImpl(0).string(), is("0"));
		assertThat(new NumberTokenImpl(-1).string(), is("-1"));
		assertThat(new NumberTokenImpl(Long.MAX_VALUE).string(), is("9223372036854775807"));
		assertThat(new NumberTokenImpl(Long.MIN_VALUE).string(), is("-9223372036854775808"));
	}

	@Test
	public void valueReturnsCorrectValue() {
		assertThat(new NumberTokenImpl(0).value(), is(0L));
		assertThat(new NumberTokenImpl(-1).value(), is(-1L));
		assertThat(new NumberTokenImpl(Long.MAX_VALUE).value(), is(9223372036854775807L));
		assertThat(new NumberTokenImpl(Long.MIN_VALUE).value(), is(-9223372036854775808L));
	}

}
