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

import com.haymel.util.exception.HaymelNullPointerException;

public class EofTokenTest {

	@Test
	public void typeReturnsNumber() {
		assertThat(EofToken.eof.type(), is(TokenType.eof));
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void stringThrowsException() {
		EofToken.eof.string();
	}

}
