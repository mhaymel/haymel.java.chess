/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhiteCaptureMoveTest3 {

	private Game game;
	private MakeMove moveMaker;
	private Piece whiteKing;
	private Piece blackRook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		whiteKing = new Piece(WhiteKing);
		whiteKing.field(e1);
		whiteKing.setMoved(false);
		game.addWhite(whiteKing);
		game.place(whiteKing);

		blackRook = new Piece(BlackRook);
		blackRook.field(e2);
		blackRook.setMoved(true);
		game.addBlack(blackRook);
		game.place(blackRook);
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);

		Move e1a2 = new Move(e1, e2, capture, blackRook);
		
		moveMaker.makeMove(e1a2);
		game.assertVerify();
		assertThat(whiteKing.moved(), is(true));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(whiteKing.moved(), is(false));
		assertThat(blackRook.moved(), is(true));
	}

}
