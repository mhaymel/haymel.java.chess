/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;
import static com.haymel.chess.engine.Promotion.Bishop;
import static com.haymel.chess.engine.Promotion.Knight;
import static com.haymel.chess.engine.Promotion.Queen;
import static com.haymel.chess.engine.Promotion.Rook;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelNullPointerException;

public class FieldsFromMoveStringTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new FieldsFromMoveString(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new FieldsFromMoveString("");
	}

	@Test
	public void testValidValues() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("e2e4");
		assertThat(fieldFromMoveString.from(), is(e2));
		assertThat(fieldFromMoveString.to(), is(e4));
	}

	@Test
	public void promotionQueen() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("d2d1q");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Queen));
	}

	@Test
	public void promotionRook() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("d2d1r");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Rook));
	}

	@Test
	public void promotionBishop() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("d2d1b");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Bishop));
	}

	@Test
	public void promotionKnight() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("d2d1n");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Knight));
	}
	
	@Test
	public void isPromotionReturnsFalseIfMoveIsNoPromotion() {
		FieldsFromMoveString fieldFromMoveString = new FieldsFromMoveString("d2d1");
		assertThat(fieldFromMoveString.isPromotion(), is(false));
	}
	
	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveIsNoPromotion() {
		new FieldsFromMoveString("d2d1").promotion();
	}

	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveContainsWrongCharacterForPromotion() {
		new FieldsFromMoveString("d2d1z").promotion();
	}

}
