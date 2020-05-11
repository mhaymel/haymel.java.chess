/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.castling.PositionCastlingRight;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class CastlingTest {

	private PositionCastlingRight castlingRight;
	
	@Before
	public void setup() {
		castlingRight = new PositionCastlingRight();
	}
	
	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullAsGameThrowsException() {
		new Castling(null, "-");
	}

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullAsCastingThrowsException() {
		new Castling(castlingRight, null);
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithEmptyStringAsCastingThrowsException() {
		new Castling(castlingRight, "");
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void constructorWithStringLongerThanForCharactersThrowsException() {
		new Castling(castlingRight, "KQkq-");
	}
	
	@Test
	public void noCastlingOnEmptyBoardIsOk() {
		new Castling(castlingRight, "-").execute();
		
		assertThat(castlingRight.white().kingside(), is(false));
		assertThat(castlingRight.white().queenside(), is(false));
		assertThat(castlingRight.black().kingside(), is(false));
		assertThat(castlingRight.black().queenside(), is(false));
	}

	@Test
	public void whiteKingsideCastling() {
		new Castling(castlingRight, "K").execute();
		
		assertThat(castlingRight.white().kingside(), is(true));
		assertThat(castlingRight.white().queenside(), is(false));
		assertThat(castlingRight.black().kingside(), is(false));
		assertThat(castlingRight.black().queenside(), is(false));
	}

	@Test
	public void whiteQueensideCastling() {
		new Castling(castlingRight, "Q").execute();
		
		assertThat(castlingRight.white().kingside(), is(false));
		assertThat(castlingRight.white().queenside(), is(true));
		assertThat(castlingRight.black().kingside(), is(false));
		assertThat(castlingRight.black().queenside(), is(false));
	}

	@Test
	public void blackKingsideCastling() {
		new Castling(castlingRight, "k").execute();
		
		assertThat(castlingRight.white().kingside(), is(false));
		assertThat(castlingRight.white().queenside(), is(false));
		assertThat(castlingRight.black().kingside(), is(true));
		assertThat(castlingRight.black().queenside(), is(false));
	}

	@Test
	public void blackQueensideCastling() {
		new Castling(castlingRight, "q").execute();
		
		assertThat(castlingRight.white().kingside(), is(false));
		assertThat(castlingRight.white().queenside(), is(false));
		assertThat(castlingRight.black().kingside(), is(false));
		assertThat(castlingRight.black().queenside(), is(true));
	}

	@Test
	public void castling() {
		new Castling(castlingRight, "KQkq").execute();
		
		assertThat(castlingRight.white().kingside(), is(true));
		assertThat(castlingRight.white().queenside(), is(true));
		assertThat(castlingRight.black().kingside(), is(true));
		assertThat(castlingRight.black().queenside(), is(true));
	}

}
