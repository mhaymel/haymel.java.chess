/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command;

import static java.lang.Long.MAX_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uci.command.UnsignedInt;
import com.haymel.chess.uci.command.UnsignedLong;
import com.haymel.util.exception.HaymelException;

public class UnsignedLongTest {

	@Test(expected=HaymelException.class)
	public void constructorWithNegativeValueThrowsIAE() {
		new UnsignedLong(-1L);
	}
	
	@Test
	public void definedReturnsTrue() {
		assertThat(new UnsignedInt(10).defined(), is(true));
	}

	@Test
	public void valueSetByConstructorWillBeReturned() {
		assertThat(new UnsignedLong(10).value(), is(10L));
		assertThat(new UnsignedLong(MAX_VALUE).value(), is(MAX_VALUE));
		assertThat(new UnsignedLong(0L).value(), is(0L));
	}
	
}
