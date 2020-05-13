/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	11.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.algebraic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class PromotionTest {

	@Test
	public void unknowCharacterReturnsEmpty() {
		assertThat(Promotion.promotion('x'), is(Optional.empty()));
		assertThat(Promotion.promotion('Q'), is(Optional.empty()));
		assertThat(Promotion.promotion('R'), is(Optional.empty()));
		assertThat(Promotion.promotion('B'), is(Optional.empty()));
		assertThat(Promotion.promotion('N'), is(Optional.empty()));
	}

	@Test
	public void testValidCharacters() {
		assertThat(Promotion.promotion('q').get(), is(Promotion.Queen));
		assertThat(Promotion.promotion('r').get(), is(Promotion.Rook));
		assertThat(Promotion.promotion('b').get(), is(Promotion.Bishop));
		assertThat(Promotion.promotion('n').get(), is(Promotion.Knight));
	}
	
}
