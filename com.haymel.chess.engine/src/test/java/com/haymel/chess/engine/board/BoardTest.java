/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.left;
import static com.haymel.chess.engine.piece.PieceType.white;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public class BoardTest {	//TODO implement

	
	@Test
	public void test() {
		Piece piece = Board.newBoard()[a1];
		
		assertThat(piece == null, is(true));
	}

	@Test
	public void testBorderElement() {
		Piece piece = Board.newBoard()[left(a1)];
		assertThat(piece == null, is(false));
		assertThat(PieceType.black(piece.type()), is(false));
		assertThat(white(piece.type()), is(false));
	}

}
