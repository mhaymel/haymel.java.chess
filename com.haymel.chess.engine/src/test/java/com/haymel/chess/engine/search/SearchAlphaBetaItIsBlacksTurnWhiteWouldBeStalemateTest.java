/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaItIsBlacksTurnWhiteWouldBeStalemateTest {

	private static final String fen = "4k3/8/8/8/6n1/4n3/4n3/7K b - - 0 73";

	@Test
	public void test() {
		test(0);
		test(1);
		test(2);
		test(3);
		test(4);
		test(5);
		test(6);
		test(7);
	}

	private void test(int depth) {
		Game game = new Game();
		new GameFromFEN(game, fen).execute();
		
		SearchInfo info = new SearchInfo(currentMoveConsumer(), bestMoveConsumer(), depthConsumer(), nodeStatisticsConsumer());
		SearchAlphaBeta search = new SearchAlphaBeta(game, info, new NodesCalculator());
		BestMove bestMove = search.execute(depth);

		assertThat(bestMove.variant(), is(notNullValue()));
		
		new MakeMove(game).makeMove(bestMove.move());
		
		bestMove = search.execute(0);
		assertThat(bestMove.variant(), is(notNullValue()));
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
		System.out.println(format("%s: %s/%s", asString(move.move()), move.moveNumber(), move.numberOfPossibleMoves()));
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
