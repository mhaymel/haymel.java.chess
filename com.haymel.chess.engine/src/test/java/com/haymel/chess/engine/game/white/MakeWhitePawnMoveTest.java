/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
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

public class MakeWhitePawnMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e2e3() {
		Piece piece = new Piece(WhitePawn, e2);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2e3 = new Move(e2, e3, normal);
		moveMaker.makeMove(e2e3);

		game.assertVerify();
		assertThat(piece.field(), is(e3));
		assertThat(game.piece(e1) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e2));
		assertThat(game.piece(e3) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2e3EnPassantResetted() {
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

		Move e2e3 = new Move(e2, e3, normal);
		moveMaker.makeMove(e2e3);

		game.assertVerify();
		assertThat(game.enPassant(), is(removed));
		assertThat(piece.field(), is(e3));
		assertThat(game.piece(e1) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.activeColor(), is(black));

		moveMaker.undoMove();
		
		game.assertVerify();
		assertThat(game.enPassant(), is(e6));
		assertThat(piece.field(), is(e2));
		assertThat(game.piece(e3) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e3e4() {
		Piece piece = new Piece(BlackPawn, d4);
		game.addBlack(piece);
		game.place(piece);
		game.activeColorBlack();
		game.enPassant(removed);
		game.activeColorWhite();
		
		piece = new Piece(WhitePawn, e3);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2e3 = new Move(e3, e4, normal);
		moveMaker.makeMove(e2e3);

		game.assertVerify();
		assertThat(piece.field(), is(e4));
		assertThat(game.piece(e3) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e3));
		assertThat(game.piece(e4) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e2DoubleStepMove() {
		Piece piece = new Piece(WhitePawn, e2);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2e4 = new Move(e2, e4, pawnDoubleStep);
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

		Move e2e4 = new Move(e2, e4, pawnDoubleStep);
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

	@Test
	public void e2d3Capture() {
		Piece capturePiece = new Piece(BlackRook, d3);
		game.addBlack(capturePiece);
		game.place(capturePiece);

		Piece piece = new Piece(WhitePawn, e2);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e2d3 = new Move(e2, d3, capture, capturePiece);
		moveMaker.makeMove(e2d3);

		game.assertVerify();
		assertThat(piece.field(), is(d3));
		assertThat(game.piece(e2) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		assertThat(game.containsBlackPiece(capturePiece), is(false));
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e2));
		assertThat(game.piece(d3), is(capturePiece));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		assertThat(game.containsBlackPiece(capturePiece), is(true));
	}
	
	@Test
	public void e5d6Enpassant() {
		Piece blackPawn = new Piece(BlackPawn, d5);
		game.addBlack(blackPawn);
		game.place(blackPawn);
		game.activeColorBlack();
		game.enPassant(d6);
		game.activeColorWhite();
		
		Piece piece = new Piece(WhitePawn, e5);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e5d6 = new Move(e5, d6, enpassant, blackPawn);
		moveMaker.makeMove(e5d6);

		game.assertVerify();
		assertThat(game.containsBlackPiece(blackPawn), is(false));
		assertThat(piece.field(), is(d6));
		assertThat(game.piece(d5) == null, is(true));
		assertThat(game.piece(e5) == null, is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e5));
		assertThat(blackPawn.field(), is(d5));
		assertThat(game.piece(d5), is(blackPawn));
		assertThat(game.piece(d6) == null, is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(d6));
		assertThat(game.activeColor(), is(white));
		assertThat(game.containsBlackPiece(blackPawn), is(true));
	}
	
}
