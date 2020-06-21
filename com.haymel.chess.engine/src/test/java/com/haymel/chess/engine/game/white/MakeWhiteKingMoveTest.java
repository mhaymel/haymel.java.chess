/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.TestHelper.find;
import static com.haymel.chess.engine.game.TestHelper.fromFen;
import static com.haymel.chess.engine.game.TestHelper.makeMove;
import static com.haymel.chess.engine.game.TestHelper.undoMove;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeWhiteKingMoveTest {

	@Test
	public void makeAndUndo() {
		Game game = fromFen("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 13 10");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.to(move)).type(), is(WhiteKing));
		assertThat(game.piece(e1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(14));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.from(move)).type(), is(WhiteKing));
		assertThat(game.piece(e2), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Game game = fromFen("r3k2r/8/8/2p5/8/8/8/R3K2R w KQkq c6 13 10");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.to(move)).type(), is(WhiteKing));
		assertThat(game.piece(e1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(14));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.from(move)).type(), is(WhiteKing));
		assertThat(game.piece(e2), is(nullValue()));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(c6));
	}
	
}
