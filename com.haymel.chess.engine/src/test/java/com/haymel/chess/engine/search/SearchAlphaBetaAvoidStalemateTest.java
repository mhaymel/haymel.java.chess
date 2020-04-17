/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaAvoidStalemateTest {

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "3k4/8/3KP3/8/8/8/8/8 w - - 5 6";
		Game game = new GameFromFEN(fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(6);
		assertThat(asString(bestMove.move()), not("e6e7"));
	}
	
	
//	@Test
//	public void blackMateInOneHasAVariantOfLengthOne1() {
//		String fen = "1k3K2/1r6/5n2/8/8/8/8/8 b - - 25 73";
//		new GameFromFEN(game, fen).execute();
//		
//		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
//		BestMove bestMove = search.execute(12);
//
//		assertThat(asString(bestMove.move()), is("d1d8"));
//		assertThat(bestMove.value(), is(Integer.MAX_VALUE - 1));
//		assertThat(bestMove.variant().size(), is(1));
//	}

//	@Test
//	public void blackMateInOneHasAVariantOfLengthOne2() {
//		String fen = "1k3K2/6r1/8/8/4n3/8/8/8 b - - 25 73";
//		new GameFromFEN(game, fen).execute();
//		
//		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
//		BestMove bestMove = search.execute(5);
//		
//		assertThat(asString(bestMove.move()), is("d1d8"));
//		assertThat(bestMove.value(), is(Integer.MAX_VALUE - 1));
//		assertThat(bestMove.variant().size(), is(1));
//	}

//	@Test
//	public void whiteMateInOne() {
//		String fen = "1k1K4/1r6/5n2/8/8/8/8/8 b - - 25 73";
//		new GameFromFEN(game, fen).execute();
//		
//		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
//		BestMove bestMove = search.execute(2);
//
//		assertThat(asString(bestMove.move()), is("b7d7"));
//		assertThat(bestMove.value(), is(MIN_VALUE + 1));
//		assertThat(bestMove.variant().size(), is(1));
//	}
	
	private static String asString(Move move) {
		return fieldAsString(move.from()) + fieldAsString(move.to());
	}
	
}
