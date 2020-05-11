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
		
		whiteRook = new Piece(WhiteRook, a1);
		game.addWhite(whiteRook);
		game.place(whiteRook);

		blackRook = new Piece(BlackRook, a8);
		game.addBlack(blackRook);
		game.place(blackRook);
		game.activeColorBlack();
		game.assertVerify();
		
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);
		game.fullMoveNumber(5);

		Move a8a1 = new Move(a8, a1, capture, whiteRook);
		
		moveMaker.makeMove(a8a1);
		game.assertVerify();
		assertThat(game.fullMoveNumber(), is(6));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(game.fullMoveNumber(), is(5));
	}

}
