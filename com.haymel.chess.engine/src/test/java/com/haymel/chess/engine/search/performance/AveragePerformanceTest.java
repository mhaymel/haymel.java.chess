/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	18.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.performance;

import static com.haymel.chess.engine.fen.GameFromFEN.initalFen;
import static com.haymel.chess.engine.search.SearchInfoImpl.nodeStatisticsConsumer;
import static com.haymel.chess.engine.search.performance.Assertion.assumeNoAssertion;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.SearchInfoImpl;
import com.haymel.chess.engine.search.execution.IterativeSearch;

public class AveragePerformanceTest {

	private final static int searchTime = 2_000_000;
	private static final String fenKiwipete = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
	public static final SearchInfoImpl searchInfo = new SearchInfoImpl(x -> {}, x -> {}, x -> {}, nodeStatisticsConsumer());

	private static final String[] fens = {
		"r1bq1b1r/ppp4p/2n3pn/4p3/3kN3/1Q5P/PPPP1PP1/R1B1K2R w - - 1 1",
		"r3k2r/ppp2ppp/3p4/4p3/4P3/PBPP4/2P3q1/R1B1K1n1 b - - 1 1", 	
		"r1bk2Br/1ppp1Qpp/8/p3P3/4P3/8/P5PP/qN5K w - - 0 1", 			
		"r1bk3r/p2p1pNp/n2B1n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w - - 1 1", 
		"1n2kb1r/p4ppp/4q3/4p1B1/4P3/8/PPP2PPP/2QR4 w - - 1 1",		
		"r2kqR2/pbp1b3/1p4Q1/3pP1B1/3P4/8/PPP4P/6K1 w - - 1 1",		
		"8/p4pkp/8/3B1b2/P2b1ppP/2N1r1n1/1PP3PR/R4QK1 b - - 1 1",		
		"1Q1RKR2/1P2n2P/1r2k2r/4P3/4P3/8/8/8 w - - 1 1",				
		initalFen, 													
		fenKiwipete, 													
		"4k3/8/8/8/8/8/4P3/4K3 w - - 0 1",							
	};

	@Test
	public void test() {
		assumeNoAssertion();

		warmUp();
		runTest();
	}

	private long searchFens() {
		long count = 0;

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
	
		for(int i = 0; i < fens.length; i++)
			count += test(fens[i]);

		long elapsed = stopWatch.getTime(SECONDS);
		long nodesPerSecond = count / elapsed;
		out.println(format("nps: %s", nodesPerSecond/1000));
		
		return nodesPerSecond;
	}

	private void runTest() {
		out.println("start test");
		long nodesPerSecond = searchFens();
		
		out.println("finished test");
		assertThat(nodesPerSecond, greaterThan(1870_000L));
	}

	private void warmUp() {
		out.println("start warmup");
		searchFens();
		out.println("finished warmup");
		out.println();
	}
	
	private long test(String fen) {
		out.println(fen);
		Game game = new GameFromFEN(fen).execute();
		IterativeSearch search = new IterativeSearch(game);
		BestMove bestMove = search.execute(searchTime, searchTime);
		return bestMove.nodes().count();
	}

}
