/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.Enpassant;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class EnpassantTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new Enpassant(null, "a3");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsFieldAsStringThrowsException() {
		new Enpassant(game, null);
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsFieldAsStringThrowsException() {
		new Enpassant(game, "");
	}

	@Test
	public void noEnpassantFieldWhite() {
		Piece whitePawn = new Piece(WhitePawn, a4);
		game.addWhite(whitePawn);
		game.place(whitePawn);
		game.activeColorWhite();
		game.enPassant(a3);

		new Enpassant(game, "-").execute();
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void noEnpassantFieldBlack() {
		Piece blackPawn = new Piece(BlackPawn, a5);
		game.addBlack(blackPawn);
		game.place(blackPawn);
		game.activeColorBlack();
		game.enPassant(a6);
		
		new Enpassant(game, "-").execute();
		assertThat(game.enPassant(), is(removed));
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalCharacterInFieldThrowsException() {
		new Enpassant(game, "x7").execute();
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalDigitInFieldThrowsException() {
		new Enpassant(game, "e9").execute();
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void illegalWhiteEnpassantFieldThrowsException() {
		new Enpassant(game, "a4").execute();
	}

	@Test
	public void enpassantFieldWhite() {
		Piece whitePawn = new Piece(WhitePawn, a4);
		game.addWhite(whitePawn);
		game.place(whitePawn);
		game.activeColorBlack();

		new Enpassant(game, "a3").execute();
		assertThat(game.enPassant(), is(a3));
	}

}
