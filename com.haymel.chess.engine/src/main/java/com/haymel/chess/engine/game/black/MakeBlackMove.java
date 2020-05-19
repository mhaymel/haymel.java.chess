/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

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

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal;
		assert PieceType.black(game.piece(move.from()).type());
		assert game.piece(move.from()).type() != BlackPawn;
		assert game.piece(move.from()).type() != BlackKing;
		assert game.piece(move.from()).type() != BlackRook;
		assert game.piece(move.to()) == null;
		
		Piece piece = game.piece(move.from());
		game.blackPositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.push(move);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.piece(move.from()) == null;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == normal;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()).type() != BlackPawn;
		assert game.piece(move.to()).type() != BlackKing;
		assert game.piece(move.to()).type() != BlackRook;
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		game.blackPositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.piece(move.to()) == null;
		assert PieceType.black(game.piece(move.from()).type());
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}
	
}
