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

import com.haymel.util.exception.HaymelException;
import com.haymel.util.exception.HaymelNullPointerException;

public class LexerTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new Lexer(null);
	}

	@Test
	public void constructorWithEmptyStringIsOk() {
		Lexer lexer = new Lexer("");
		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));
	}

	@Test
	public void testLexerWithOneToken() {
		Lexer lexer = new Lexer("stop");
		
		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.stop));
		
		token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));
	}
	
	@Test
	public void testLexerWithTwoTokens() {
		Lexer lexer = new Lexer("go infinite");
		
		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.go));

		token = lexer.next();
		assertThat(token.type(), is(TokenType.infinite));
		
		token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));
	}
	
	@Test
	public void testLexerWithNumber() {
		Lexer lexer = new Lexer("100000");
		
		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.number));
		assertThat(token, instanceOf(NumberToken.class));
		assertThat(token.string(), is("100000"));
		NumberToken number = (NumberToken)token;
		assertThat(number.value(), is(100000L));

		token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));
	}
	
	@Test(expected=HaymelException.class)
	public void readAfterEofThrowsExceptionForEmptyString() {
		Lexer lexer = new Lexer("");
		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));

		lexer.next();
	}

	@Test(expected=HaymelException.class)
	public void readAfterEofThrowsException() {
		Lexer lexer = new Lexer("go infinite");
		
		Token token = lexer.next();
		token = lexer.next();
		token = lexer.next();
		assertThat(token.type(), is(TokenType.eof));

		lexer.next();
	}
}
