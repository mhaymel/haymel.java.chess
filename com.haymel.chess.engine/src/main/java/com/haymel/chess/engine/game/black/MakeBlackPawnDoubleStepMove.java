/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackPawnDoubleStepMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == black; 
		assert Move.type(move) == pawnDoubleStep;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.piece(up(Move.to(move))) == null;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) == 6;
		
		final int from = Move.from(move);
		Piece piece = game.piece(from);
		final int to = Move.to(move);
		game.blackPositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		game.enPassant(down(from));
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.enPassant() == down(Move.from(move));
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == BlackPawn;
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.piece(Move.to(move)).type() == BlackPawn;
		assert game.piece(up(Move.to(move))) == null;
		assert game.piece(Move.from(move)) == null;
		assert rank(Move.from(move)) == 6;
		assert game.activeColor() == white; 

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		game.clear(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		game.blackPositionValue(piece.type(), to, from);
		
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == black; 
		assert game.piece(Move.to(move)) == null;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.assertVerify();
	}

}
