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

public class MakeBlackEnpassantMoveTest {

	private Game game;
	private MakeMove moveMaker;
	private Piece blackPawn;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		blackPawn = new Piece(BlackPawn, e4);
		game.addBlack(blackPawn);
		game.place(blackPawn);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndoLeftEnpassant() {
		game.assertVerify();

		Piece whitePawn = new Piece(WhitePawn, d4);
		game.addWhite(whitePawn);
		game.place(whitePawn);

		game.activeColorWhite();
		game.enPassant(d3);
		
		game.activeColorBlack();
		Move c5d6 = new Move(e4, d3, enpassant, whitePawn);
		game.halfMoveClock(13);
		game.fullMoveNumber(10);
		game.assertVerify();
		
		moveMaker.makeMove(c5d6);

		game.assertVerify();
		assertThat(blackPawn.field(), is(d3));
		assertThat(game.piece(d4) == null, is(true));
		assertThat(game.piece(e4) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();
		game.assertVerify();

		assertThat(blackPawn.field(), is(e4));
		assertThat(game.piece(e4), is(blackPawn));
		assertThat(whitePawn.field(), is(d4));
		assertThat(game.piece(d4), is(whitePawn));
		
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(d3));
		assertThat(game.activeColor(), is(black));
	}

	@Test
	public void makeAndUndoRightEnpassant() {
		game.assertVerify();

		Piece whitePawn = new Piece(WhitePawn, f4);
		game.addWhite(whitePawn);
		game.place(whitePawn);

		game.activeColorWhite();
		game.enPassant(f3);
		
		game.activeColorBlack();
		Move c5d6 = new Move(e4, f3, enpassant, whitePawn);
		game.halfMoveClock(13);
		game.fullMoveNumber(10);
		game.assertVerify();
		
		moveMaker.makeMove(c5d6);

		game.assertVerify();
		assertThat(blackPawn.field(), is(f3));
		assertThat(game.piece(f4) == null, is(true));
		assertThat(game.piece(e4) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();
		game.assertVerify();

		assertThat(blackPawn.field(), is(e4));
		assertThat(game.piece(e4), is(blackPawn));
		assertThat(whitePawn.field(), is(f4));
		assertThat(game.piece(f4), is(whitePawn));
		
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(f3));
		assertThat(game.activeColor(), is(black));
	}

}
