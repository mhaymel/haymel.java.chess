/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhiteMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void makeAndUndo() {
		Piece king = new Piece(WhiteKing);
		king.field(e1);
		king.setMoved(false);
		game.addWhite(king);
		game.place(king);
		
		Move e1e2 = new Move(e1, e2);
		
		moveMaker.makeMove(e1e2);
		assertThat(king.field(), is(e2));
		assertThat(king.moved(), is(true));
		assertThat(game.piece(e1).free(), is(true));
		assertThat(game.halfMoveClock(), is(1));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(king.field(), is(e1));
		assertThat(king.moved(), is(false));
		assertThat(game.piece(e2).free(), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		
		Piece blackEnpassantPawn = new Piece(BlackPawn);
		blackEnpassantPawn.field(a5);
		game.addBlack(blackEnpassantPawn);
		game.place(blackEnpassantPawn);
		game.activeColorBlack();
		game.enPassant(a6);
		
		Piece king = new Piece(WhiteKing);
		king.field(e1);
		king.setMoved(false);
		game.addWhite(king);
		game.place(king);
		
		game.activeColorWhite();
		
		Move e1e2 = new Move(e1, e2);
		
		moveMaker.makeMove(e1e2);
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(a6));
	}
	
	@Test
	public void movedIsSetCorrectly() {
		Piece king = new Piece(WhiteKing);
		king.field(e1);
		king.setMoved(true);
		game.addWhite(king);
		game.place(king);
		
		Move e1e2 = new Move(e1, e2);
		
		moveMaker.makeMove(e1e2);
		assertThat(king.moved(), is(true));
		
		moveMaker.undoMove();
		assertThat(king.moved(), is(true));
	}
	
}
