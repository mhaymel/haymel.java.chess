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
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.GameStartPos;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.BlackMoves;
import com.haymel.chess.engine.moves.white.WhiteMoves;

public class GameWhiteStartPosMakeAndUndoTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new GameStartPos().startPos();
	}
	
	@Test
	public void testStartPos() {
		white(2);
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(game.board(), moves);
		whiteMoves.generate(game.whitePieces(), game.enPassant());
		
		assertThat(moves.size(), is(20));
		Set<Move> result = movesAsSet(moves);
		assertThat(result.contains(new Move(a2, a4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(b2, b4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(c2, c4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(d2, d4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(e2, e4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(f2, f4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(g2, g4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(h2, h4, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(a2, a3, pawn)), is(true));
		assertThat(result.contains(new Move(b2, b3, pawn)), is(true));
		assertThat(result.contains(new Move(c2, c3, pawn)), is(true));
		assertThat(result.contains(new Move(d2, d3, pawn)), is(true));
		assertThat(result.contains(new Move(e2, e3, pawn)), is(true));
		assertThat(result.contains(new Move(f2, f3, pawn)), is(true));
		assertThat(result.contains(new Move(g2, g3, pawn)), is(true));
		assertThat(result.contains(new Move(h2, h3, pawn)), is(true));
		assertThat(result.contains(new Move(b1, a3)), is(true));
		assertThat(result.contains(new Move(b1, c3)), is(true));
		assertThat(result.contains(new Move(g1, f3)), is(true));
		assertThat(result.contains(new Move(g1, h3)), is(true));
	}

	private void white(int depth) {
		if (depth == 0)
			return;
		
		Board board = game.board();
		PieceList pieces = game.whitePieces();
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(board, moves);
		whiteMoves.generate(pieces, removed);
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			makeMove.makeMove(move);
			black(depth - 1);
			makeMove.undoMove();
		}
	}
	
	private void black(int depth) {
		if (depth == 0)
			return;

		Board board = game.board();
		PieceList pieces = game.blackPieces();
		Moves moves = new Moves();
		BlackMoves blackMoves = new BlackMoves(board, moves);
		blackMoves.generate(pieces, game.enPassant());
		
		MakeMove makeMove = new MakeMove(game);
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			makeMove.makeMove(move);
			white(depth - 1);
			makeMove.undoMove();
		}
	}

	private Set<Move> movesAsSet(Moves moves) {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	
}
