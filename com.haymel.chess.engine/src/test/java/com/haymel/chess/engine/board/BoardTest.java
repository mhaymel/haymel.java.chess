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

import org.junit.Test;

import com.haymel.chess.engine.piece.Piece;

public class BoardTest {	//TODO implement

	
	@Test
	public void test() {
		Piece piece = Board.newBoard()[Field.a1.position()];
		
		assertThat(piece.free(), is(true));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(false));
	}

	@Test
	public void testBorderElement() {
		Piece piece = Board.newBoard()[Field.a1.left().position()];
		assertThat(piece.free(), is(false));
		assertThat(piece.black(), is(false));
		assertThat(piece.white(), is(false));
	}

}
