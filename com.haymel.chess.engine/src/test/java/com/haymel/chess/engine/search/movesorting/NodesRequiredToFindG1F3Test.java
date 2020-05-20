/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.movesorting;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.search.SearchInfoImpl.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.SearchAlphaBeta;

public class NodesRequiredToFindG1F3Test {

	@Test
	public void test() {
		String fen = "6nk/2b2ppp/3q4/8/8/7P/P4PB1/Q5NK w - - 1 1";
		Game game = new GameFromFEN(fen).value();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo, new PVMoveIteratorCreator(game));
		BestMove bestMove = search.execute(3);
		assertThat(asString(bestMove.move()), is("g1f3"));
		System.out.println(bestMove.nodes().count());
		assertThat(bestMove.nodes().count(), lessThan(710L));
	}
	
	private static String asString(Move move) {
		return fieldAsString(move.from()) + fieldAsString(move.to());
	}

}
