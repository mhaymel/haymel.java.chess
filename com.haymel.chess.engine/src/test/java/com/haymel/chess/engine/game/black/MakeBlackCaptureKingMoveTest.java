/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.util.Require.nonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public class MakeBlackCaptureKingMoveTest {

	@Test
	public void captureMoveDisablesKingAndQueensideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R b KQkq - 0 1");
		Move move = find("e8e7", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}
	
	@Test
	public void captureMoveDisablesKingsideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R b KQk - 0 1");
		Move move = find("e8e7", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}
	
	@Test
	public void captureMoveDisablesQueensideCastlingRight() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R b KQq - 0 1");
		Move move = find("e8e7", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(true));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}

	@Test
	public void captureMoveWithAlreadyDisabledCastlingRights() {
		Game game = fromFen("r3k2r/4P3/8/8/8/8/4p3/R3K2R b KQ - 0 1");
		Move move = find("e8e7", game);

		makeMove(move, game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}

	@Test
	public void capturingRookAtA1DisablesWhiteQueensideCastlingRight() {
		Game game = fromFen("8/8/8/8/8/8/k7/R3K2R b KQ - 0 1 ");
		Move move = find("a2a1", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}

	@Test
	public void capturingRookAtA1WhenWhiteHasNoCastlingRights() {
		Game game = fromFen("8/8/8/8/8/8/k7/R3K2R b - - 0 1");
		Move move = find("a2a1", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
	}
	
	@Test
	public void capturingRookAtH1DisablesWhiteKingsideCastling() {
		Game game = fromFen("8/8/8/8/8/8/7k/R3K2R b KQ - 0 1");
		Move move = find("h2h1", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(true));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
	}

	@Test
	public void capturingRookAtH1WhenBlackHasNoCastlingRights() {
		Game game = fromFen("8/8/8/8/8/8/7k/R3K2R b - - 0 1");
		Move move = find("h2h1", game);

		makeMove(move, game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		
		undoMove(game);
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
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

