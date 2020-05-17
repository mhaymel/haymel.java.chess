/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeBlackCapturePromotionMoveTest {

	private Game game;
	
	@Before
	public void setup() {
		game = fromFen("7k/8/8/8/P7/8/4p3/3N1N1K b - a3 13 10");
	}
	
	@Test
	public void e2d1Queen() {
		test("e2d1q", BlackQueen);
	}

	@Test
	public void e2d1Rook() {
		test("e2d1r", BlackRook);
	}
	
	@Test
	public void e2d1Bishop() {
		test("e2d1b", BlackBishop);
	}

	@Test
	public void e2d1Knight() {
		test("e2d1n", BlackKnight);
	}

	@Test
	public void e2f1Queen() {
		test("e2f1q", BlackQueen);
	}

	@Test
	public void e2f1Rook() {
		test("e2f1r", BlackRook);
	}
	
	@Test
	public void e2f1Bishop() {
		test("e2f1b", BlackBishop);
	}

	@Test
	public void e2f1Knight() {
		test("e2f1n", BlackKnight);
	}
	
	
	private void test(String moveString, int promotedPieceType) {
		Move move = find(moveString, game);
		makeMove(move, game);
		
		assertThat(game.piece(move.to()).type(), is(promotedPieceType));
		assertThat(game.piece(move.from()), is(nullValue()));
		assertThat(game.containsBlackPiece(game.piece(move.to())), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		undoMove(game);
		assertThat(game.piece(move.from()).type(), is(BlackPawn));
		assertThat(game.piece(move.to()).type(), is(WhiteKnight));
		assertThat(game.containsBlackPiece(game.piece(move.from())), is(true));
		assertThat(game.containsWhitePiece(game.piece(move.to())), is(true));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(a3));
		assertThat(game.activeColor(), is(black));
	}
	
}
