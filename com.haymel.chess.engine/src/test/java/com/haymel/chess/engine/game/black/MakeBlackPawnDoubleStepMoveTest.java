/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
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

public class MakeBlackPawnDoubleStepMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e2DoubleStepMove() {
		Piece piece = new Piece(BlackPawn);
		piece.field(e7);
		game.addBlack(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.fullMoveNumber(37);
		game.activeColorBlack();
		game.assertVerify();

		Move e7e5 = new Move(e7, e5, pawnDoubleStep);
		moveMaker.makeMove(e7e5);

		game.assertVerify();
		assertThat(piece.field(), is(e5));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(38));
		assertThat(game.enPassant(), is(e6));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e5).free(), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(37));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
	}
	
	@Test
	public void e2DoubleStepMoveEnPassantResetted() {
		game.activeColorWhite();
		Piece piece = new Piece(WhitePawn);
		piece.field(e4);
		game.addWhite(piece);
		game.place(piece);
		game.enPassant(e3);
		game.activeColorBlack();
		

		piece = new Piece(BlackPawn);
		piece.field(e7);
		game.addBlack(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.fullMoveNumber(37);
		game.activeColorBlack();
		game.assertVerify();

		Move e7e5 = new Move(e7, e5, pawnDoubleStep);
		moveMaker.makeMove(e7e5);

		game.assertVerify();
		assertThat(piece.field(), is(e5));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(38));
		assertThat(game.enPassant(), is(e6));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e5).free(), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(37));
		assertThat(game.enPassant(), is(e3));
		assertThat(game.activeColor(), is(black));
	}

}
