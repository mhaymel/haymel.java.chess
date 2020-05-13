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
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static com.haymel.util.Require.nonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;
import com.haymel.chess.engine.piece.Piece;

public class MoveTest {

	private Piece capturedWhitePiece;
	private Piece capturedBlackPiece;
	
	@Before
	public void setup() {
		capturedWhitePiece = new Piece(WhiteRook, removed);
		capturedBlackPiece = new Piece(BlackRook, removed);
	}
	
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
	public void testToStringOfCapture() {
		assertThat(new Move(a1, b2, capture).toString(), is("a1xb2"));
	}
	
	@Test
	public void captureReturnsTrueForCaptureMove() {
		assertThat(new Move(a1, b2, capture).capture(), is(true));
	}
	
	@Test
	public void testToStringEnpassant() {
		assertThat(new Move(d4, c3, enpassant).toString(), is("d4xc3e.p."));
	}

	@Test
	public void captureReturnsTrueForEnpassant() {
		assertThat(new Move(d4, c3, enpassant).capture(), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionQueen() {
		assertThat(new Move(d7, e8, capturedWhitePiece, BlackQueen).toString(), is("d7xe8Q"));
		assertThat(new Move(d7, e8, capturedBlackPiece, WhiteQueen).toString(), is("d7xe8Q"));
	}

	@Test
	public void captureReturnsTrueForCapturePromotion() {
		assertThat(new Move(d7, e8, capturedWhitePiece, BlackQueen).capture(), is(true));
		assertThat(new Move(d7, e8, capturedBlackPiece, WhiteQueen).capture(), is(true));
	}
	
	@Test
	public void testToStringCapturePromotionRook() {
		assertThat(new Move(d7, e8, capturedWhitePiece, BlackRook).toString(), is("d7xe8R"));
		assertThat(new Move(d7, e8, capturedBlackPiece, WhiteRook).toString(), is("d7xe8R"));
	}
	
	@Test
	public void testToStringCapturePromotionBishop() {
		assertThat(new Move(d7, e8, capturedWhitePiece, BlackBishop).toString(), is("d7xe8B"));
		assertThat(new Move(d7, e8, capturedBlackPiece, WhiteBishop).toString(), is("d7xe8B"));
	}
	
	@Test
	public void testToStringCapturePromotionKnight() {
		assertThat(new Move(d7, e8, capturedWhitePiece, BlackKnight).toString(), is("d7xe8N"));
		assertThat(new Move(d7, e8, capturedBlackPiece, WhiteKnight).toString(), is("d7xe8N"));
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
		assertThat(new Move(d7, d8, BlackQueen).toString(), is("d7-d8Q"));
		assertThat(new Move(d7, d8, WhiteQueen).toString(), is("d7-d8Q"));
	}
	
	@Test
	public void testToStringPromotionRook() {
		assertThat(new Move(d7, d8, BlackRook).toString(), is("d7-d8R"));
		assertThat(new Move(d7, d8, WhiteRook).toString(), is("d7-d8R"));
	}

	@Test
	public void testToStringPromotionBishop() {
		assertThat(new Move(d7, d8, BlackBishop).toString(), is("d7-d8B"));
		assertThat(new Move(d7, d8, WhiteBishop).toString(), is("d7-d8B"));
	}
	
	@Test
	public void testToStringPromotionKnight() {
		assertThat(new Move(d7, d8, BlackKnight).toString(), is("d7-d8N"));
		assertThat(new Move(d7, d8, WhiteKnight).toString(), is("d7-d8N"));
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
	

	private Game fromFen(String fen) {
		return new GameFromFEN(fen).execute();		
	}
	
	private Move find(String move, Game game) {
		return nonNull(new MoveFinder(game.moves()).find(move), "move");
	}
	
}
