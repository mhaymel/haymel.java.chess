/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeBlackCaptureMoveTest3 {

	private Game game;
	private MakeMove moveMaker;
	private Piece blackKing;
	private Piece whiteRook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);
		
		blackKing = new Piece(BlackKing, e8);
		blackKing.setMoved(false);
		game.addBlack(blackKing);
		game.place(blackKing);

		whiteRook = new Piece(WhiteRook, e7);
		whiteRook.setMoved(true);
		game.addWhite(whiteRook);
		game.place(whiteRook);
		game.assertVerify();
		
		game.activeColorBlack();
	}
	
	@Test
	public void makeAndUndo() {
		game.assertVerify();
		game.halfMoveClock(13);
		game.fullMoveNumber(5);

		Move e8e7 = new Move(e8, e7, normal, whiteRook);
		
		moveMaker.makeMove(e8e7);
		game.assertVerify();
		assertThat(blackKing.moved(), is(true));
		assertThat(game.fullMoveNumber(), is(6));
		
		moveMaker.undoMove();
		game.assertVerify();
		assertThat(blackKing.moved(), is(false));
		assertThat(whiteRook.moved(), is(true));
		assertThat(game.fullMoveNumber(), is(5));
	}

}
