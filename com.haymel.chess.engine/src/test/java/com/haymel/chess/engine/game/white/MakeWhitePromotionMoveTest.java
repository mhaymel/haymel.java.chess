/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhitePromotionMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e7e8PromotionQueen() {
		Piece piece = new Piece(WhitePawn);
		piece.field(e7);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e7e8Q = new Move(e7, e8, WhiteQueen);
		moveMaker.makeMove(e7e8Q);

		game.assertVerify();
		assertThat(piece.field(), is(e8));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(piece.whiteQueen(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e8).free(), is(true));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void e7e8PromotionRook() {
		Piece piece = new Piece(WhitePawn);
		piece.field(e7);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e7e8Q = new Move(e7, e8, WhiteRook);
		moveMaker.makeMove(e7e8Q);

		game.assertVerify();
		assertThat(piece.field(), is(e8));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(piece.whiteRook(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e8).free(), is(true));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}

	@Test
	public void e7e8PromotionBishop() {
		Piece piece = new Piece(WhitePawn);
		piece.field(e7);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e7e8Q = new Move(e7, e8, WhiteBishop);
		moveMaker.makeMove(e7e8Q);

		game.assertVerify();
		assertThat(piece.field(), is(e8));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(piece.whiteBishop(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e8).free(), is(true));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}

	@Test
	public void e7e8PromotionKnight() {
		Piece piece = new Piece(WhitePawn);
		piece.field(e7);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.assertVerify();

		Move e7e8Q = new Move(e7, e8, WhiteKnight);
		moveMaker.makeMove(e7e8Q);

		game.assertVerify();
		assertThat(piece.field(), is(e8));
		assertThat(game.piece(e7).free(), is(true));
		assertThat(piece.whiteKnight(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(e7));
		assertThat(game.piece(e8).free(), is(true));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
}
