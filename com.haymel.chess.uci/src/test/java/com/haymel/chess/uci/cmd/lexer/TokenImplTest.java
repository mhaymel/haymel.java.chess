/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class TokenImplTest {

	@Test(expected = HaymelNullPointerException.class)
	public void constructurWithNullAsTypeThrowsException() {
		new TokenImpl(null, "aString");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructurWithNullAsStringThrowsException() {
		new TokenImpl(TokenType.binc, null);
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructurWithEmptyStringThrowsException() {
		new TokenImpl(TokenType.binc, "");
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructurWithStringWithWithspacesOnlyThrowsException() {
		new TokenImpl(TokenType.binc, "  ");
	}

	@Test
	public void typeReturnsValueSetByConstructor() {
		TokenImpl token = new TokenImpl(TokenType.binc, "10");
		assertThat(token.type(), is(TokenType.binc));
		
		token = new TokenImpl(TokenType.uci, "uci");
		assertThat(token.type(), is(TokenType.uci));
	}
	
	@Test
	public void stringReturnsValueSetByConstructor() {
		TokenImpl token = new TokenImpl(TokenType.binc, "10");
		assertThat(token.string(), is("10"));
		
		token = new TokenImpl(TokenType.uci, "uci");
		assertThat(token.string(), is("uci"));
	}

}
