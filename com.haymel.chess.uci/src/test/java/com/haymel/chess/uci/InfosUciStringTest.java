/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class InfosUciStringTest {

	private Infos infos;
	
	@Before
	public void setup() {
		infos = new Infos();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new InfosUciString(null);
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyInfosThrowsHIAE() {
		new InfosUciString(infos);
	}

	@Test
	public void infosWithSingleEntry() {
		infos.scorecp(100);
		String result = new InfosUciString(infos).value();
		assertThat(result, is("info score cp 100"));
	}

	@Test
	public void infosWithTwoEntries() {
		infos.currmove("e2e4").currmovenumber(1);
		String result = new InfosUciString(infos).value();
		assertThat(result, is("info currmove e2e4 currmovenumber 1"));
	}

	@Test
	public void infosWithThreeEntries() {
		infos.depth(12).nodes(123456).nps(100000);
		String result = new InfosUciString(infos).value();
		assertThat(result, is("info depth 12 nodes 123456 nps 100000"));
	}
}
