/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhitePawnDoubleStepMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e2DoubleStepMove() {
		Piece piece = new Piece(WhitePawn, e2);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2e4 = new Move(e2, e4, normal);
		moveMaker.makeMove(e2e4);

		game.assertVerify();
		assertThat(piece.field(), is(e4));
		assertThat(game.piece(e2) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(e3));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e2));
		assertThat(game.piece(e4) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2DoubleStepMoveEnPassantResetted() {
		Piece piece = new Piece(BlackPawn, e5);
		game.addBlack(piece);
		game.place(piece);
		game.activeColorBlack();
		game.enPassant(e6);
		game.activeColorWhite();
		
		piece = new Piece(WhitePawn, e2);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2e4 = new Move(e2, e4, normal);
		moveMaker.makeMove(e2e4);

		game.assertVerify();
		assertThat(piece.field(), is(e4));
		assertThat(game.piece(e2) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(e3));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e2));
		assertThat(game.piece(e4) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(e6));
		assertThat(game.activeColor(), is(white));
	}

}
