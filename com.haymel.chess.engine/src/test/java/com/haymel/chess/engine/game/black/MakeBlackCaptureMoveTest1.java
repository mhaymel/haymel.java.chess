/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeBlackCaptureMoveTest1 {

	private Game game;
	private MakeMove moveMaker;
	private Piece blackKnight;
	private Piece whitePawn;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		blackKnight = new Piece(BlackKnight);
		blackKnight.field(a1);
		blackKnight.setMoved(false);
		game.addBlack(blackKnight);
		game.place(blackKnight);

		whitePawn = new Piece(WhitePawn);
		whitePawn.field(c2);
		game.addWhite(whitePawn);
		game.place(whitePawn);
		game.assertVerify();
		
		game.activeColorBlack();
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);
		game.fullMoveNumber(5);

		Move a1c2 = new Move(a1, c2, capture, whitePawn);
		
		moveMaker.makeMove(a1c2);
		game.assertVerify();
		assertThat(blackKnight.field(), is(c2));
		assertThat(game.piece(c2), is(blackKnight));
		assertThat(blackKnight.moved(), is(true));
		assertThat(game.piece(a1).free(), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(6));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(blackKnight.field(), is(a1));
		assertThat(game.piece(c2), is(whitePawn));
		assertThat(blackKnight.moved(), is(false));
		assertThat(game.piece(a1), is(blackKnight));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		game.assertVerify();

		Piece whiteEnpassantPawn = new Piece(WhitePawn);
		whiteEnpassantPawn.field(Field.h4);
		game.addWhite(whiteEnpassantPawn);
		game.place(whiteEnpassantPawn);
		game.activeColorWhite();
		game.enPassant(h3);
		game.halfMoveClock(13);
		game.activeColorBlack();

		Move a1c2 = new Move(a1, c2, capture, whitePawn);
		
		moveMaker.makeMove(a1c2);

		game.assertVerify();
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(h3));
	}
	
}
