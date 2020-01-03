/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeBlackCaptureMoveTest2 {

	private Game game;
	private MakeMove moveMaker;

	private Piece whiteRook;
	private Piece blackRook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
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
		game.activeColorBlack();
		game.assertVerify();
		
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);

		Move a8a1 = new Move(a8, a1, capture, whiteRook);
		
		moveMaker.makeMove(a8a1);
		game.assertVerify();
		assertThat(blackRook.moved(), is(true));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(whiteRook.moved(), is(false));
		assertThat(blackRook.moved(), is(false));
	}

}
