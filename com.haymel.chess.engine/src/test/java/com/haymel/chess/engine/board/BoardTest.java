/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.piece.Piece;

public class BoardTest {	//TODO implement

	private Board board;
	
	@Before
	public void setup() {
		board = new Board();
	}
	
	@Test
	public void test() {
		Piece elem = board.pieces[Field.a1.position()];
		
		assertThat(elem.free(), is(true));
		assertThat(elem.black(), is(false));
		assertThat(elem.white(), is(false));
	}

	@Test
	public void testBorderElement() {
		Piece elem = board.pieces[Field.a1.left().position()];
		assertThat(elem.free(), is(false));
		assertThat(elem.black(), is(false));
		assertThat(elem.white(), is(false));
	}

}
