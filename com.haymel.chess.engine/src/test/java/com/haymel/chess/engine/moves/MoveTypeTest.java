/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTypeTest {

	@Test
	public void capture() {
		assertThat(MoveType.capture(MoveType.normal), is(false));
		assertThat(MoveType.capture(MoveType.pawnDoubleStep), is(false));
		assertThat(MoveType.capture(MoveType.kingsideCastling), is(false));
		assertThat(MoveType.capture(MoveType.queensideCastling), is(false));
		assertThat(MoveType.capture(MoveType.enpassant), is(true));
		assertThat(MoveType.capture(MoveType.capture), is(true));
		assertThat(MoveType.capture(MoveType.promotion), is(false));
		assertThat(MoveType.capture(MoveType.capturePromotion), is(true));
	}

}
