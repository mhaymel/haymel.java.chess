/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.07.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMoveFromString;
import com.haymel.chess.engine.game.white.WhiteMakeMove;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaRepetitionTest {

	@Test
	public void moveE2C3WouldLeadToRepetition() {
		
		Game game = GameFromFEN.gameFromInitialFen();
		MakeMoveFromString make = new MakeMoveFromString(game);
		
		String[] moves = 
			("e2e4 e7e5 b1c3 b8c6 g1f3 f8c5 f3e5 c5f2 e1f2 c6e5 d2d4 e5g6 f2g1 d7d6 d1f3 c8e6 f1b5 c7c6 b5d3 d8b6 f3e3 " + 
			"g8f6 h2h3 e8c8 a1b1 h7h5 c1d2 h8e8 e3f2 d6d5 e4e5 f6h7 d3e2 e8h8 e2h5 h7f8 h5f3 d8e8 c3a4 b6c7 d2b4 g6h4 " +
			"f3g4 e6g4 h3g4 h4g6 h1h8 g6h8 b4d6 c7a5 f2f5 f8e6 a4c3 a5d8 c3e2 d8a5").split(" ");

		for (String move : moves)
			make.move(move);

		assertThat(game.repetition(), is(false));
		
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove move = search.execute(1);
		
		System.out.println("nodes: " + move.nodes().count());
		System.out.println("play: " + asString(move.move()));
		System.out.println("value: " + move.value());
		
		WhiteMakeMove.makeMove(game, move.move());
	}
	
	private static String asString(int move) {
		return fieldAsString(Move.from(move)) + fieldAsString(Move.to(move));
	}
	
}
