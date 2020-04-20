/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeBlackKingSideCastlingMoveTest {

	private Game game;
	private MakeMove moveMaker;
	private Piece king;
	private Piece rook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);

		king = new Piece(BlackKing, e8);
		king.setMoved(false);
		game.addBlack(king);
		game.place(king);
		
		rook = new Piece(BlackRook, h8);
		rook.setMoved(false);
		game.addBlack(rook);
		game.place(rook);
		game.assertVerify();
		
		game.activeColorBlack();
	}
	
	@Test
	public void makeAndUndo() {
		game.fullMoveNumber(5);

		Move e8g8 = new Move(e8, g8, kingsideCastling);
		
		moveMaker.makeMove(e8g8);
		assertThat(king.field(), is(g8));
		assertThat(king.moved(), is(true));
		assertThat(game.piece(g8), is(king));
		assertThat(game.piece(e8) == null, is(true));

		assertThat(rook.field(), is(f8));
		assertThat(rook.moved(), is(true));
		assertThat(game.piece(f8), is(rook));
		assertThat(game.piece(h8) == null, is(true));
		
		assertThat(game.halfMoveClock(), is(1));
		assertThat(game.fullMoveNumber(), is(6));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(king.field(), is(e8));
		assertThat(king.moved(), is(false));
		assertThat(game.piece(e8), is(king));
		assertThat(game.piece(g8) == null, is(true));
		assertThat(game.piece(f8) == null, is(true));
		assertThat(game.piece(h8).blackRook(), is(true));
		assertThat(game.piece(h8).moved(), is(false));
		assertThat(game.piece(h8), is(rook));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(5));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Piece whiteEnpassantPawn = new Piece(WhitePawn, a4);
		game.addWhite(whiteEnpassantPawn);
		game.place(whiteEnpassantPawn);
		game.activeColorWhite();
		game.enPassant(a3);
		game.activeColorBlack();

		Move e8g8 = new Move(e8, g8, kingsideCastling);
		moveMaker.makeMove(e8g8);
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(a3));
	}
	
}
