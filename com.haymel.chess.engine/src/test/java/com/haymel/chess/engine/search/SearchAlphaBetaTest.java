/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	08.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.search.SearchAlphaBeta.MAX_VALUE;
import static com.haymel.chess.engine.search.SearchAlphaBeta.MIN_VALUE;
import static com.haymel.chess.engine.search.SearchInfo.sysoutSearchInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;

public class SearchAlphaBetaTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
		new StartposCreator(game).execute();
	}
	
	@Test
	public void blackMateInOneHasAVariantOfLengthOne() {
		String fen = "6k1/5ppp/8/8/8/8/8/3R2K1 w - - 0 1";
		new GameFromFEN(game, fen).execute();
		
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(2);

		assertThat(asString(bestMove.move()), is("d1d8"));
		assertThat(bestMove.value(), is(MAX_VALUE - 1));
		assertThat(bestMove.variant().size(), is(1));
	}

	@Test
	public void whiteMateInOneHasAVariantOfLengthOne() {
		String fen = "r6r/1p2k1pp/8/p4p2/1bb5/PP4N1/K4PPP/Q2q3R b - - 0 20";
		new GameFromFEN(game, fen).execute();
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		BestMove bestMove = search.execute(2);
		
		assertThat(asString(bestMove.move()), is("d1b3"));
		assertThat(bestMove.value(), is(MIN_VALUE + 1));
		assertThat(bestMove.variant().size(), is(1));
		
		bestMove.value();
	}
	
	@Test
	public void testWhiteStarts1() {
		SearchAlphaBeta search = new SearchAlphaBeta(game, sysoutSearchInfo);
		Move move = search.execute(4).move();
		System.out.println("play: " + asString(move));
		new MakeMove(game).makeMove(move);
	}
	
//	@Test
//	public void test1() {
//		String[] moves = "e2e4 e7e6 d2d4 d8f6 g1f3".split(" ");
//		for (String move : moves)
//			uciMoveMaker.move(move);
//		
//		SearchAlphaBeta search = new SearchAlphaBeta(game, currentMoveConsumer());
//		Move move = search.execute(1).move();
//		String moveAsString = move.from().toString()+ move.to().toString();
//		System.out.println(moveAsString);
//	}
	
	private static String asString(Move move) {
		return fieldAsString(move.from()) + fieldAsString(move.to());
	}
	
}
