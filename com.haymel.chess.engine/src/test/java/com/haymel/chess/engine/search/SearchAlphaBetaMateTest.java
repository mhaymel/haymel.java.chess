/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchAlphaBeta.blackMate;
import static com.haymel.chess.engine.search.SearchAlphaBeta.whiteMate;
import static com.haymel.util.Require.nonEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

@RunWith(Parameterized.class)
public class SearchAlphaBetaMateTest {

	private final int depth;
	private final String pv;
	private final int value;	
	private Game game;
	
	@Parameters(name = "{index}: value={0}")
    public static Iterable<Object[]> data() {
           return Arrays.asList(new Object[][] {
                  { "r1bq1b1r/ppp4p/2n3pn/4p3/3kN3/1Q5P/PPPP1PP1/R1B1K2R w - - 1 1", 2, "b3d3", blackMate(1) },
                  { "r3k2r/ppp2ppp/3p4/4p3/4P3/PBPP4/2P3q1/R1B1K1n1 b - - 1 1", 2, "g2e2", whiteMate(1) },
                  { "r1bk2Br/1ppp1Qpp/8/p3P3/4P3/8/P5PP/qN5K w - - 0 1", 2, "f7f8", blackMate(1) },
                  { "r1bk3r/p2p1pNp/n2B1n2/1p1NP2P/6P1/3P4/P1P1K3/q5b1 w - - 1 1", 2, "d6e7", blackMate(1) },
                  { "1n2kb1r/p4ppp/4q3/4p1B1/4P3/8/PPP2PPP/2QR4 w - - 1 1", 2, "d1d8", blackMate(1) },
                  { "r2kqR2/pbp1b3/1p4Q1/3pP1B1/3P4/8/PPP4P/6K1 w - - 1 1", 2, "g6e8", blackMate(1) },
                  { "8/p4pkp/8/3B1b2/P2b1ppP/2N1r1n1/1PP3PR/R4QK1 b - - 1 1", 2, "e3e1", whiteMate(1) },
                  { "1Q1RKR2/1P2n2P/1r2k2r/4P3/4P3/8/8/8 w - - 1 1", 4, "b8c7 h6h7 f8f6", blackMate(3) },
           });
    }
    
 	public SearchAlphaBetaMateTest(String fen, int depth, String pv, int value) {
 		this.game = new Game();
 		this.depth = depth;
 		this.pv = nonEmpty(pv, "pv");
 		this.value = value;
		new GameFromFEN(game, fen).execute();
 		
 	}

	@Test
	public void test() {
		for(int i = depth; i < depth + 1; i++)
			search(i);
	}
	
	private void search(int depth) {
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		BestMove bestMove = search.execute(depth);
		
		assertThat(bestMove, not(nullValue()));
		assertThat(bestMove.value(), is(value));
		assertThat(asString(bestMove.variant()), is(pv));
	}
	
	private Consumer<Nodes> nodeStatisticsConsumer() {
		return (ns) -> System.out.println("nodes: " + ns.count() + ", nps: " + ns.nps());
	}

	private IntConsumer depthConsumer() {
		return (int depth) -> System.out.println("depth: " + depth);
	}

	private Consumer<BestMove> bestMoveConsumer() {
		return (bm) -> System.out.println("bestmove: " + asString(bm.variant()) + ", value: " + bm.value());
	}

	private Consumer<AnalyzedMove> currentMoveConsumer() {
		return (cm) -> currentMove(cm); 
	}
	
	private void currentMove(AnalyzedMove move) {
		System.out.println(String.format("%s: %s/%s", asString(move.move()), move.moveNumber(), move.numberOfPossibleMoves()));
	}

	private static String asString(Move move) {
		return move.from().toString() + move.to().toString();
	}
	
	private String asString(Variant variant) {
		StringBuffer sb = new StringBuffer();
		sb.append(asString(variant.move()));
		
		if (variant.moves() != null)
			for(Move move: variant.moves()) 
				sb.append(" ").append(asString(move));
		
		return sb.toString();
	}
	
}
