/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert Move.type(move) == normal;
		assert PieceType.black(game.piece(Move.from(move)).type());
		assert game.piece(Move.from(move)).type() != BlackPawn;
		assert game.piece(Move.from(move)).type() != BlackKing;
		assert game.piece(Move.from(move)).type() != BlackRook;
		assert game.piece(Move.to(move)) == null;
		
		final int from = Move.from(move);
		Piece piece = game.piece(Move.from(move));
		final int to = Move.to(move);
		game.blackPositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert Move.type(move) == normal;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)).type() != BlackPawn;
		assert game.piece(Move.to(move)).type() != BlackKing;
		assert game.piece(Move.to(move)).type() != BlackRook;
		assert game.piece(Move.from(move)) == null;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.decHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		game.clear(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		game.blackPositionValue(piece.type(), to, from);
		
		assert game.halfMoveClock() >= 0;
		assert game.piece(Move.to(move)) == null;
		assert PieceType.black(game.piece(Move.from(move)).type());
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}
	
}
