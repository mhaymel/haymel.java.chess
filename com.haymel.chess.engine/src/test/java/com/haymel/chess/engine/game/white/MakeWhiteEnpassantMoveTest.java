/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
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

public class MakeWhiteEnpassantMoveTest {

	private Game game;
	private MakeMove moveMaker;
	private Piece whitePawn;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		whitePawn = new Piece(WhitePawn, c5);
		game.addWhite(whitePawn);
		game.place(whitePawn);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndoRightEnpassant() {
		game.assertVerify();

		Piece blackPawn = new Piece(BlackPawn, d5);
		game.addBlack(blackPawn);
		game.place(blackPawn);

		game.activeColorBlack();
		game.enPassant(d6);
		
		game.activeColorWhite();
		Move c5d6 = new Move(c5, d6, enpassant, blackPawn);
		game.halfMoveClock(13);
		game.fullMoveNumber(10);
		game.assertVerify();
		
		moveMaker.makeMove(c5d6);

		game.assertVerify();
		assertThat(whitePawn.field(), is(d6));
		assertThat(game.piece(d5) == null, is(true));
		assertThat(game.piece(c5) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();
		game.assertVerify();

		assertThat(whitePawn.field(), is(c5));
		assertThat(game.piece(c5), is(whitePawn));
		assertThat(blackPawn.field(), is(d5));
		assertThat(game.piece(d5), is(blackPawn));
		
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(d6));
		assertThat(game.activeColor(), is(white));
	}

	@Test
	public void makeAndUndoLeftEnpassant() {
		game.assertVerify();

		Piece blackPawn = new Piece(BlackPawn, b5);
		game.addBlack(blackPawn);
		game.place(blackPawn);

		game.activeColorBlack();
		game.enPassant(b6);
		
		game.activeColorWhite();
		Move c5b6 = new Move(c5, b6, enpassant, blackPawn);
		game.halfMoveClock(13);
		game.fullMoveNumber(10);
		game.assertVerify();
		
		moveMaker.makeMove(c5b6);

		game.assertVerify();
		assertThat(whitePawn.field(), is(b6));
		assertThat(game.piece(b5) == null, is(true));
		assertThat(game.piece(c5) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();
		game.assertVerify();

		assertThat(whitePawn.field(), is(c5));
		assertThat(game.piece(c5), is(whitePawn));
		assertThat(blackPawn.field(), is(b5));
		assertThat(game.piece(b5), is(blackPawn));
		
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(b6));
		assertThat(game.activeColor(), is(white));
	}

}
