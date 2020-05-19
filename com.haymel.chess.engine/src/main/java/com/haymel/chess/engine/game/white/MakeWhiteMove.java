/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

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

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == normal;
		assert PieceType.white(game.piece(move.from()).type());
		assert game.piece(move.from()).type() != WhitePawn;
		assert game.piece(move.from()).type() != WhiteKing;
		assert game.piece(move.from()).type() != WhiteRook;
		assert game.piece(move.to()) == null;
		
		Piece piece = game.piece(move.from());
		game.whitePositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.push(move);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()).type() != WhitePawn;
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.piece(move.to()).type() != WhiteRook;
		assert game.activeColor() == black; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == normal;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()).type() != WhitePawn;
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.piece(move.to()).type() != WhiteRook;
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		game.whitePositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.piece(move.to()) == null;
		assert PieceType.white(game.piece(move.from()).type());
		assert game.piece(move.from()).type() != WhitePawn;
		assert game.piece(move.from()).type() != WhiteKing;
		assert game.piece(move.from()).type() != WhiteRook;
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

}
