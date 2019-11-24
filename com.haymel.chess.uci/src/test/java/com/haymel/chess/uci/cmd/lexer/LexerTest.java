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
	
	@Test
	public void remaingTokensReturnsOneForEmptyString() {
		Lexer lexer = new Lexer("");
		assertThat(lexer.remainingTokens(), is(1));
	}
	
	@Test
	public void remaingTokensReturnsZeroAfterReadingEof() {
		Lexer lexer = new Lexer("");
		lexer.next();
		assertThat(lexer.remainingTokens(), is(0));
	}

	@Test
	public void remaingReturnCorrectValueAfterNext() {
		Lexer lexer = new Lexer("go infinite");
		assertThat(lexer.remainingTokens(), is(3));
		
		lexer.next();
		assertThat(lexer.remainingTokens(), is(2));

		lexer.next();
		assertThat(lexer.remainingTokens(), is(1));
		
		lexer.next();
		assertThat(lexer.remainingTokens(), is(0));
	}
	
	@Test
	public void testLexerWithFen() {
		Lexer lexer = new Lexer("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1");
		assertThat(lexer.remainingTokens(), is(6 + 1));
		
		assertThat(lexer.next().string(), is("8/4k3/8/8/8/6R1/4K3/8"));
		assertThat(lexer.next().string(), is("b"));
		assertThat(lexer.next().string(), is("-"));
		assertThat(lexer.next().string(), is("-"));
		assertThat(lexer.next().string(), is("0"));
		assertThat(lexer.next().string(), is("1"));

		assertThat(lexer.remainingTokens(), is(1));
	}
	
	@Test
	public void hasNextReturnsTrueForEmptyString() {
		Lexer lexer = new Lexer("");
		assertThat(lexer.hasNext(), is(true));
	}
	
	@Test
	public void hasNextReturnsTrueAsLongAsTokensAreAvailable() {
		Lexer lexer = new Lexer("go infinite");
		assertThat(lexer.hasNext(), is(true));
		
		lexer.next();
		assertThat(lexer.hasNext(), is(true));

		lexer.next();
		assertThat(lexer.hasNext(), is(true));
	
		lexer.next();
		assertThat(lexer.hasNext(), is(false));
	}
	
	@Test(expected=HaymelException.class)
	public void pushbackThrowsExceptionIfNoTokenWasRead() {
		new Lexer("").pushback();
	}

	@Test
	public void pushbackOnEofIsOk() {
		Lexer lexer = new Lexer("");
		lexer.next();
		assertThat(lexer.hasNext(), is(false));
		lexer.pushback();
		assertThat(lexer.hasNext(), is(true));
		assertThat(lexer.next().type(), is(TokenType.eof));
	}

	@Test
	public void pushbackOnReadToken() {
		Lexer lexer = new Lexer("go infinite");

		Token token = lexer.next();
		assertThat(token.type(), is(TokenType.go));

		token = lexer.next();
		assertThat(token.type(), is(TokenType.infinite));
		
		lexer.pushback();
		lexer.pushback();

		token = lexer.next();
		assertThat(token.type(), is(TokenType.go));
	}
	
}
