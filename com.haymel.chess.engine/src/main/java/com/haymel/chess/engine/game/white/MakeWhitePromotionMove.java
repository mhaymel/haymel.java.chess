/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.file;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhitePromotionMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == promotion;
		assert game.piece(move.from()).type() == WhitePawn;
		assert game.piece(move.to()) == null;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert 
			move.pieceType() == WhiteQueen || 
			move.pieceType() == WhiteRook  ||
			move.pieceType() == WhiteBishop  ||
			move.pieceType() == WhiteKnight;
		assert game.containsWhitePiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		game.removeWhite(piece);
		piece.type(move.pieceType());
		piece.field(move.to());
		game.addWhite(piece);
		game.place(piece);
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == promotion;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.from()) == null;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(move.to()));
	
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		game.removeWhite(piece);
		piece.type(WhitePawn);
		piece.field(move.from());
		game.addWhite(piece);
		game.place(piece);
		
		assert game.containsWhitePiece(piece);
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == white; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).type() == WhitePawn;
		assert game.assertVerify();
	}

}
