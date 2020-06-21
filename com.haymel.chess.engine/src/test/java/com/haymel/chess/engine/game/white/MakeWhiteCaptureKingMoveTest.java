/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.05.2020
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
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public class MakeWhiteCaptureKingMoveTest {

	@Test
	public void captureMoveDisablesKingAndQueensideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w KQkq - 0 1");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}
	
	@Test
	public void captureMoveDisablesKingsideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w Kkq - 0 1");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}
	
	@Test
	public void captureMoveDisablesQueensideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w Qkq - 0 1");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	@Test
	public void captureMoveWithAlreadyDisabledCastlingRights() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w kq - 0 1");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	@Test
	public void capturingRookAtA8DisablesBlackQueensideCastlingRight() {
		Game game = fromFen("r3k2r/K7/8/8/8/8/8/8 w kq - 0 1 ");
		int move = find("a7a8", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	@Test
	public void capturingRookAtA8WhenBlackHasNoCastlingRights() {
		Game game = fromFen("r3k2r/K7/8/8/8/8/8/8 w - - 0 1 ");
		int move = find("a7a8", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}
	
	@Test
	public void capturingRookAtH8DisablesBlackKingsideCastling() {
		Game game = fromFen("r3k2r/7K/8/8/8/8/8/8 w kq - 0 1");
		int move = find("h7h8", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	@Test
	public void capturingRookAtH8WhenBlackHasNoCastlingRights() {
		Game game = fromFen("r3k2r/7K/8/8/8/8/8/8 w - - 0 1");
		int move = find("h7h8", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}
	
	@Test
	public void fullMoveNumberAndHalfMoveClock() {
		Game game = fromFen("7k/8/8/8/8/8/4r3/4K3 w - - 13 10");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.halfMoveClock(), is(0));
		
		undoMove(game);
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.halfMoveClock(), is(13));
	}

	@Test
	public void makeAndUndo() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w KQkq - 13 10");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.to(move)).type(), is(WhiteKing));
		assertThat(game.piece(e1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.from(move)).type(), is(WhiteKing));
		assertThat(game.piece(e2).type(), is(BlackPawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
	}

	@Test
	public void enPassantIsSetCorrectly() {
		Game game = fromFen("r3k2r/4P3/8/2p5/8/8/4p3/R3K2R w KQkq c6 13 10");
		int move = find("e1e2", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.to(move)).type(), is(WhiteKing));
		assertThat(game.piece(e1), is(nullValue()));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(removed));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.piece(Move.from(move)).type(), is(WhiteKing));
		assertThat(game.piece(e2).type(), is(BlackPawn));
		assertThat(game.halfMoveClock(), is(13));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(c6));
	}
	
}

