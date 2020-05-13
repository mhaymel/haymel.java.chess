/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.algebraic;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.util.exception.HaymelNullPointerException;

public class FieldsFromAlgebraicMoveTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new FieldsFromAlgebraicMove(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorWithEmptyStringThrowsException() {
		new FieldsFromAlgebraicMove("");
	}

	@Test
	public void testValidValues() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("e2e4");
		assertThat(fieldFromMoveString.from(), is(e2));
		assertThat(fieldFromMoveString.to(), is(e4));
	}

	@Test
	public void promotionQueen() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("d2d1q");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Promotion.Queen));
	}

	@Test
	public void promotionRook() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("d2d1r");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Promotion.Rook));
	}

	@Test
	public void promotionBishop() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("d2d1b");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Promotion.Bishop));
	}

	@Test
	public void promotionKnight() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("d2d1n");
		assertThat(fieldFromMoveString.from(), is(d2));
		assertThat(fieldFromMoveString.to(), is(d1));
		assertThat(fieldFromMoveString.isPromotion(), is(true));
		assertThat(fieldFromMoveString.promotion(), is(Promotion.Knight));
	}
	
	@Test
	public void isPromotionReturnsFalseIfMoveIsNoPromotion() {
		FieldsFromAlgebraicMove fieldFromMoveString = new FieldsFromAlgebraicMove("d2d1");
		assertThat(fieldFromMoveString.isPromotion(), is(false));
	}
	
	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveIsNoPromotion() {
		new FieldsFromAlgebraicMove("d2d1").promotion();
	}

	@Test(expected=IllegalStateException.class)
	public void promotionThrowsExceptionIfMoveContainsWrongCharacterForPromotion() {
		new FieldsFromAlgebraicMove("d2d1z").promotion();
	}

}
