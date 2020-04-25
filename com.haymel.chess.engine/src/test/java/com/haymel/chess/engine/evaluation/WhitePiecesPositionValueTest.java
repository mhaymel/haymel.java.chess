/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WhitePiecesPositionValueTest {

	private static final WhitePiecesPositionValue pst = new WhitePiecesPositionValue();
	
	@Test
	public void pawn() {
		assertThat(pst.value(WhitePawn, e4), is(20));
		assertThat(pst.value(WhitePawn, d2), is(-20));
		assertThat(pst.value(WhitePawn, c6), is(20));
		assertThat(pst.value(WhitePawn, c7), is(50));
	}

}
