/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.file;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackPromotionMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == promotion;
		assert game.piece(move.from()).type() == BlackPawn;
		assert game.piece(move.to()) == null;
		assert rank(move.from()) == 1;
		assert rank(move.to()) == 0;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert 
			move.pieceType() == BlackQueen || 
			move.pieceType() == BlackRook  ||
			move.pieceType() == BlackBishop  ||
			move.pieceType() == BlackKnight;
		assert game.containsBlackPiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(move.pieceType());
		piece.field(move.to());
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		game.push(move);
		game.resetHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 2;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == promotion;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.from()) == null;
		assert rank(move.from()) == 1;
		assert rank(move.to()) == 0;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsBlackPiece(game.piece(move.to()));
	
		game.activeColorBlack();
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(BlackPawn);
		piece.field(move.from());
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		
		assert game.containsBlackPiece(piece);
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == black; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).type() == BlackPawn;
		assert game.assertVerify();
	}

}
