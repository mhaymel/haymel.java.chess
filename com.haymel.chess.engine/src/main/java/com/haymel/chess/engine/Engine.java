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
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.FieldFromString;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.board.StartposCreator;
import com.haymel.chess.engine.game.ActiveColor;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.white.WhiteMoves;

public class Engine {

	private final Game game;
	
	public Engine() {
		Board board = new Board();
		new StartposCreator(board).execute();
		game = new Game(board);
	}
	
	public void move(String move) {
		if (move.length() != 4)
			throw new IllegalArgumentException(format("move must have a lenght of 4 but is '%s'", move));
		
		String from = move.substring(0, 2);
		String to = move.substring(2);
		
		Field f1 = new FieldFromString(from).value();
		Field f2 = new FieldFromString(to).value();
		
		if (game.activeColor() == white)
			moveWhite(f1, f2);
		else
			moveBlack(f1, f2);
	}

	private void moveBlack(Field from, Field to) {
		Board board = game.board();
		PieceList pieces = game.whitePieces();
		Moves moves = new Moves();
		WhiteMoves whiteMoves = new WhiteMoves(board, moves);
		whiteMoves.generate(pieces, game.enPassant());

		
	}

	private void moveWhite(Field from, Field to) {
		// TODO Auto-generated method stub
		
	}

}
