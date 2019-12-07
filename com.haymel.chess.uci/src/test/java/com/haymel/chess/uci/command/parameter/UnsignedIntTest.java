/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.parameter;

import static java.lang.Integer.MAX_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uci.command.parameter.UnsignedInt;
import com.haymel.util.exception.HaymelException;

public class UnsignedIntTest {

	@Test(expected=HaymelException.class)
	public void constructorWithNegativeValueThrowsIAE() {
		new UnsignedInt(-1);
	}
	
	@Test
	public void definedReturnsTrue() {
		assertThat(new UnsignedInt(10).defined(), is(true));
	}

	@Test
	public void valueSetByConstructorWillBeReturned() {
		assertThat(new UnsignedInt(10).value(), is(10));
		assertThat(new UnsignedInt(MAX_VALUE).value(), is(MAX_VALUE));
		assertThat(new UnsignedInt(0).value(), is(0));
	}
	
}
