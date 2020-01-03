/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class MakeBlackPawnCapturePromotionMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e2d1Queen() {
		test(e2, d1, BlackQueen);
	}

	@Test
	public void e2d1Rook() {
		test(e2, d1, BlackRook);
	}
	
	@Test
	public void e2f1Queen() {
		test(e2, f1, BlackQueen);
	}
	
	@Test
	public void e7f8Rook() {
		test(e2, f1, BlackRook);
	}
	
	private void test(Field from, Field to, PieceType promo) {
		Piece whitePiece = new Piece(WhiteKnight);
		whitePiece.field(to);
		game.addWhite(whitePiece);
		game.place(whitePiece);
		
		Piece piece = new Piece(BlackPawn);
		piece.field(from);
		game.addBlack(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.fullMoveNumber(45);
		game.activeColorBlack();
		game.assertVerify();

		Move e7d8Q = new Move(from, to, whitePiece, promo);
		moveMaker.makeMove(e7d8Q);

		game.assertVerify();
		assertThat(piece.field(), is(to));
		assertThat(game.piece(from).free(), is(true));
		assertThat(piece.type(), is(promo));
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.containsWhitePiece(whitePiece), is(false));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(46));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(from));
		assertThat(game.piece(to), is(whitePiece));
		assertThat(piece.blackPawn(), is(true));
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.containsWhitePiece(whitePiece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(45));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
	}
	
}
