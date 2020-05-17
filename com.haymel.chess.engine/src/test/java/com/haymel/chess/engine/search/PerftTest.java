/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;


/**
 * @author Markus.Heumel
 *
 * https://www.chessprogramming.org/Perft_Results
 * 
 */
public class PerftTest {

	@Test
	public void initialPosition() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		perft(fen, 0, 1);
		perft(fen, 1, 20);
		perft(fen, 2, 400);
		perft(fen, 3, 8902);
		perft(fen, 4, 197281);
//		perft(fen, 5, 4865609);
	}
	
	@Test
	public void kiwipete () {
		String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
		perft(fen, 1, 48);
		perft(fen, 2, 2039);
		perft(fen, 3, 97862);
//		perft(fen, 4, 4085603);
//		perft(fen, 5, 193690690);
//		perft(fen, 6, 8031647685L);
	}
	
	@Test
	public void position3 () {
		String fen = "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1";
		perft(fen, 1, 14);
		perft(fen, 2, 191);
		perft(fen, 3, 2812);
		perft(fen, 4, 43238);
		perft(fen, 5, 674624);
//		perft(fen, 6, 11030083);
//		perft(fen, 7, 178633661);
//		perft(fen, 8, 3009794393L);
	}

	@Test
	public void position4 () {
		String fen = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
		perft(fen, 1, 6);
		perft(fen, 2, 264);
		perft(fen, 3, 9467);
		perft(fen, 4, 422333);
//		perft(fen, 5, 15833292);
//		perft(fen, 6, 706045033L);
	}

	@Test
	public void position4Mirrored () {
		String fen = "r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1";
		perft(fen, 1, 6);
		perft(fen, 2, 264);
		perft(fen, 3, 9467);
		perft(fen, 4, 422333);
//		perft(fen, 5, 15833292);
//		perft(fen, 6, 706045033L);
	}

	@Test
	public void position5 () {
		String fen = "rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8";
		perft(fen, 1, 44);
		perft(fen, 2, 1486);
		perft(fen, 3, 62379);
//		perft(fen, 4, 2103487);
//		perft(fen, 5, 89941194);
	}
	
	@Test
	public void position6 () {
		String fen = "r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10";
		perft(fen, 1, 46);
		perft(fen, 2, 2079);
		perft(fen, 3, 89890);
//		perft(fen, 4, 3894594);
//		perft(fen, 5, 164075551);
//		perft(fen, 6, 6923051137L);
	}
	
	private void perft(String fen, int depth, long nodesExpected) {
		Game game = new GameFromFEN(fen).value();
		Perft perft = new Perft(game);
		perft.execute(depth);
		assertThat(perft.nodes(), is(nodesExpected));
	}
	
}
