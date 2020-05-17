/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
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
import com.haymel.chess.engine.moves.Move;

public class MakeBlackEnpassantMoveTest {

	@Test
	public void makeAndUndoLeftEnpassant() {
		Game game = fromFen("7k/8/8/8/3Pp3/8/8/7K b - d3 13 10");
		Move move = find("e4d3", game);
		
		makeMove(move, game);
		assertThat(game.piece(d3).type(), is(BlackPawn));
		assertThat(game.piece(d4), is(nullValue()));
		assertThat(game.piece(e4), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		undoMove(game);
		assertThat(game.piece(e4).type(), is(BlackPawn));
		assertThat(game.piece(d4).type(), is(WhitePawn));
		assertThat(game.piece(d3), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(d3));
		assertThat(game.activeColor(), is(black));
	}

	@Test
	public void makeAndUndoRightEnpassant() {
		Game game = fromFen("7k/8/8/8/4pP2/8/8/7K b - f3 13 10");
		Move move = find("e4f3", game);
		
		makeMove(move, game);
		assertThat(game.piece(f3).type(), is(BlackPawn));
		assertThat(game.piece(d4), is(nullValue()));
		assertThat(game.piece(f4), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		undoMove(game);
		assertThat(game.piece(e4).type(), is(BlackPawn));
		assertThat(game.piece(f4).type(), is(WhitePawn));
		assertThat(game.piece(f3), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(f3));
		assertThat(game.activeColor(), is(black));
	}

}
