/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.GameStartPos;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;

public class BlackMovesTest {

	@Test
	public void testStartPos() {
		Game game = new GameStartPos().startPos();
		Moves moves = game.blackMoves();

		assertThat(moves.size(), is(20));
		Set<Move> result = movesAsSet(moves);
		assertThat(result.contains(new Move(Field.a7, Field.a5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.b7, Field.b5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.c7, Field.c5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.d7, Field.d5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.e7, Field.e5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.f7, Field.f5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.g7, Field.g5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.h7, Field.h5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.a7, Field.a6, normal)), is(true));
		assertThat(result.contains(new Move(Field.b7, Field.b6, normal)), is(true));
		assertThat(result.contains(new Move(Field.c7, Field.c6, normal)), is(true));
		assertThat(result.contains(new Move(Field.d7, Field.d6, normal)), is(true));
		assertThat(result.contains(new Move(Field.e7, Field.e6, normal)), is(true));
		assertThat(result.contains(new Move(Field.f7, Field.f6, normal)), is(true));
		assertThat(result.contains(new Move(Field.g7, Field.g6, normal)), is(true));
		assertThat(result.contains(new Move(Field.h7, Field.h6, normal)), is(true));
		assertThat(result.contains(new Move(Field.b8, Field.a6)), is(true));
		assertThat(result.contains(new Move(Field.b8, Field.c6)), is(true));
		assertThat(result.contains(new Move(Field.g8, Field.f6)), is(true));
		assertThat(result.contains(new Move(Field.g8, Field.h6)), is(true));
	}

	private static Set<Move> movesAsSet(Moves moves) {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	
}
