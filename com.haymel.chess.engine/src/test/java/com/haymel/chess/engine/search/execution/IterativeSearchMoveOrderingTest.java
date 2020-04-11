///***************************************************
// * (c) Markus Heumel
// *
// * @date: 	02.04.2020
// * @author: Markus.Heumel
// *
// */
//package com.haymel.chess.engine.search.execution;
//
//import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfoNoCurrentMoveNoNodeStatistics;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import org.junit.Test;
//
//import com.haymel.chess.engine.fen.GameFromFEN;
//import com.haymel.chess.engine.game.Game;
//import com.haymel.chess.engine.moves.Move;
//import com.haymel.chess.engine.search.result.Result;
//
//public class IterativeSearchMoveOrderingTest {
//
//	private static final String fen = "rnbq1bnr/4kpp1/2p1p2p/1p1P4/1P1P1B2/PBN2N2/5PPP/R2Q1RK1 b - - 0 13 ";
//	
//	@Test
//	public void test() {
//		Game game = new Game();
//		new GameFromFEN(game, fen).execute();
//		
//		IterativeSearch search = new IterativeSearch(game, sysoutSearchInfoNoCurrentMoveNoNodeStatistics);
//		Result result = search.execute(10000_000, 10000_000);
//		
//		assertThat(result.value(), is(0));
////		assertThat(result.moves().length, is(1));
////		assertThat(asString(result.moves()[0]), is("d6e6"));
//	}
//
//	private static String asString(Move move) {
//		return move.from().toString() + move.to().toString();
//	}
//	
//}
