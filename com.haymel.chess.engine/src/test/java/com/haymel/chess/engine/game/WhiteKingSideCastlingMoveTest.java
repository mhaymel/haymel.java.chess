/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class WhiteKingSideCastlingMoveTest {

	private Game game;
	private MoveMaker moveMaker;
	private Piece king;
	private Piece rook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MoveMaker(game);

		king = new Piece(WhiteKing);
		king.field(e1);
		king.setMoved(false);
		game.addWhite(king);
		game.place(king);
		
		rook = new Piece(WhiteRook);
		rook.field(h1);
		rook.setMoved(false);
		game.addWhite(rook);
		game.place(rook);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndo() {
		Move e1g1 = new Move(e1, g1, kingsideCastling);
		
		moveMaker.makeMove(e1g1);
		assertThat(king.field(), is(g1));
		assertThat(king.moved(), is(true));
		assertThat(game.piece(g1), is(king));
		assertThat(game.piece(e1).free(), is(true));

		assertThat(rook.field(), is(f1));
		assertThat(rook.moved(), is(true));
		assertThat(game.piece(f1), is(rook));
		assertThat(game.piece(h1).free(), is(true));
		
		assertThat(game.halfMoveClock(), is(1));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(king.field(), is(e1));
		assertThat(king.moved(), is(false));
		assertThat(game.piece(e1), is(king));
		assertThat(game.piece(g1).free(), is(true));
		assertThat(game.piece(f1).free(), is(true));
		assertThat(game.piece(h1).whiteRook(), is(true));
		assertThat(game.piece(h1).moved(), is(false));
		assertThat(game.piece(h1), is(rook));
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
		game.activeColorWhite();

		Move e1g1 = new Move(e1, g1, kingsideCastling);
		moveMaker.makeMove(e1g1);
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(a6));
	}
	
}
