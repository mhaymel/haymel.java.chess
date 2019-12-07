/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.parameter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.uci.command.parameter.IntParamImpl;

public class IntParamImplTest {

	@Test
	public void definedReturnsTrue() {
		assertThat(new IntParamImpl(10).defined(), is(true));
	}

	@Test
	public void valueSetByConstructorWillBeReturned() {
		assertThat(new IntParamImpl(10).value(), is(10));
		assertThat(new IntParamImpl(MIN_VALUE).value(), is(MIN_VALUE));
		assertThat(new IntParamImpl(MAX_VALUE).value(), is(MAX_VALUE));
	}
	
}
