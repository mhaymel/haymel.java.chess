/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PieceSquareTableFromValueArrayTest {

	private static final int[] data = {
		57,	58,	59,	60,	61,	62,	63,	64,
		49, 50, 51, 52,	53,	54,	55,	56,
		41,	42,	43,	44,	45,	46,	47,	48,
		33,	34,	35,	36,	37,	38,	39,	40,
		25,	26,	27,	28,	29,	30,	31,	32,
		17,	18,	19,	20,	21,	22,	23,	24,
		 9,	10,	11,	12,	13,	14,	15,	16,
		 1,	 2,	 3,	 4,	 5,	 6,	 7,	 8
	};
	
	@Test
	public void test() {
		int[] pst = new PieceSquareTableFromArray(data).value();
		assertThat(pst[a1], is(1));
		assertThat(pst[h1], is(8));
		assertThat(pst[a8], is(57));
		assertThat(pst[h8], is(64));
		assertThat(pst[e4], is(29));
		assertThat(pst[d5], is(36));
	}

	@Test
	public void testSwitchSides() {
		int[] pst = new PieceSquareTableFromArray(data, true).value();
		assertThat(pst[a1], is(57));
		assertThat(pst[h1], is(64));
		assertThat(pst[a8], is(1));
		assertThat(pst[h8], is(8));
		assertThat(pst[e4], is(37));
		assertThat(pst[d5], is(28));
	}
	
}
