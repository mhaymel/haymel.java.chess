/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class TokenCreatorTest {

	@Test
	public void valueReturnsNumberTokenForNumber() {
		Token token = new TokenCreator(TokenType.number, "123").value();
		assertThat(token, instanceOf(NumberToken.class));
		assertThat(token.string(), is("123"));
		NumberToken number = (NumberToken)token;
		assertThat(number.value(), is(123L));
	}

	@Test(expected=NumberFormatException.class)
	public void valueThrowsExceptionIfStringIsNoNumberButType() {
		new TokenCreator(TokenType.number, "uci").value();
	}
	
	@Test
	public void valueReturnsBoolTokenForTrue() {
		Token token = new TokenCreator(TokenType.bool, "true").value();
		assertThat(token, instanceOf(BooleanToken.class));
	}

	@Test
	public void valueReturnsBoolTokenForFalse() {
		Token token = new TokenCreator(TokenType.bool, "false").value();
		assertThat(token, instanceOf(BooleanToken.class));
	}

	@Test
	public void valueReturnsBoolenTokenWithFalseForValueNotEqualTrueOrFalse() {
		Token token = new TokenCreator(TokenType.bool, "uci").value();
		assertThat(token, instanceOf(BooleanToken.class));
		BooleanToken bool = (BooleanToken)token;
		assertThat(bool.value(), is(false));
		assertThat(bool.string(), is("false"));
	}

}
