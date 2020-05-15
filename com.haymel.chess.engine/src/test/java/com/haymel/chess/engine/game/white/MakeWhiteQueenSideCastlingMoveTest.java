/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public class MakeWhiteQueenSideCastlingMoveTest {

	private Game game;
	private MakeMove moveMaker;
	private Piece king;
	private Piece rook;
	
	@Before
	public void setup() {
		game = new Game();
		moveMaker = new MakeMove(game);

		king = new Piece(WhiteKing, e1);
		game.addWhite(king);
		game.place(king);
		
		rook = new Piece(WhiteRook, a1);
		game.addWhite(rook);
		game.place(rook);
		game.castlingRight().white().enableQueenside();
		game.assertVerify();
	}
	
	@Test
	public void makeAndUndo() {
		Move e1c1 = new Move(e1, c1, queensideCastling);
		
		moveMaker.makeMove(e1c1);
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(king.field(), is(c1));
		assertThat(game.piece(c1), is(king));
		assertThat(game.piece(e1) == null, is(true));

		assertThat(rook.field(), is(d1));
		assertThat(game.piece(d1), is(rook));
		assertThat(game.piece(a1) == null, is(true));
		
		assertThat(game.halfMoveClock(), is(1));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(king.field(), is(e1));
		assertThat(game.piece(e1), is(king));
		assertThat(game.piece(d1) == null, is(true));
		assertThat(game.piece(c1) == null, is(true));
		assertThat(game.piece(a1).type() == WhiteRook, is(true));
		assertThat(game.piece(a1), is(rook));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(1));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Piece blackEnpassantPawn = new Piece(BlackPawn, a5);
		game.addBlack(blackEnpassantPawn);
		game.place(blackEnpassantPawn);
		game.activeColorBlack();
		game.enPassant(a6);
		game.activeColorWhite();

		Move e1c1 = new Move(e1, c1, queensideCastling);
		moveMaker.makeMove(e1c1);
		assertThat(game.enPassant(), is(removed));
		
		moveMaker.undoMove();
		assertThat(game.enPassant(), is(a6));
	}
	
}
