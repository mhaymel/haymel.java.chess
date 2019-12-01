/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.lexer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.uci.lexer.BooleanTokenImpl;
import com.haymel.chess.uci.lexer.TokenType;

public class BooleanTokenImplTest {

	@Test
	public void typeReturnsBool() {
		assertThat(new BooleanTokenImpl(false).type(), is(TokenType.bool));
	}
	
	@Test
	public void stringReturnsCorrectValue() {
		assertThat(new BooleanTokenImpl(true).string(), is("true"));
		assertThat(new BooleanTokenImpl(false).string(), is("false"));
	}

	@Test
	public void valueReturnsCorrectValue() {
		assertThat(new BooleanTokenImpl(true).value(), is(true));
		assertThat(new BooleanTokenImpl(false).value(), is(false));
	}

}
