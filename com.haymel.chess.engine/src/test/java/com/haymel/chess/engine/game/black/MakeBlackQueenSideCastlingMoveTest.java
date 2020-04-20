/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
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

public class MakeBlackQueenSideCastlingMoveTest {

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
		
		rook = new Piece(BlackRook, a8);
		rook.setMoved(false);
		game.addBlack(rook);
		game.place(rook);
	}
	
	@Test
	public void makeAndUndo() {
		makeAndUndo(removed);
	}

	@Test
	public void makeAndUndoEnPassant() {
		makeAndUndo(e3);
	}

	private void makeAndUndo(int enpassant) {
		addPotentialEnpassant(game);
		game.activeColorWhite();
		game.enPassant(enpassant);
		
		game.halfMoveClock(3);
		game.fullMoveNumber(10);
		game.activeColorBlack();
		
		Move e8c8 = new Move(e8, c8, queensideCastling);
		
		moveMaker.makeMove(e8c8);
		assertThat(king.field(), is(c8));
		assertThat(king.moved(), is(true));
		assertThat(game.piece(c8), is(king));
		assertThat(game.piece(e8) == null, is(true));

		assertThat(rook.field(), is(d8));
		assertThat(rook.moved(), is(true));
		assertThat(game.piece(d8), is(rook));
		assertThat(game.piece(a1) == null, is(true));
		
		assertThat(game.halfMoveClock(), is(4));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(king.field(), is(e8));
		assertThat(king.moved(), is(false));
		assertThat(game.piece(e8), is(king));
		assertThat(game.piece(d8) == null, is(true));
		assertThat(game.piece(c8) == null, is(true));
		assertThat(game.piece(a8).blackRook(), is(true));
		assertThat(game.piece(a8).moved(), is(false));
		assertThat(game.piece(a8), is(rook));
		assertThat(game.halfMoveClock(), is(3));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(enpassant));
	}
	
	private void addPotentialEnpassant(Game game) {
		Piece piece = new Piece(WhitePawn, e4);
		game.addWhite(piece);
		game.place(piece);
	}
	
//	@Test				
//	public void enPassantIsSetCorrectly() {						//TODO ???
//		Piece blackEnpassantPawn = new Piece(BlackPawn);
//		blackEnpassantPawn.field(a5);
//		game.addBlack(blackEnpassantPawn);
//		game.place(blackEnpassantPawn);
//		game.activeColorBlack();
//		game.enPassant(a6);
//		game.activeColorWhite();
//
//		Move e1c1 = new Move(e1, c1, queensideCastling);
//		moveMaker.makeMove(e1c1);
//		assertThat(game.enPassant(), is(removed));
//		
//		moveMaker.undoMove();
//		assertThat(game.enPassant(), is(a6));
//	}
	
}
