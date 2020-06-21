/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b6;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d6;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e6;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f6;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g6;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h6;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.moves.Move.newMove;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Moves;

public class BlackMovesTest {

	@Test
	public void testStartPos() {
		Game game = GameFromFEN.gameFromInitialFen();
		Moves moves = game.blackMoves();

		assertThat(moves.size(), is(20));
		Set<Integer> result = movesAsSet(moves);
		assertThat(result.contains(newMove(a7, a5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(b7, b5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(c7, c5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(d7, d5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(e7, e5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(f7, f5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(g7, g5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(h7, h5, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(a7, a6, pawn)), is(true));
		assertThat(result.contains(newMove(b7, b6, pawn)), is(true));
		assertThat(result.contains(newMove(c7, c6, pawn)), is(true));
		assertThat(result.contains(newMove(d7, d6, pawn)), is(true));
		assertThat(result.contains(newMove(e7, e6, pawn)), is(true));
		assertThat(result.contains(newMove(f7, f6, pawn)), is(true));
		assertThat(result.contains(newMove(g7, g6, pawn)), is(true));
		assertThat(result.contains(newMove(h7, h6, pawn)), is(true));
		assertThat(result.contains(newMove(b8, a6, normal)), is(true));
		assertThat(result.contains(newMove(b8, c6, normal)), is(true));
		assertThat(result.contains(newMove(g8, f6, normal)), is(true));
		assertThat(result.contains(newMove(g8, h6, normal)), is(true));
	}

	private static Set<Integer> movesAsSet(Moves moves) {
		Set<Integer> result = new HashSet<>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	
}
