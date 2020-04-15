/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
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
import com.haymel.chess.engine.piece.PieceType;

public class MakeWhitePawnCapturePromotionMoveTest {

	private Game game;
	private MakeMove moveMaker;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
	}
	
	@Test
	public void e7d8Queen() {
		test(e7, d8, WhiteQueen);
	}

	@Test
	public void e7d8Rook() {
		test(e7, d8, WhiteRook);
	}
	
	@Test
	public void e7f8Queen() {
		test(e7, f8, WhiteQueen);
	}
	
	@Test
	public void e7f8Rook() {
		test(e7, f8, WhiteRook);
	}
	
	private void test(int from, int to, PieceType promo) {
		Piece blackPiece = new Piece(BlackKnight, to);
		game.addBlack(blackPiece);
		game.place(blackPiece);
		
		Piece piece = new Piece(WhitePawn, from);
		game.addWhite(piece);
		game.place(piece);
		game.halfMoveClock(30);
		game.fullMoveNumber(45);
		game.assertVerify();

		Move e7d8Q = new Move(from, to, blackPiece, promo);
		moveMaker.makeMove(e7d8Q);

		game.assertVerify();
		assertThat(piece.field(), is(to));
		assertThat(game.piece(from).free(), is(true));
		assertThat(piece.type(), is(promo));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.containsBlackPiece(blackPiece), is(false));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(45));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(black));
		
		moveMaker.undoMove();

		game.assertVerify();
		assertThat(piece.field(), is(from));
		assertThat(game.piece(to), is(blackPiece));
		assertThat(piece.whitePawn(), is(true));
		assertThat(game.containsWhitePiece(piece), is(true));
		assertThat(game.containsBlackPiece(blackPiece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(45));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
	}
	
}
