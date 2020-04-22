/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	22.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.piece;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTypeTest {

	@Test
	public void whitePiecesAreWhite() {
		assertThat(PieceType.white(PieceType.WhitePawn), is(true));
		assertThat(PieceType.white(PieceType.WhiteRook), is(true));
		assertThat(PieceType.white(PieceType.WhiteKnight), is(true));
		assertThat(PieceType.white(PieceType.WhiteBishop), is(true));
		assertThat(PieceType.white(PieceType.WhiteQueen), is(true));
		assertThat(PieceType.white(PieceType.WhiteKing), is(true));
	}

	@Test
	public void whitePiecesAreNotBlack() {
		assertThat(PieceType.black(PieceType.WhitePawn), is(false));
		assertThat(PieceType.black(PieceType.WhiteRook), is(false));
		assertThat(PieceType.black(PieceType.WhiteKnight), is(false));
		assertThat(PieceType.black(PieceType.WhiteBishop), is(false));
		assertThat(PieceType.black(PieceType.WhiteQueen), is(false));
		assertThat(PieceType.black(PieceType.WhiteKing), is(false));
	}

	@Test
	public void blackPiecesAreBlack() {
		assertThat(PieceType.black(PieceType.BlackPawn), is(true));
		assertThat(PieceType.black(PieceType.BlackRook), is(true));
		assertThat(PieceType.black(PieceType.BlackKnight), is(true));
		assertThat(PieceType.black(PieceType.BlackBishop), is(true));
		assertThat(PieceType.black(PieceType.BlackQueen), is(true));
		assertThat(PieceType.black(PieceType.BlackKing), is(true));
	}

	@Test
	public void blackPiecesAreNotWhite() {
		assertThat(PieceType.white(PieceType.BlackPawn), is(false));
		assertThat(PieceType.white(PieceType.BlackRook), is(false));
		assertThat(PieceType.white(PieceType.BlackKnight), is(false));
		assertThat(PieceType.white(PieceType.BlackBishop), is(false));
		assertThat(PieceType.white(PieceType.BlackQueen), is(false));
		assertThat(PieceType.white(PieceType.BlackKing), is(false));
	}
	
}
