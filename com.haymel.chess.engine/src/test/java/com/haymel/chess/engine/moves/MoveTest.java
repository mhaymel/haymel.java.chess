/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.moves.Move.asString;
import static com.haymel.chess.engine.moves.Move.newMove;
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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public class MoveTest {

	@Test
	public void fromAndToReturnValuesSetByConstructor() {
		int move = Move.newMove(e2, e4, MoveType.normal);
		assertThat(Move.from(move), is(e2));
		assertThat(Move.to(move), is(e4));
		assertThat(Move.capture(move), is(false));
	}
	
	@Test
	public void testToStringOfWhiteCapture() {
		int move = find("a2a8", fromFen("q6k/8/8/8/8/8/Q7/7K w - - 0 1"));
		assertThat(Move.asString(move), is("a2xa8"));
	}
	
	@Test
	public void testToStringOfBlackCapture() {
		int move = find("a7a1", fromFen("7k/q7/8/8/8/8/8/Q6K b - - 0 1"));
		assertThat(Move.asString(move), is("a7xa1"));
	}
	
	@Test
	public void captureReturnsTrueForWhiteCaptureMove() {
		int move = find("a2a8", fromFen("q6k/8/8/8/8/8/Q7/7K w - - 0 1"));
		assertThat(Move.capture(move), is(true));
	}

	@Test
	public void captureReturnsTrueForBlackCaptureMove() {
		int move = find("a7a1", fromFen("7k/q7/8/8/8/8/8/Q6K b - - 0 1"));
		assertThat(Move.capture(move), is(true));
	}
	
	@Test
	public void testToStringWhiteEnpassant() {
		int move = find("c5d6", fromFen("7k/8/8/2Pp4/8/8/8/7K w - d6 1 1"));
		assertThat(Move.asString(move), is("c5xd6e.p."));
	}

	@Test
	public void testToStringBlackEnpassant() {
		int move = find("d4c3", fromFen("7k/8/8/8/2Pp4/8/8/7K b - c3 1 1"));
		assertThat(Move.asString(move), is("d4xc3e.p."));
	}
	
	@Test
	public void captureReturnsTrueForWhiteEnpassant() {
		int move = find("c5d6", fromFen("7k/8/8/2Pp4/8/8/8/7K w - d6 1 1"));
		assertThat(Move.capture(move), is(true));
	}

	@Test
	public void captureReturnsTrueForBlackEnpassant() {
		int move = find("d4c3", fromFen("7k/8/8/8/2Pp4/8/8/7K b - c3 1 1"));
		assertThat(Move.capture(move), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionQueen() {
		assertThat(asString(newMove(d7, e8, capturePromotionQueen)), is("d7xe8Q"));
		assertThat(asString(newMove(d2, e1, capturePromotionQueen)), is("d2xe1Q"));
	}

	@Test
	public void captureReturnsTrueForCapturePromotion() {
		assertThat(Move.capture(newMove(d7, e8, capturePromotionQueen)), is(true));
		assertThat(Move.capture(newMove(d7, e8, capturePromotionQueen)), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionRook() {
		assertThat(asString(newMove(d7, e8, capturePromotionRook)), is("d7xe8R"));
		assertThat(asString(newMove(d2, e1, capturePromotionRook)), is("d2xe1R"));
	}
	
	@Test
	public void testToStringCapturePromotionBishop() {
		assertThat(asString(newMove(d7, e8, capturePromotionBishop)), is("d7xe8B"));
		assertThat(asString(newMove(d2, e1, capturePromotionBishop)), is("d2xe1B"));
	}
	
	@Test
	public void testToStringCapturePromotionKnight() {
		assertThat(asString(newMove(d7, e8, capturePromotionKnight)), is("d7xe8N"));
		assertThat(asString(newMove(d2, e1, capturePromotionKnight)), is("d2xe1N"));
	}
	
	@Test
	public void testToStringKingsideCastling() {
		assertThat(asString(newMove(e1, g1, kingsideCastling)), is("O-O"));
	}

	@Test
	public void testToStringQueensideCastling() {
		assertThat(asString(newMove(e1, g1, queensideCastling)), is("O-O-O"));
	}
	
	@Test
	public void testToStringPromotionQueen() {
		assertThat(asString(newMove(d7, d8, promotionQueen)), is("d7-d8Q"));
		assertThat(asString(newMove(d2, d1, promotionQueen)), is("d2-d1Q"));
	}
	
	@Test
	public void testToStringPromotionRook() {
		assertThat(asString(newMove(d7, d8, promotionRook)), is("d7-d8R"));
		assertThat(asString(newMove(d2, d1, promotionRook)), is("d2-d1R"));
	}

	@Test
	public void testToStringPromotionBishop() {
		assertThat(asString(newMove(d7, d8, promotionBishop)), is("d7-d8B"));
		assertThat(asString(newMove(d2, d1, promotionBishop)), is("d2-d1B"));
	}
	
	@Test
	public void testToStringPromotionKnight() {
		assertThat(asString(newMove(d7, d8, promotionKnight)), is("d7-d8N"));
		assertThat(asString(newMove(d2, d1, promotionKnight)), is("d2-d1N"));
	}

	@Test
	public void whiteKingNormalMoveAsString() {
		int move = find("e1e2", fromFen("4k3/8/8/8/8/8/8/3rK3 w - - 2 1"));
		assertThat(Move.asString(move), is("e1-e2"));
	}

	@Test
	public void whiteKingCaptureMoveAsString() {
		int move = find("e1d1", fromFen("4k3/8/8/8/8/8/8/3rK3 w - - 2 1"));
		assertThat(Move.asString(move), is("e1xd1"));
	}

	@Test
	public void whiteRookNormalMoveAsString() {
		int move = find("a1a2", fromFen("r6k/8/8/8/8/8/8/R6K w - - 0 1"));
		assertThat(Move.asString(move), is("a1-a2"));
	}
	
	@Test
	public void whiteRookCaptureMoveAsString() {
		int move = find("a1a8", fromFen("r6k/8/8/8/8/8/8/R6K w - - 0 1"));
		assertThat(Move.asString(move), is("a1xa8"));
	}
	
	@Test
	public void blackKingNormalMoveAsString() {
		int move = find("e8e7", fromFen("3Rk3/8/8/8/8/8/8/4K3 b - - 2 1"));
		assertThat(Move.asString(move), is("e8-e7"));
	}

	@Test
	public void blackKingCaptureMoveAsString() {
		int move = find("e8d8", fromFen("3Rk3/8/8/8/8/8/8/4K3 b - - 2 1"));
		assertThat(Move.asString(move), is("e8xd8"));
	}
	
	@Test
	public void blackRookNormalMoveAsString() {
		int move = find("a8a7", fromFen("r6k/8/8/8/8/8/8/R6K b - - 0 1"));
		assertThat(Move.asString(move), is("a8-a7"));
	}
	
	@Test
	public void blackRookCaptureMoveAsString() {
		int move = find("a8a1", fromFen("r6k/8/8/8/8/8/8/R6K b - - 0 1"));
		assertThat(Move.asString(move), is("a8xa1"));
	}
	
	private static Game fromFen(String fen) {
		return new GameFromFEN(fen).value();		
	}
	
	private static int find(String moveAsString, Game game) {
		int move = new MoveFinder(game.moves()).find(moveAsString);
		assertThat(Move.validMove(move), is(true));
		return move;
	}
	
}
