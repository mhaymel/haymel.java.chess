/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.20.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackPawnMove {

	public static void make(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert Move.type(move) == pawn;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) != 7;
		assert rank(Move.to(move)) != 0;
		
		Piece piece = game.piece(Move.from(move));
		game.blackPositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == BlackPawn;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert game.piece(Move.to(move)).type() == BlackPawn;
		assert game.piece(Move.from(move)) == null;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		game.blackPositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == black; 
		assert game.piece(Move.to(move)) == null;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.assertVerify();
	}

}
