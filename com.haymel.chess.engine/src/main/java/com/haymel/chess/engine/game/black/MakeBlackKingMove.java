/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normalKingMove;
		assert PieceType.black(game.piece(move.from()).type());
		assert game.piece(move.from()).type() == BlackKing;
		assert game.piece(move.to()) == null;
		
		game.pushCastlingRight();
		game.castlingRight().black().disable();
		Piece piece = game.piece(move.from());
		game.blackPositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.push(move);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == normalKingMove;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()).type() == BlackKing;
		assert game.piece(move.from()) == null;

		game.activeColorBlack();
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		game.blackPositionValue(piece.type(), move.to(), move.from());
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert game.piece(move.from()).type() == BlackKing;
		assert game.piece(move.to()) == null;
		assert game.assertVerify();
	}

}
