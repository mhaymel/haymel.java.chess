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
		
		Piece piece = game.piece(Move.from(move));
		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
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
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.piece(Move.to(move)) == null;
		assert game.assertVerify();
	}

}
