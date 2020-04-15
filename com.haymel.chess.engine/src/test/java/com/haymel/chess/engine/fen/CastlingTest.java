/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class CastlingTest {

	private Game game;
	private Piece whiteKing;
	private Piece whiteRookA;
	private Piece whiteRookH;
	private Piece blackKing;
	private Piece blackRookA;
	private Piece blackRookH;
	
	@Before
	public void setup() {
		game = new Game();
		whiteKing = create(WhiteKing, e1);
		whiteRookA = create(WhiteRook, a1);
		whiteRookH = create(WhiteRook, h1);
		blackKing = create(BlackKing, e8);
		blackRookA = create(BlackRook, a8);
		blackRookH = create(BlackRook, h8);
	}
	
	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new Castling(null, "-");
	}

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullAsCastingThrowsException() {
		new Castling(game, null);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsCastingThrowsException() {
		new Castling(game, "");
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithStringLongerThanForCharactersThrowsException() {
		new Castling(game, "KQkq-");
	}
	
	@Test
	public void noCastlingOnEmptyBoardIsOk() {
		new Castling(game, "-").execute();
		
		assertThat(whiteKing.moved(), is(true));
		assertThat(whiteRookA.moved(), is(true));
		assertThat(whiteRookH.moved(), is(true));
		assertThat(blackKing.moved(), is(true));
		assertThat(blackRookA.moved(), is(true));
		assertThat(blackRookH.moved(), is(true));
	}

	@Test
	public void whiteKingsideCastling() {
		new Castling(game, "K").execute();
		
		assertThat(whiteKing.moved(), is(false));
		assertThat(whiteRookA.moved(), is(true));
		assertThat(whiteRookH.moved(), is(false));
		assertThat(blackKing.moved(), is(true));
		assertThat(blackRookA.moved(), is(true));
		assertThat(blackRookH.moved(), is(true));
	}

	@Test
	public void whiteQueensideCastling() {
		new Castling(game, "Q").execute();
		
		assertThat(whiteKing.moved(), is(false));
		assertThat(whiteRookA.moved(), is(false));
		assertThat(whiteRookH.moved(), is(true));
		assertThat(blackKing.moved(), is(true));
		assertThat(blackRookA.moved(), is(true));
		assertThat(blackRookH.moved(), is(true));
	}

	@Test
	public void blackKingsideCastling() {
		new Castling(game, "k").execute();
		
		assertThat(whiteKing.moved(), is(true));
		assertThat(whiteRookA.moved(), is(true));
		assertThat(whiteRookH.moved(), is(true));
		assertThat(blackKing.moved(), is(false));
		assertThat(blackRookA.moved(), is(true));
		assertThat(blackRookH.moved(), is(false));
	}

	@Test
	public void blackQueensideCastling() {
		new Castling(game, "q").execute();
		
		assertThat(whiteKing.moved(), is(true));
		assertThat(whiteRookA.moved(), is(true));
		assertThat(whiteRookH.moved(), is(true));
		assertThat(blackKing.moved(), is(false));
		assertThat(blackRookA.moved(), is(false));
		assertThat(blackRookH.moved(), is(true));
	}

	@Test
	public void castling() {
		new Castling(game, "KQkq").execute();
		
		assertThat(whiteKing.moved(), is(false));
		assertThat(whiteRookA.moved(), is(false));
		assertThat(whiteRookH.moved(), is(false));
		assertThat(blackKing.moved(), is(false));
		assertThat(blackRookA.moved(), is(false));
		assertThat(blackRookH.moved(), is(false));
	}
	
	private Piece create(PieceType type, int field) {
		Piece piece = new Piece(type, field);
		piece.setMoved(true);
		if (piece.white())
			game.addWhite(piece);
		else
			game.addBlack(piece);
		
		game.place(piece);
		return piece;
	}

}
