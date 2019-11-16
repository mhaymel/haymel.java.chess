/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class ParserTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new Parser(null);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void firstThrowsExceptionIfEmpty() {
		new Parser("").first();
	}

	@Test
	public void firstReturnsTheFirstWord() {
		assertThat(new Parser("debug on").first(), is("debug"));
		assertThat(new Parser("DEBUG on").first(), is("DEBUG"));
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void valueWithLessThanZeroThrowsException() {
		new Parser("debug on").value(-1);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void valueWithIndexGreaterCountZeroThrowsException() {
		new Parser("debug on").value(3);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void valueWithIndexEqualCountZeroThrowsException() {
		new Parser("debug on").value(2);
	}

	@Test
	public void valueReturnsTheCorrectWord() {
		Parser parser = new Parser("DEBUG on");
		assertThat(parser.value(0), is("DEBUG"));
		assertThat(parser.value(1), is("on"));
	}
	
	@Test
	public void test() {
		test("      ", new String[] { } );
		test("", new String[] { } );
		test("uci", new String[] { "uci"} );
		test("    uci   ", new String[] { "uci"} );
		test("debug on", new String[] { "debug", "on"} );
		test("debug on a b c", new String[] { "debug", "on", "a", "b", "c" } );
	}

	private void test(String line, String[] strings) {
		Parser parser = new Parser(line);
		assertThat(parser.count(), is(strings.length));
		assertThat(parser.empty(), is(strings.length==0));
		assertThat(parser.values(), is(strings));
	}
	
}
