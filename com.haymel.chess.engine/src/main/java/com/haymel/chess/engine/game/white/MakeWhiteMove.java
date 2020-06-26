/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert Move.type(move) == normal;
		assert PieceType.white(game.piece(Move.from(move)).type());
		assert game.piece(Move.from(move)).type() != WhitePawn;
		assert game.piece(Move.from(move)).type() != WhiteKing;
		assert game.piece(Move.from(move)).type() != WhiteRook;
		assert game.piece(Move.to(move)) == null;
		
		Piece piece = game.piece(Move.from(move));
		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		game.incHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)).type() != WhitePawn;
		assert game.piece(Move.to(move)).type() != WhiteKing;
		assert game.piece(Move.to(move)).type() != WhiteRook;
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert Move.type(move) == normal;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)).type() != WhitePawn;
		assert game.piece(Move.to(move)).type() != WhiteKing;
		assert game.piece(Move.to(move)).type() != WhiteRook;
		assert game.piece(Move.from(move)) == null;

		game.activeColorWhite();
		game.decHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.piece(Move.to(move)) == null;
		assert PieceType.white(game.piece(Move.from(move)).type());
		assert game.piece(Move.from(move)).type() != WhitePawn;
		assert game.piece(Move.from(move)).type() != WhiteKing;
		assert game.piece(Move.from(move)).type() != WhiteRook;
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

}
