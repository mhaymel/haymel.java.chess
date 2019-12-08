/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board board = new Board();
		BoardElement piece = board.piece(Field.a1);
		
		assertThat(piece.free(), is(true));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(false));
	}
}
