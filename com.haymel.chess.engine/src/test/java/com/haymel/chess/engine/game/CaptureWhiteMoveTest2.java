/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class CaptureWhiteMoveTest2 {

	private Game game;
	private MoveMaker moveMaker;

	private Piece whiteRook;
	private Piece blackRook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MoveMaker(game);
		
		whiteRook = new Piece(WhiteRook);
		whiteRook.field(a1);
		whiteRook.setMoved(false);
		game.addWhite(whiteRook);
		game.place(whiteRook);

		blackRook = new Piece(BlackRook);
		blackRook.field(a8);
		blackRook.setMoved(false);
		game.addBlack(blackRook);
		game.place(blackRook);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);

		Move a1a8 = new Move(a1, a8, capture, blackRook);
		
		moveMaker.makeMove(a1a8);
		game.assertVerify();
		assertThat(whiteRook.moved(), is(true));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(whiteRook.moved(), is(false));
		assertThat(blackRook.moved(), is(false));
	}

}
