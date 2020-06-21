/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeWhiteCapturePromotionMoveTest {

	private Game game;
	
	@Before
	public void setup() {
		game = fromFen("3n1n1k/4P3/8/p7/8/8/8/7K w - a6 13 10");
	}
	
	@Test
	public void e7d8Queen() {
		test("e7d8q", WhiteQueen);
	}

	@Test
	public void e7d8Rook() {
		test("e7d8r", WhiteRook);
	}
	
	@Test
	public void e7d8Bishop() {
		test("e7d8b", WhiteBishop);
	}

	@Test
	public void e7d8Knight() {
		test("e7d8n", WhiteKnight);
	}

	@Test
	public void e7f8Queen() {
		test("e7f8q", WhiteQueen);
	}

	@Test
	public void e7f8Rook() {
		test("e7f8r", WhiteRook);
	}
	
	@Test
	public void e7f8Bishop() {
		test("e7f8b", WhiteBishop);
	}

	@Test
	public void e7f8Knight() {
		test("e7f8n", WhiteKnight);
	}
	
	private void test(String moveString, int promotedPieceType) {
		int move = find(moveString, game);
		makeMove(move, game);
		
		assertThat(game.piece(Move.to(move)).type(), is(promotedPieceType));
		assertThat(game.piece(Move.from(move)), is(nullValue()));
		assertThat(game.containsWhitePiece(game.piece(Move.to(move))), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(Move.from(move)).type(), is(WhitePawn));
		assertThat(game.piece(Move.to(move)).type(), is(BlackKnight));
		assertThat(game.containsWhitePiece(game.piece(Move.from(move))), is(true));
		assertThat(game.containsBlackPiece(game.piece(Move.to(move))), is(true));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(a6));
		assertThat(game.activeColor(), is(white));
	}
	
}
