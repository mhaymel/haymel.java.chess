/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b3;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.field;
import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h6;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.leftDownDown;
import static com.haymel.chess.engine.board.Field.leftLeftDown;
import static com.haymel.chess.engine.board.Field.leftLeftUp;
import static com.haymel.chess.engine.board.Field.leftUpUp;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.rightDownDown;
import static com.haymel.chess.engine.board.Field.rightRightDown;
import static com.haymel.chess.engine.board.Field.rightRightUp;
import static com.haymel.chess.engine.board.Field.rightUpUp;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testToString() {
		assertThat(fieldAsString(a1), is("a1"));
		assertThat(fieldAsString(a2), is("a2"));
		assertThat(fieldAsString(a3), is("a3"));
		assertThat(fieldAsString(a4), is("a4"));
		assertThat(fieldAsString(a5), is("a5"));
		assertThat(fieldAsString(a6), is("a6"));
		assertThat(fieldAsString(a7), is("a7"));
		assertThat(fieldAsString(a8), is("a8"));

		assertThat(fieldAsString(b1), is("b1"));
		assertThat(fieldAsString(b2), is("b2"));
		assertThat(fieldAsString(b3), is("b3"));
		assertThat(fieldAsString(b4), is("b4"));
		assertThat(fieldAsString(b5), is("b5"));
		assertThat(fieldAsString(b6), is("b6"));
		assertThat(fieldAsString(b7), is("b7"));
		assertThat(fieldAsString(b8), is("b8"));
		
		assertThat(fieldAsString(c1), is("c1"));
		assertThat(fieldAsString(c2), is("c2"));
		assertThat(fieldAsString(c3), is("c3"));
		assertThat(fieldAsString(c4), is("c4"));
		assertThat(fieldAsString(c5), is("c5"));
		assertThat(fieldAsString(c6), is("c6"));
		assertThat(fieldAsString(c7), is("c7"));
		assertThat(fieldAsString(c8), is("c8"));
		
		assertThat(fieldAsString(d1), is("d1"));
		assertThat(fieldAsString(d2), is("d2"));
		assertThat(fieldAsString(d3), is("d3"));
		assertThat(fieldAsString(d4), is("d4"));
		assertThat(fieldAsString(d5), is("d5"));
		assertThat(fieldAsString(d6), is("d6"));
		assertThat(fieldAsString(d7), is("d7"));
		assertThat(fieldAsString(d8), is("d8"));
		
		assertThat(fieldAsString(e1), is("e1"));
		assertThat(fieldAsString(e2), is("e2"));
		assertThat(fieldAsString(e3), is("e3"));
		assertThat(fieldAsString(e4), is("e4"));
		assertThat(fieldAsString(e5), is("e5"));
		assertThat(fieldAsString(e6), is("e6"));
		assertThat(fieldAsString(e7), is("e7"));
		assertThat(fieldAsString(e8), is("e8"));
		
		assertThat(fieldAsString(f1), is("f1"));
		assertThat(fieldAsString(f2), is("f2"));
		assertThat(fieldAsString(f3), is("f3"));
		assertThat(fieldAsString(f4), is("f4"));
		assertThat(fieldAsString(f5), is("f5"));
		assertThat(fieldAsString(f6), is("f6"));
		assertThat(fieldAsString(f7), is("f7"));
		assertThat(fieldAsString(f8), is("f8"));
		
		assertThat(fieldAsString(g1), is("g1"));
		assertThat(fieldAsString(g2), is("g2"));
		assertThat(fieldAsString(g3), is("g3"));
		assertThat(fieldAsString(g4), is("g4"));
		assertThat(fieldAsString(g5), is("g5"));
		assertThat(fieldAsString(g6), is("g6"));
		assertThat(fieldAsString(g7), is("g7"));
		assertThat(fieldAsString(g8), is("g8"));
		
		assertThat(fieldAsString(h1), is("h1"));
		assertThat(fieldAsString(h2), is("h2"));
		assertThat(fieldAsString(h3), is("h3"));
		assertThat(fieldAsString(h4), is("h4"));
		assertThat(fieldAsString(h5), is("h5"));
		assertThat(fieldAsString(h6), is("h6"));
		assertThat(fieldAsString(h7), is("h7"));
		assertThat(fieldAsString(h8), is("h8"));
	}

	@Test
	public void testFile() {
		assertThat(rank(a1), is(0));
		assertThat(rank(a2), is(1));
		assertThat(rank(a8), is(7));
	}
	
	@Test
	public void knightMoves() {
		assertThat(leftLeftUp(e4), is(c5));
		assertThat(leftLeftDown(e4), is(c3));
		assertThat(leftUpUp(e4), is(d6));
		assertThat(rightUpUp(e4), is(f6));
		assertThat(rightRightUp(e4), is(g5));
		assertThat(rightRightDown(e4), is(g3));
		assertThat(rightDownDown(e4), is(f2));
		assertThat(leftDownDown(e4), is(d2));
	}

	@Test
	public void fieldReturnsCorrectFieldForFileAndRank() {
		assertThat(field(0, 0), is(a1));
		assertThat(field(7, 7), is(h8));
		assertThat(field(0, 7), is(a8));
		assertThat(field(7, 0), is(h1));
		assertThat(field(4, 3), is(e4));
	}

}
