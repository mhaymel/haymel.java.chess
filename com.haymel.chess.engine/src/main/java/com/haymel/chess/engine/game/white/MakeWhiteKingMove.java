/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteKingMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white; 
		assert Move.type(move) == normalKingMove;
		assert game.piece(Move.from(move)).type() == WhiteKing;
		assert game.piece(Move.to(move)) == null;
		
		game.pushCastlingRight();
		game.castlingRight().white().disable();
		Piece piece = game.piece(Move.from(move));
		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		game.push(move);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.piece(Move.from(move)) == null;
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert Move.type(move) == normalKingMove;
		assert game.piece(Move.to(move)).type() == WhiteKing;
		assert game.piece(Move.from(move)) == null;

		game.activeColorWhite();
		game.decHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert game.piece(Move.to(move)) == null;
		assert game.piece(Move.from(move)).type() == WhiteKing;
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

}
