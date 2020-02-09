/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class MakeBlackPromotionMoveTest {

	@Test
	public void promotion() {
		testPromotion(a2, a1);
		testPromotion(b2, b1);
		testPromotion(c2, c1);
		testPromotion(d2, d1);
		testPromotion(e2, e1);
		testPromotion(f2, f1);
		testPromotion(g2, g1);
		testPromotion(h2, h1);
	}

	private void testPromotion(Field from, Field to) {
		testPromotion(from, to, removed);
		testPromotion(from, to, e3);
	}	
	
	private void testPromotion(Field from, Field to, Field enpassant) {
		testPromotion(from, to, BlackQueen, enpassant);
		testPromotion(from, to, BlackRook, enpassant);
		testPromotion(from, to, BlackBishop, enpassant);
		testPromotion(from, to, BlackKnight, enpassant);
	}
	
	private void testPromotion(Field from, Field to, PieceType promo, Field enpassant) {
		Game game = new Game();
		MakeMove moveMaker = new MakeMove(game);

		addPotentialEnpassant(game);

		game.activeColorWhite();
		game.enPassant(enpassant);
		game.activeColorBlack();
	
		Piece piece = new Piece(BlackPawn, from);
		game.addBlack(piece);
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
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(from));
		assertThat(game.piece(to).free(), is(true));
		assertThat(piece.blackPawn(), is(true));
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(enpassant));
		assertThat(game.activeColor(), is(black));
	}

	private void addPotentialEnpassant(Game game) {
		Piece piece = new Piece(WhitePawn, e4);
		game.addWhite(piece);
		game.place(piece);
	}

}
