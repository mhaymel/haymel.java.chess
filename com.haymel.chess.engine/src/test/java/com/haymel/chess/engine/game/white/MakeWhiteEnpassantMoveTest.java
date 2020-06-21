/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;

public class MakeWhiteEnpassantMoveTest {

	@Test
	public void makeAndUndoLeftEnpassant() {
		Game game = fromFen("7k/8/8/2pP4/8/8/8/7K w - c6 13 10");
		int move = find("d5c6", game);
		
		makeMove(move, game);
		assertThat(game.piece(c6).type(), is(WhitePawn));
		assertThat(game.piece(c5), is(nullValue()));
		assertThat(game.piece(d5), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(c5).type(), is(BlackPawn));
		assertThat(game.piece(d5).type(), is(WhitePawn));
		assertThat(game.piece(c6), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(c6));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void makeAndUndoRightEnpassant() {
		Game game = fromFen("7k/8/8/2Pp4/8/8/8/7K w - d6 13 10");
		int move = find("c5d6", game);
		
		makeMove(move, game);
		assertThat(game.piece(d6).type(), is(WhitePawn));
		assertThat(game.piece(d5), is(nullValue()));
		assertThat(game.piece(c5), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(c5).type(), is(WhitePawn));
		assertThat(game.piece(d5).type(), is(BlackPawn));
		assertThat(game.piece(d6), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(d6));
		assertThat(game.activeColor(), is(white));
	}
	
}
