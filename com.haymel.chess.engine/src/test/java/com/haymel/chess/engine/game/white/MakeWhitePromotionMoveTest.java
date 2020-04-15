/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class MakeWhitePromotionMoveTest {

	@Test
	public void promotion() {
		testPromotion(a7, a8);
		testPromotion(b7, b8);
		testPromotion(c7, c8);
		testPromotion(d7, d8);
		testPromotion(e7, e8);
		testPromotion(f7, f8);
		testPromotion(g7, g8);
		testPromotion(h7, h8);
	}

	private void testPromotion(int from, int to) {
		testPromotion(from, to, removed);
		testPromotion(from, to, e6);
	}	
	
	private void testPromotion(int from, int to, int enpassant) {
		testPromotion(from, to, WhiteQueen, enpassant);
		testPromotion(from, to, WhiteRook, enpassant);
		testPromotion(from, to, WhiteBishop, enpassant);
		testPromotion(from, to, WhiteKnight, enpassant);
	}
	
	private void testPromotion(int from, int to, PieceType promo, int enpassant) {
		Game game = new Game();
		MakeMove moveMaker = new MakeMove(game);

		addPotentialEnpassant(game);

		game.activeColorBlack();
		game.enPassant(enpassant);
		game.activeColorWhite();
		
		Piece piece = new Piece(WhitePawn, from);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.fullMoveNumber(10);
		game.assertVerify();

		Move e2e1 = new Move(from, to, promo);
		moveMaker.makeMove(e2e1);

		game.assertVerify();
		assertThat(piece.field(), is(to));
		assertThat(game.piece(from).free(), is(true));
		assertThat(piece.type(), is(promo));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(from));
		assertThat(game.piece(to).free(), is(true));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(enpassant));
		assertThat(game.activeColor(), is(white));
	}

	private void addPotentialEnpassant(Game game) {
		Piece piece = new Piece(BlackPawn, e5);
		game.addBlack(piece);
		game.place(piece);
	}

}
