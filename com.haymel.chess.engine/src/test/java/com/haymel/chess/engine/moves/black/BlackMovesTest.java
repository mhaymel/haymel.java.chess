/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.GameStartPos;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class BlackMovesTest {

	private Moves moves;
	private Game game;
	
	@Before
	public void setup() {
		moves = new Moves();
		game = new GameStartPos().startPos();
	}
	
	@Test
	public void testStartPos() {
		Piece[] pieces = game.pieces();
		PieceList blackPieces = game.blackPieces();
		BlackMoves blackMoves = new BlackMoves(pieces);
		blackMoves.generate(blackPieces, removed, moves);
		
		assertThat(moves.size(), is(20));
		Set<Move> result = movesAsSet();
		assertThat(result.contains(new Move(Field.a7, Field.a5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.b7, Field.b5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.c7, Field.c5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.d7, Field.d5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.e7, Field.e5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.f7, Field.f5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.g7, Field.g5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.h7, Field.h5, pawnDoubleStep)), is(true));
		assertThat(result.contains(new Move(Field.a7, Field.a6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.b7, Field.b6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.c7, Field.c6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.d7, Field.d6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.e7, Field.e6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.f7, Field.f6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.g7, Field.g6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.h7, Field.h6, pawn)), is(true));
		assertThat(result.contains(new Move(Field.b8, Field.a6)), is(true));
		assertThat(result.contains(new Move(Field.b8, Field.c6)), is(true));
		assertThat(result.contains(new Move(Field.g8, Field.f6)), is(true));
		assertThat(result.contains(new Move(Field.g8, Field.h6)), is(true));
	}

	private Set<Move> movesAsSet() {
		Set<Move> result = new HashSet<Move>(moves.size());

		int size = moves.size();
		for(int i = 0; i < size; i++) 
			result.add(moves.move(i));
		
		return result;
	}
	
}
