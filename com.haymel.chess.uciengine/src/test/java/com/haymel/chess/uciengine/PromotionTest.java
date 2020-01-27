/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	11.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.chess.uciengine.Promotion.Bishop;
import static com.haymel.chess.uciengine.Promotion.Knight;
import static com.haymel.chess.uciengine.Promotion.Queen;
import static com.haymel.chess.uciengine.Promotion.Rook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.haymel.chess.uciengine.Promotion;

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
		assertThat(Promotion.promotion('q').get(), is(Queen));
		assertThat(Promotion.promotion('r').get(), is(Rook));
		assertThat(Promotion.promotion('b').get(), is(Bishop));
		assertThat(Promotion.promotion('n').get(), is(Knight));
	}
	
}
