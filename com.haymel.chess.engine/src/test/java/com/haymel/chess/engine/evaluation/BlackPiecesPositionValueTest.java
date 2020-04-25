/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BlackPiecesPositionValueTest {

	private static final BlackPiecesPositionValue pst = new BlackPiecesPositionValue();
	
	@Test
	public void pawn() {
		assertThat(pst.value(BlackPawn, e5), is(20));
		assertThat(pst.value(BlackPawn, d7), is(-20));
		assertThat(pst.value(BlackPawn, c3), is(20));
		assertThat(pst.value(BlackPawn, c2), is(50));
	}

}
