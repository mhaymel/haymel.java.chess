/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackKingMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == black; 
		assert Move.type(move) == normalKingMove;
		assert PieceType.black(game.piece(Move.from(move)).type());
		assert game.piece(Move.from(move)).type() == BlackKing;
		assert game.piece(Move.to(move)) == null;
		
		game.pushCastlingRight();
		game.castlingRight().black().disable();
		final int from = Move.from(move);
		Piece piece = game.piece(from);
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
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white; 
		assert Move.type(move) == normalKingMove;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)).type() == BlackKing;
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
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)).type() == BlackKing;
		assert game.piece(Move.to(move)) == null;
		assert game.assertVerify();
	}

}
