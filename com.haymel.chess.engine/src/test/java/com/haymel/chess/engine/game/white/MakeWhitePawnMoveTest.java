/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e6;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;

public class MakeWhitePawnMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e2e3() {
		Game game = fromFen("7k/8/8/8/8/8/4P3/7K w - - 45 30");
		Move move = find("e2e3", game);
		
		makeMove(move, game);
		assertThat(game.piece(e3).type(), is(WhitePawn));
		assertThat(game.piece(e2), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(e2).type(), is(WhitePawn));
		assertThat(game.piece(e3), is(nullValue()));
		assertThat(game.halfMoveClock(), is(45));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e3e4() {
		Game game = fromFen("7k/8/8/8/8/4P3/8/7K w - - 45 30");
		Move move = find("e3e4", game);
		
		makeMove(move, game);
		assertThat(game.piece(e4).type(), is(WhitePawn));
		assertThat(game.piece(e3), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));

		undoMove(game);
		assertThat(game.piece(e3).type(), is(WhitePawn));
		assertThat(game.piece(e4), is(nullValue()));
		assertThat(game.halfMoveClock(), is(45));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2e3EnPassantResetted() {
		Game game = fromFen("7k/8/8/4p3/8/8/4P3/7K w - e6 45 30");
		Move move = find("e2e3", game);
		
		makeMove(move, game);
		assertThat(game.piece(e3).type(), is(WhitePawn));
		assertThat(game.piece(e2), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(e2).type(), is(WhitePawn));
		assertThat(game.piece(e3), is(nullValue()));
		assertThat(game.halfMoveClock(), is(45));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(e6));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2d3Capture() {
		Game game = fromFen("7k/8/8/8/8/3p4/4P3/7K w - - 45 30");
		Move move = find("e2d3", game);
		
		makeMove(move, game);
		assertThat(game.piece(d3).type(), is(WhitePawn));
		assertThat(game.piece(e2), is(nullValue()));
		assertThat(move.capturedPiece(), is(notNullValue()));
		assertThat(move.capturedPiece().type(), is(BlackPawn));
		assertThat(game.containsBlackPiece(move.capturedPiece()), is(false));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		undoMove(game);
		assertThat(game.piece(e2).type(), is(WhitePawn));
		assertThat(game.piece(d3).type(), is(BlackPawn));
		assertThat(game.containsBlackPiece(move.capturedPiece()), is(true));
		assertThat(game.halfMoveClock(), is(45));
		assertThat(game.fullMoveNumber(), is(30));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
}
