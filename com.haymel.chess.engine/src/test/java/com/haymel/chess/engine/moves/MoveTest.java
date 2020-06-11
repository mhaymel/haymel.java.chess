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
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.util.Require.nonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public class MoveTest {

	@Test
	public void fromAndToReturnValuesSetByConstructor() {
		Move move = new Move(e2, e4);
		assertThat(move.from(), is(e2));
		assertThat(move.to(), is(e4));
		assertThat(move.capture(), is(false));
	}
	
	@Test
	public void testToString() {
		assertThat(new Move(a1, b2).toString(), is("a1-b2"));
	}
	
	@Test
	public void testToStringOfWhiteCapture() {
		Move move = find("a2a8", fromFen("q6k/8/8/8/8/8/Q7/7K w - - 0 1"));
		assertThat(move.toString(), is("a2xa8"));
	}
	
	@Test
	public void testToStringOfBlackCapture() {
		Move move = find("a7a1", fromFen("7k/q7/8/8/8/8/8/Q6K b - - 0 1"));
		assertThat(move.toString(), is("a7xa1"));
	}
	
	@Test
	public void captureReturnsTrueForWhiteCaptureMove() {
		Move move = find("a2a8", fromFen("q6k/8/8/8/8/8/Q7/7K w - - 0 1"));
		assertThat(move.capture(), is(true));
	}

	@Test
	public void captureReturnsTrueForBlackCaptureMove() {
		Move move = find("a7a1", fromFen("7k/q7/8/8/8/8/8/Q6K b - - 0 1"));
		assertThat(move.capture(), is(true));
	}
	
	@Test
	public void testToStringWhiteEnpassant() {
		Move move = find("c5d6", fromFen("7k/8/8/2Pp4/8/8/8/7K w - d6 1 1"));
		assertThat(move.toString(), is("c5xd6e.p."));
	}

	@Test
	public void testToStringBlackEnpassant() {
		Move move = find("d4c3", fromFen("7k/8/8/8/2Pp4/8/8/7K b - c3 1 1"));
		assertThat(move.toString(), is("d4xc3e.p."));
	}
	
	@Test
	public void captureReturnsTrueForWhiteEnpassant() {
		Move move = find("c5d6", fromFen("7k/8/8/2Pp4/8/8/8/7K w - d6 1 1"));
		assertThat(move.capture(), is(true));
	}

	@Test
	public void captureReturnsTrueForBlackEnpassant() {
		Move move = find("d4c3", fromFen("7k/8/8/8/2Pp4/8/8/7K b - c3 1 1"));
		assertThat(move.capture(), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionQueen() {
		assertThat(new Move(d7, e8, capturePromotionQueen).toString(), is("d7xe8Q"));
		assertThat(new Move(d2, e1, capturePromotionQueen).toString(), is("d2xe1Q"));
	}

	@Test
	public void captureReturnsTrueForCapturePromotion() {
		assertThat(new Move(d7, e8, capturePromotionQueen).capture(), is(true));
		assertThat(new Move(d7, e8, capturePromotionQueen).capture(), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionRook() {
		assertThat(new Move(d7, e8, capturePromotionRook).toString(), is("d7xe8R"));
		assertThat(new Move(d2, e1, capturePromotionRook).toString(), is("d2xe1R"));
	}
	
	@Test
	public void testToStringCapturePromotionBishop() {
		assertThat(new Move(d7, e8, capturePromotionBishop).toString(), is("d7xe8B"));
		assertThat(new Move(d2, e1, capturePromotionBishop).toString(), is("d2xe1B"));
	}
	
	@Test
	public void testToStringCapturePromotionKnight() {
		assertThat(new Move(d7, e8, capturePromotionKnight).toString(), is("d7xe8N"));
		assertThat(new Move(d2, e1, capturePromotionKnight).toString(), is("d2xe1N"));
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
		assertThat(new Move(d2, d1, promotionQueen).toString(), is("d2-d1Q"));
	}
	
	@Test
	public void testToStringPromotionRook() {
		assertThat(new Move(d7, d8, promotionRook).toString(), is("d7-d8R"));
		assertThat(new Move(d2, d1, promotionRook).toString(), is("d2-d1R"));
	}

	@Test
	public void testToStringPromotionBishop() {
		assertThat(new Move(d7, d8, promotionBishop).toString(), is("d7-d8B"));
		assertThat(new Move(d2, d1, promotionBishop).toString(), is("d2-d1B"));
	}
	
	@Test
	public void testToStringPromotionKnight() {
		assertThat(new Move(d7, d8, promotionKnight).toString(), is("d7-d8N"));
		assertThat(new Move(d2, d1, promotionKnight).toString(), is("d2-d1N"));
	}

	@Test
	public void whiteKingNormalMoveAsString() {
		Move move = find("e1e2", fromFen("4k3/8/8/8/8/8/8/3rK3 w - - 2 1"));
		assertThat(move.toString(), is("e1-e2"));
	}

	@Test
	public void whiteKingCaptureMoveAsString() {
		Move move = find("e1d1", fromFen("4k3/8/8/8/8/8/8/3rK3 w - - 2 1"));
		assertThat(move.toString(), is("e1xd1"));
	}

	@Test
	public void whiteRookNormalMoveAsString() {
		Move move = find("a1a2", fromFen("r6k/8/8/8/8/8/8/R6K w - - 0 1"));
		assertThat(move.toString(), is("a1-a2"));
	}
	
	@Test
	public void whiteRookCaptureMoveAsString() {
		Move move = find("a1a8", fromFen("r6k/8/8/8/8/8/8/R6K w - - 0 1"));
		assertThat(move.toString(), is("a1xa8"));
	}
	
	@Test
	public void blackKingNormalMoveAsString() {
		Move move = find("e8e7", fromFen("3Rk3/8/8/8/8/8/8/4K3 b - - 2 1"));
		assertThat(move.toString(), is("e8-e7"));
	}

	@Test
	public void blackKingCaptureMoveAsString() {
		Move move = find("e8d8", fromFen("3Rk3/8/8/8/8/8/8/4K3 b - - 2 1"));
		assertThat(move.toString(), is("e8xd8"));
	}
	
	@Test
	public void blackRookNormalMoveAsString() {
		Move move = find("a8a7", fromFen("r6k/8/8/8/8/8/8/R6K b - - 0 1"));
		assertThat(move.toString(), is("a8-a7"));
	}
	
	@Test
	public void blackRookCaptureMoveAsString() {
		Move move = find("a8a1", fromFen("r6k/8/8/8/8/8/8/R6K b - - 0 1"));
		assertThat(move.toString(), is("a8xa1"));
	}
	
	private static Game fromFen(String fen) {
		return new GameFromFEN(fen).value();		
	}
	
	private static Move find(String move, Game game) {
		return nonNull(new MoveFinder(game.moves()).find(move), "move");
	}
	
}
