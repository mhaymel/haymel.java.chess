/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.SearchAlphaBeta.MIN_VALUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaAvoidStalemateTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
		new StartposCreator(game).execute();
	}

	@Test
	public void whiteSetsBlackStalemate() {
		String fen = "3k4/8/3KP3/8/8/8/8/8 w - - 5 6";
		new GameFromFEN(game, fen).execute();
		
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		BestMove bestMove = search.execute(6);
		assertThat(asString(bestMove.move()), not("e6e7"));
	}
	
//	
//	@Test
//	public void blackMateInOneHasAVariantOfLengthOne1() {
//		String fen = "1k3K2/1r6/5n2/8/8/8/8/8 b - - 25 73";
//		new GameFromFEN(game, fen).execute();
//		
//		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
//		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
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
//		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
//		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
//		BestMove bestMove = search.execute(5);
//		
//		assertThat(asString(bestMove.move()), is("d1d8"));
//		assertThat(bestMove.value(), is(Integer.MAX_VALUE - 1));
//		assertThat(bestMove.variant().size(), is(1));
//	}

	@Test
	public void whiteMateInOne() {
		String fen = "1k1K4/1r6/5n2/8/8/8/8/8 b - - 25 73";
		new GameFromFEN(game, fen).execute();
		
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		BestMove bestMove = search.execute(2);

		assertThat(asString(bestMove.move()), is("b7d7"));
		assertThat(bestMove.value(), is(MIN_VALUE + 1));
		assertThat(bestMove.variant().size(), is(1));
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
