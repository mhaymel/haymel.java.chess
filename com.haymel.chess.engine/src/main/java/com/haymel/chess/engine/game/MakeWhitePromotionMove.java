/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

final class MakeWhitePromotionMove {

	static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == promotion;
		assert game.piece(move.from()).whitePawn();
		assert game.piece(move.to()).free();
		assert move.to().rank() == 7;
		assert game.halfMoveClock() >= 0;
		assert 
			move.pieceType() == WhiteQueen || 
			move.pieceType() == WhiteRook  ||
			move.pieceType() == WhiteBishop  ||
			move.pieceType() == WhiteKnight;
		assert game.containsWhitePiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.type(move.pieceType());
		piece.field(move.to());
		game.place(piece);
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(move.from()).free();
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == promotion;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.from()).free();
		assert move.to().rank() == 7;
		assert game.halfMoveClock() >= 0;
		assert game.containsWhitePiece(game.piece(move.to()));
	
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		piece.type(WhitePawn);
		game.place(piece);
		
		assert game.containsWhitePiece(piece);
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(move.to()).free();
		assert game.piece(move.from()).whitePawn();
		assert game.assertVerify();
	}

}
