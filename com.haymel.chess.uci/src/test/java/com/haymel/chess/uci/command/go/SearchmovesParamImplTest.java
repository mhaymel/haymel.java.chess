/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.uci.moves.MovesImpl;
import com.haymel.util.exception.HaymelNullPointerException;

public class SearchmovesParamImplTest {

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullTHrowsException() {
		new SearchmovesParamImpl(null);
	}
	
	@Test
	public void movesReturnsValueSetByConstrucor() {
		MovesImpl moves = new MovesImpl().add("e2e4").add("d7d5");
		
		assertThat(new SearchmovesParamImpl(moves).moves(), is(moves));
	}

}
