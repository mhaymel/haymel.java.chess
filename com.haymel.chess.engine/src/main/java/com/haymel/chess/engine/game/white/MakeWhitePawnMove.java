/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhitePawnMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert Move.type(move) == pawn;
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) != 0;
		assert rank(Move.to(move)) != 7;
	
		final int from = Move.from(move);
		Piece piece = game.piece(from);
		final int to = Move.to(move);
		game.whitePositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		game.pushHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == WhitePawn;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.type(move) == pawn;
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == WhitePawn;

		game.activeColorWhite();
		game.popHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		game.clear(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		game.whitePositionValue(piece.type(), to, from);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.piece(Move.to(move)) == null;
		assert game.assertVerify();
	}

}
