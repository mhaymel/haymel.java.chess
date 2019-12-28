/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MoveTest {

	@Test
	public void fromAndToReturnValuesSetByConstructor() {
		Move move = new Move(e2, e4);
		assertThat(move.from(), is(e2));
		assertThat(move.to(), is(e4));
	}
	
	@Test
	public void testToString() {
		assertThat(new Move(a1, b2).toString(), is("a1-b2"));
	}
	
	@Test
	public void testToStringOfCapture() {
		assertThat(new Move(a1, b2, capture).toString(), is("a1xb2"));
	}
	
	@Test
	public void testToStringEnpassant() {
		assertThat(new Move(d4, c3, enpassant).toString(), is("d4xc3e.p."));
	}
	
	@Test
	public void testToStringCapturePromotionQueen() {
		assertThat(new Move(d7, e8, capturePromotionQueen).toString(), is("d7xe8Q"));
	}

	@Test
	public void testToStringCapturePromotionRook() {
		assertThat(new Move(d7, e8, capturePromotionRook).toString(), is("d7xe8R"));
	}
	
	@Test
	public void testToStringCapturePromotionBishop() {
		assertThat(new Move(d7, e8, capturePromotionBishop).toString(), is("d7xe8B"));
	}
	
	@Test
	public void testToStringCapturePromotionKnight() {
		assertThat(new Move(d7, e8, capturePromotionKnight).toString(), is("d7xe8N"));
	}
	
	@Test
	public void testToStringKingsideCastling() {
		assertThat(new Move(e1, g1, kingsideCastling).toString(), is("O-O"));
	}

	@Test
	public void testToStringQueensideCastling() {
		assertThat(new Move(e1, g1, queensideCastling).toString(), is("O-O-O"));
	}
	
	@Test
	public void testToStringPromotionQueen() {
		assertThat(new Move(d7, d8, promotionQueen).toString(), is("d7-d8Q"));
	}
	
	@Test
	public void testToStringPromotionRook() {
		assertThat(new Move(d7, d8, promotionRook).toString(), is("d7-d8R"));
	}

	@Test
	public void testToStringPromotionBishop() {
		assertThat(new Move(d7, d8, promotionBishop).toString(), is("d7-d8B"));
	}

	@Test
	public void testToStringPromotionKnight() {
		assertThat(new Move(d7, d8, promotionKnight).toString(), is("d7-d8N"));
	}
	
}
