/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.uciengine.Promotion.Bishop;
import static com.haymel.chess.uciengine.Promotion.Knight;
import static com.haymel.chess.uciengine.Promotion.Queen;
import static com.haymel.chess.uciengine.Promotion.Rook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uciengine.FieldsFromUciMoveString;
import com.haymel.util.exception.HaymelNullPointerException;

public class FieldsFromUciMoveStringTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new FieldsFromUciMoveString(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new FieldsFromUciMoveString("");
	}

	@Test
	public void testValidValues() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("e2e4");
		assertThat(fieldFromMoveString.from(), is(e2));
		assertThat(fieldFromMoveString.to(), is(e4));
	}

	@Test
	public void promotionQueen() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("d2d1q");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Queen));
	}

	@Test
	public void promotionRook() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("d2d1r");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Rook));
	}

	@Test
	public void promotionBishop() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("d2d1b");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Bishop));
	}

	@Test
	public void promotionKnight() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("d2d1n");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Knight));
	}
	
	@Test
	public void isPromotionReturnsFalseIfMoveIsNoPromotion() {
		FieldsFromUciMoveString fieldFromMoveString = new FieldsFromUciMoveString("d2d1");
		assertThat(fieldFromMoveString.isPromotion(), is(false));
	}
	
	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveIsNoPromotion() {
		new FieldsFromUciMoveString("d2d1").promotion();
	}

	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveContainsWrongCharacterForPromotion() {
		new FieldsFromUciMoveString("d2d1z").promotion();
	}

}
