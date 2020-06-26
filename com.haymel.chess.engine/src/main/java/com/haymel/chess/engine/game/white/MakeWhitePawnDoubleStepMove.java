/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhitePawnDoubleStepMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white; 
		assert Move.type(move) == pawnDoubleStep;
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) == 1;
		
		Piece piece = game.piece(Move.from(move));
		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		game.enPassant(up(Move.from(move)));
		game.pushHalfMoveClock();
		game.activeColorBlack();

		assert game.enPassant() == up(Move.from(move));
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.piece(Move.to(move)).type() == WhitePawn;
		assert game.piece(Move.from(move)) == null;

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
