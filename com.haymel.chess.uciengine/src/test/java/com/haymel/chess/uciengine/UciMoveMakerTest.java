/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.uciengine.UciMoveMaker;

public class UciMoveMakerTest {

	private UciMoveMaker uciMoveMaker;
	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
		new StartposCreator(game).execute();
		uciMoveMaker = new UciMoveMaker(game);
	}
	
	@Test
	public void e2e4_d7d5() {
		uciMoveMaker.move("e2e4");
		uciMoveMaker.move("d7d5");
	}

	@Test
	public void e2e3_e7e5() {
		uciMoveMaker.move("e2e3");
		uciMoveMaker.move("e7e5");
	}
	
	@Test
	public void testPromotionQueen() {
		String[] moves = (
			"e2e3 b8c6 d1g4 d7d5 g4g5 h7h6 g5h5 g8f6 h5h4 a7a6 " +
			"h4f4 e7e5 f4h4 c8f5 f1e2 c6b4 b1a3 f5c2 h4g3 e5e4 " +
			"g3e5 f8e7 e5f5 e7d6 f5h3 e8g8 f2f4 d5d4 e3d4 f8e8 " +
			"g2g3 b4d3 e1f1 d6a3 b2a3 d8d4 e2d3 e4d3 a1b1 d4e4 " +
			"c1b2 c2b1 b2f6 e4e1 f1g2 e1d2 g2f3 d2d1 f3f2 d1c2 " +
			"g1e2 c2e2 f2g1 e2e3 g1g2 e3d2 g2g1 e8e1 h3f1 e1f1 " +
			"g1f1 d2e2 f1g1 d3d2 f6c3 d2d1q"
		).split(" ");
	
		for (String move : moves) 
			uciMoveMaker.move(move);
		
		assertThat(game.board().pieces[d1.position()].type(), is(BlackQueen));
	}
	
	@Test
	public void testPromotionRook() {
		String[] moves = (
			"e2e3 b8c6 d1g4 d7d5 g4g5 h7h6 g5h5 g8f6 h5h4 a7a6 " +
			"h4f4 e7e5 f4h4 c8f5 f1e2 c6b4 b1a3 f5c2 h4g3 e5e4 " +
			"g3e5 f8e7 e5f5 e7d6 f5h3 e8g8 f2f4 d5d4 e3d4 f8e8 " +
			"g2g3 b4d3 e1f1 d6a3 b2a3 d8d4 e2d3 e4d3 a1b1 d4e4 " +
			"c1b2 c2b1 b2f6 e4e1 f1g2 e1d2 g2f3 d2d1 f3f2 d1c2 " +
			"g1e2 c2e2 f2g1 e2e3 g1g2 e3d2 g2g1 e8e1 h3f1 e1f1 " +
			"g1f1 d2e2 f1g1 d3d2 f6c3 d2d1r"
		).split(" ");
	
		for (String move : moves) 
			uciMoveMaker.move(move);

		assertThat(game.board().pieces[d1.position()].type(), is(BlackRook));
	}

	@Test
	public void testPromotionBishop() {
		String[] moves = (
				"e2e3 b8c6 d1g4 d7d5 g4g5 h7h6 g5h5 g8f6 h5h4 a7a6 " +
				"h4f4 e7e5 f4h4 c8f5 f1e2 c6b4 b1a3 f5c2 h4g3 e5e4 " +
				"g3e5 f8e7 e5f5 e7d6 f5h3 e8g8 f2f4 d5d4 e3d4 f8e8 " +
				"g2g3 b4d3 e1f1 d6a3 b2a3 d8d4 e2d3 e4d3 a1b1 d4e4 " +
				"c1b2 c2b1 b2f6 e4e1 f1g2 e1d2 g2f3 d2d1 f3f2 d1c2 " +
				"g1e2 c2e2 f2g1 e2e3 g1g2 e3d2 g2g1 e8e1 h3f1 e1f1 " +
				"g1f1 d2e2 f1g1 d3d2 f6c3 d2d1b"
				).split(" ");
		
		for (String move : moves) 
			uciMoveMaker.move(move);

		assertThat(game.board().pieces[d1.position()].type(), is(BlackBishop));
	}
	
	@Test
	public void testPromotionKnight() {
		String[] moves = (
				"e2e3 b8c6 d1g4 d7d5 g4g5 h7h6 g5h5 g8f6 h5h4 a7a6 " +
				"h4f4 e7e5 f4h4 c8f5 f1e2 c6b4 b1a3 f5c2 h4g3 e5e4 " +
				"g3e5 f8e7 e5f5 e7d6 f5h3 e8g8 f2f4 d5d4 e3d4 f8e8 " +
				"g2g3 b4d3 e1f1 d6a3 b2a3 d8d4 e2d3 e4d3 a1b1 d4e4 " +
				"c1b2 c2b1 b2f6 e4e1 f1g2 e1d2 g2f3 d2d1 f3f2 d1c2 " +
				"g1e2 c2e2 f2g1 e2e3 g1g2 e3d2 g2g1 e8e1 h3f1 e1f1 " +
				"g1f1 d2e2 f1g1 d3d2 f6c3 d2d1n"
				).split(" ");
		
		for (String move : moves) 
			uciMoveMaker.move(move);

		assertThat(game.board().pieces[d1.position()].type(), is(BlackKnight));
	}
}
