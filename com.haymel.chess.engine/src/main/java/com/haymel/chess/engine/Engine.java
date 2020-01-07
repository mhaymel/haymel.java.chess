/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.01.2020
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.engine;

import static com.haymel.chess.engine.game.ActiveColor.white;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.game.StartposCreator;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.BlackMoves;
import com.haymel.chess.engine.moves.white.WhiteMoves;

public class Engine {

	private Game game;
	
	public Engine() {
		startpos();
	}
	
	private void startpos() {
		game = new Game();
		new StartposCreator(game).execute();
	}

	public void move(String moveAsString) {
		FieldsFromMoveString fields = new FieldsFromMoveString(moveAsString);
	
		Moves moves = (game.activeColor() == white) ? whiteMoves() : blackMoves();
		Move move = moves.findMove(fields.from(), fields.to());
		if (move == null)
			throw new IllegalStateException(format("cannot find a move %s%s", fields.from(), fields.to()));
		
		new MakeMove(game).makeMove(move);
	}

	private Moves blackMoves() {
		Board board = game.board();
		PieceList pieces = game.blackPieces();
		Moves moves = new Moves();
		BlackMoves black = new BlackMoves(board, moves);
		black.generate(pieces, game.enPassant());

		assert moves.kingCaptureCount() == 0;
		return moves;
	}

	private Moves whiteMoves() {
		Board board = game.board();
		PieceList pieces = game.whitePieces();
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(board, moves);
		whiteMoves.generate(pieces, game.enPassant());

		assert moves.kingCaptureCount() == 0;
		return moves;
	}

}
