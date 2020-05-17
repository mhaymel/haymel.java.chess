/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	17.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.performance;

import static com.haymel.chess.engine.fen.PositionFromFEN.initalFen;
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

	private final static int warmUpSearchTime = 200_000;
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
		
		out.println("warmup finished");
		out.println();
	}
	
	private static void warmUp(String fen) {
		out.println(format("fen: %s", fen));
		
		Game game = new GameFromFEN(fen).value();
		IterativeSearch search = new IterativeSearch(game, noopSearchInfo, new NodesCalculator(0.1));
		search.execute(warmUpSearchTime, warmUpSearchTime);
	}

	@Parameters(name = "{index}: value={0}")
    public static Iterable<Object[]> data() {
           return Arrays.asList(new Object[][] {
                  { initalFen, 															2538_000 },
                  { fenKiwipete, 														1316_000 },
                  { "4k3/8/8/8/8/8/4P3/4K3 w - - 0 1",									4215_000 },
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
		Game game = new GameFromFEN(fen).value();
		IterativeSearch search = new IterativeSearch(game);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		BestMove bestMove = search.execute(warmUpSearchTime, warmUpSearchTime);
		stopWatch.stop();
		long elapsed = stopWatch.getTime(SECONDS);
		out.println(format("nodes: %s", bestMove.nodes().count()));
		long nodesPerSecond = bestMove.nodes().count() / elapsed;
		out.println(format("nps: %sk", nodesPerSecond/1000));
		out.println();
		assertThat(nodesPerSecond, greaterThan(expectedNodesPerSecond));
	}

}
