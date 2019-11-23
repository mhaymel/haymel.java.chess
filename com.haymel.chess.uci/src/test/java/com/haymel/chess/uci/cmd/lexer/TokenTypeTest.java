/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static com.haymel.chess.uci.cmd.lexer.TokenType.tokenTypeOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTypeTest {

	@Test
	public void testUciMatches() {
		assertThat(TokenType.uci.matches("uci"), is(true));
		assertThat(TokenType.uci.matches("debug"), is(false));
	}

	@Test
	public void testNumberMatches() {
		assertThat(TokenType.number.matches("1234"), is(true));
		assertThat(TokenType.number.matches("uci"), is(false));
	}

	@Test
	public void testWordMatches() {
		assertThat(TokenType.word.matches("word"), is(true));
	}
	
	@Test
	public void tokenReturnsTheCorrectType() {
		assertThat(tokenTypeOf("uci"), is(TokenType.uci));
		assertThat(tokenTypeOf("123"), is(TokenType.number));
		assertThat(tokenTypeOf("true"), is(TokenType.bool));
		assertThat(tokenTypeOf("false"), is(TokenType.bool));
		assertThat(tokenTypeOf("aword"), is(TokenType.word));
	}
	
	@Test
	public void tokenBoolMatchesTrueAndFalse() {
		assertThat(TokenType.bool.matches("true"), is(true));
		assertThat(TokenType.bool.matches("false"), is(true));
	}
	
	@Test 
	public void eofDoesNotMatchAnyThing() {
		assertThat(TokenType.eof.matches("eof"), is(false));
		assertThat(TokenType.eof.matches("uci"), is(false));
		assertThat(TokenType.eof.matches("debug"), is(false));
		assertThat(TokenType.eof.matches("123"), is(false));
		assertThat(TokenType.eof.matches("aword"), is(false));
	}
	
}
