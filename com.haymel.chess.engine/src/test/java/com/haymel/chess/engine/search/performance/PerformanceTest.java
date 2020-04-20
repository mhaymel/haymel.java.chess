/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	17.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.performance;

import static com.haymel.chess.engine.fen.GameFromFEN.initalFen;
import static com.haymel.chess.engine.search.SearchInfo.noopSearchInfo;
import static com.haymel.chess.engine.search.SearchInfoImpl.nodeStatisticsConsumer;
import static com.haymel.chess.engine.search.performance.Assertion.assumeNoAssertion;
import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.NodesCalculator;
import com.haymel.chess.engine.search.SearchInfoImpl;
import com.haymel.chess.engine.search.execution.IterativeSearch;

@RunWith(Parameterized.class)
public class PerformanceTest {

	private final static int warmUpSearchTime = 2_000_000;
	private static final String fenKiwipete = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
	public static final SearchInfoImpl searchInfo = new SearchInfoImpl(x -> {}, x -> {}, x -> {}, nodeStatisticsConsumer());

	private final String fen; 
	private final long expectedNodesPerSecond;
	
	@BeforeClass
	public static void warmUp() {
		assumeNoAssertion();
		
		out.println("warmup");
		
		warmUp(initalFen);
		warmUp(fenKiwipete);
		warmUp("r1bk3r/p2p1pNp/n2B1n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w - - 1 1");
		warmUp("r3k2r/ppp2ppp/3p4/4p3/4P3/PBPP4/2P3q1/R1B1K1n1 b - - 1 1");
		
		out.println("warmup finished");
		out.println();
	}
	
	private static void warmUp(String fen) {
		out.println(format("fen: %s", fen));
		
		Game game = new GameFromFEN(fen).execute();
		IterativeSearch search = new IterativeSearch(game, noopSearchInfo, new NodesCalculator(0.1));
		search.execute(warmUpSearchTime, warmUpSearchTime);
	}

	@Parameters(name = "{index}: value={0}")
    public static Iterable<Object[]> data() {
           return Arrays.asList(new Object[][] {
                  { "r1bq1b1r/ppp4p/2n3pn/4p3/3kN3/1Q5P/PPPP1PP1/R1B1K2R w - - 1 1", 	1185_000 },
                  { "r3k2r/ppp2ppp/3p4/4p3/4P3/PBPP4/2P3q1/R1B1K1n1 b - - 1 1", 		1780_000 },
                  { "r1bk2Br/1ppp1Qpp/8/p3P3/4P3/8/P5PP/qN5K w - - 0 1", 				1720_000 },
                  { "r1bk3r/p2p1pNp/n2B1n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w - - 1 1", 		1051_000 },
                  { "1n2kb1r/p4ppp/4q3/4p1B1/4P3/8/PPP2PPP/2QR4 w - - 1 1",				1508_000 },
                  { "r2kqR2/pbp1b3/1p4Q1/3pP1B1/3P4/8/PPP4P/6K1 w - - 1 1",				2174_000 },
                  { "8/p4pkp/8/3B1b2/P2b1ppP/2N1r1n1/1PP3PR/R4QK1 b - - 1 1",			1380_000 },
                  { "1Q1RKR2/1P2n2P/1r2k2r/4P3/4P3/8/8/8 w - - 1 1",					2846_000 },
                  { initalFen, 															1678_000 },
                  { fenKiwipete, 														1283_000 },
                  { "4k3/8/8/8/8/8/4P3/4K3 w - - 0 1",									3938_000 },
           	});
    }
	
    public PerformanceTest(String fen, long expectedNodesPerSecond) {
    	this.fen = nonNull(fen, "fen");	
    	this.expectedNodesPerSecond = greaterThanZero(expectedNodesPerSecond, "expectedNodesPerSecond");
    }
    
	@Test
	public void test() {
		assumeNoAssertion();

		out.println(fen);
		Game game = new GameFromFEN(fen).execute();
		IterativeSearch search = new IterativeSearch(game);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		BestMove bestMove = search.execute(warmUpSearchTime, warmUpSearchTime);
		stopWatch.stop();
		long elapsed = stopWatch.getTime(SECONDS);
		long nodesPerSecond = bestMove.nodes().count() / elapsed;
		out.println(format("nps: %sk", nodesPerSecond/1000));
		out.println();
		assertThat(nodesPerSecond, greaterThan(expectedNodesPerSecond));
	}

}
