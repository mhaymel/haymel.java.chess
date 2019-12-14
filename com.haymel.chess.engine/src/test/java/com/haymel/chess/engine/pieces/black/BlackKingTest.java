/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.black;

import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BlackKingTest {

	private BlackKing piece;
	
	@Before
	public void setup() {
		piece = new BlackKing();
	}
	
	@Test
	public void whiteReturnsFalse() {
		assertThat(piece.white(), is(false));
	}

	@Test
	public void blackReturnsTrue() {
		assertThat(piece.black(), is(true));
	}
	
	@Test
	public void freeReturnsFalse() {
		assertThat(piece.free(), is(false));
	}

	@Test
	public void fieldReturnsRemovedForVirginInstance() {
		assertThat(piece.field(), is(removed));
	}

	@Test
	public void fieldReturnsValueSet() {
		piece.field(e4);
		assertThat(piece.field(), is(e4));
	}
	
	@Test
	public void movedReturnsTrueForVirginObject() {
		assertThat(piece.moved(), is(true));
	}

	@Test
	public void moveSetFalse() {
		assertThat(piece.moved(), is(true));
		piece.field(e8);
		piece.setMoved(false);
		assertThat(piece.moved(), is(false));
	}
	
	@Test
	public void moveSetTrue() {
		piece.field(e8);
		piece.setMoved(true);
		assertThat(piece.moved(), is(true));
		piece.setMoved(false);
		assertThat(piece.moved(), is(false));
	}
	
}
