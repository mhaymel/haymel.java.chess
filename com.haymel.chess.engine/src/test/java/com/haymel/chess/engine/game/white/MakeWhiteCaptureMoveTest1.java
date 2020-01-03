/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhiteCaptureMoveTest1 {

	private Game game;
	private MakeMove moveMaker;
	private Piece whiteKnight;
	private Piece blackPawn;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		whiteKnight = new Piece(WhiteKnight);
		whiteKnight.field(a1);
		whiteKnight.setMoved(false);
		game.addWhite(whiteKnight);
		game.place(whiteKnight);

		blackPawn = new Piece(BlackPawn);
		blackPawn.field(c2);
		game.addBlack(blackPawn);
		game.place(blackPawn);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);

		Move a1c2 = new Move(a1, c2, capture, blackPawn);
		
		moveMaker.makeMove(a1c2);
		game.assertVerify();
		assertThat(whiteKnight.field(), is(c2));
		assertThat(game.piece(c2), is(whiteKnight));
		assertThat(whiteKnight.moved(), is(true));
		assertThat(game.piece(a1).free(), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(whiteKnight.field(), is(a1));
		assertThat(game.piece(c2), is(blackPawn));
		assertThat(whiteKnight.moved(), is(false));
		assertThat(game.piece(a1), is(whiteKnight));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		game.assertVerify();

		Piece blackEnpassantPawn = new Piece(BlackPawn);
		blackEnpassantPawn.field(a5);
		game.addBlack(blackEnpassantPawn);
		game.place(blackEnpassantPawn);
		game.activeColorBlack();
		game.enPassant(a6);
		
		game.halfMoveClock(13);
		game.activeColorWhite();

		Move a1c2 = new Move(a1, c2, capture, blackPawn);
		
		moveMaker.makeMove(a1c2);

		game.assertVerify();
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(a6));
	}
	
}
