/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b3;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c3;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d3;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g3;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h3;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.moves.Move.newMove;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.moves.Moves;

public class GameWhiteStartPosMakeAndUndoTest {

	private Game game;
	private long count;
	private long maxDepth;
	
	@Before
	public void setup() {
		game = GameFromFEN.gameFromInitialFen();
		count = 0;
	}
	
	@Test
	public void testStartPos() {
		maxDepth = 4;
		white(0);
		System.out.println("nodes:  " + count);
		assertThat(count, is(197281L));
		Moves moves = game.whiteMoves();
		
		assertThat(moves.size(), is(20));
		Set<Integer> result = movesAsSet(moves);
		assertThat(result.contains(newMove(a2, a4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(b2, b4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(c2, c4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(d2, d4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(e2, e4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(f2, f4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(g2, g4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(h2, h4, pawnDoubleStep)), is(true));
		assertThat(result.contains(newMove(a2, a3, pawn)), is(true));
		assertThat(result.contains(newMove(b2, b3, pawn)), is(true));
		assertThat(result.contains(newMove(c2, c3, pawn)), is(true));
		assertThat(result.contains(newMove(d2, d3, pawn)), is(true));
		assertThat(result.contains(newMove(e2, e3, pawn)), is(true));
		assertThat(result.contains(newMove(f2, f3, pawn)), is(true));
		assertThat(result.contains(newMove(g2, g3, pawn)), is(true));
		assertThat(result.contains(newMove(h2, h3, pawn)), is(true));
		assertThat(result.contains(newMove(b1, a3, normal)), is(true));
		assertThat(result.contains(newMove(b1, c3, normal)), is(true));
		assertThat(result.contains(newMove(g1, f3, normal)), is(true));
		assertThat(result.contains(newMove(g1, h3, normal)), is(true));
	}

	private void white(int depth) {
		Moves moves = game.whiteMoves();
		if (moves == null)
			return;
		
		if (depth == maxDepth) {
			count++;
			return;
		}
		
		int enPassant = game.enPassant();
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
		
			if (depth == 0)
				System.out.print(format("%s:  ", size - i));
		
			int move = moves.move(i);
			makeMove.makeMove(move);
			black(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
			
			if (depth == 0)
				System.out.println("     " + count);
		}
	}
	
	private void black(int depth) {
		Moves moves = game.blackMoves();
		if (moves == null)
			return;

		if (depth == maxDepth) {
			count++;
			return;
		}
		
		int enPassant = game.enPassant();
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			
			if (depth == 1)
				System.out.print(format("%s ", size - i));
			
			int move = moves.move(i);
			makeMove.makeMove(move);
			white(depth + 1);
			makeMove.undoMove();
			assert enPassant == game.enPassant();
		}
	}

	private Set<Integer> movesAsSet(Moves moves) {
		Set<Integer> result = new HashSet<>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	
}
