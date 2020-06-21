/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelException.throwHE;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.algebraic.MoveFinder;

public class MakeMoveFromString {	//TODO unit test

	private final Game game;
	
	public MakeMoveFromString(Game game) {
		this.game = verified(game);
	}
	
	public void move(String fromString) {
		nonEmpty(fromString, "moveAsString");
		if (!game.assertVerify())
			throwHE("game is in an illegal state");

		make(find(fromString));
	}

	private void make(int move) {
		assert Move.validMove(move);
		new MakeMove(game).makeMove(move);
	}

	private int find(String moveAsString) {
		int move = new MoveFinder(game.moves()).find(moveAsString);
		if (move == 0)
			return throwIAE("cannot find move %s", moveAsString);
		
		Move.validMove(move);
		
		return move;
	}

	private static Game verified(Game game) {
		if (!nonNull(game, "game").assertVerify())
			throwHE("game is in an illegal state");
		return game;
	}
		
}
