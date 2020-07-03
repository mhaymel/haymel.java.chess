/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.fieldAsString;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.white.WhiteMakeMove;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaTest {

	@Test
	public void testWhiteStarts1() {
		String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
		Game game = new GameFromFEN(fen).value();
		SearchAlphaBeta search = new SearchAlphaBeta(game);
		BestMove move = search.execute(6);
		
		System.out.println(move.nodes().count());
		System.out.println("play: " + asString(move.move()));
		WhiteMakeMove.makeMove(game, move.move());
	}
	
	private static String asString(int move) {
		return fieldAsString(Move.from(move)) + fieldAsString(Move.to(move));
	}
	
}
