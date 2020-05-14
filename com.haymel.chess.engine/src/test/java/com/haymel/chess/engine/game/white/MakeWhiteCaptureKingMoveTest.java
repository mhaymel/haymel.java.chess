/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.util.Require.nonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public class MakeWhiteCaptureKingMoveTest {

	@Test
	public void captureMoveDisablesKingAndQueensideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R w KQkq - 0 1");
		Move move = find("e1e2", game);

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
		Move move = find("e1e2", game);

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
		Move move = find("e1e2", game);

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
		Move move = find("e1e2", game);

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
		Move move = find("a7a8", game);

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
		Move move = find("a7a8", game);

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
		Move move = find("h7h8", game);

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
		Move move = find("h7h8", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}

	private void undoMove(Game game) {
		new MakeMove(game).undoMove();
	}

	private static void makeMove(Move move, Game game) {
		new MakeMove(game).makeMove(move);
	}

	private static Game fromFen(String fen) {
		return new GameFromFEN(fen).execute();		
	}
	
	private static Move find(String move, Game game) {
		return nonNull(new MoveFinder(game.moves()).find(move), "move");
	}
	
}

