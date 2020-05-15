/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;


/**
 * @author Markus.Heumel
 *
 * https://www.chessprogramming.org/Perft_Results
 * 
 */

@RunWith(Parameterized.class)
public class PerftKiwipeteTest {

	private static final String fenKiwipete = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";

	@Parameters(name = "{index}: value={0}")
    public static Iterable<Object[]> data() {
           return Arrays.asList(new Object[][] {
                  { fenKiwipete, 	1, 	48 },
                  { fenKiwipete, 	2, 	2039 },
                  { fenKiwipete, 	3, 	97862 },
//                  { fenKiwipete, 	5, 	193690690 },
//                  { fenKiwipete, 	6, 	8031647685L },
           	});
    }
	
	private final String fen; 
	private final int depth;
	private final long expectedNodes;
	
	public PerftKiwipeteTest(String fen, int depth, long expectedNodes) {
		this.fen = fen;
		this.depth = depth;
		this.expectedNodes = expectedNodes;
	}

	@Test
	public void test() {
		Game game = new GameFromFEN(fen).execute();
		Perft perft = new Perft(game);
		perft.execute(depth);
		assertThat(perft.nodes(), is(expectedNodes));
	}
	
}
