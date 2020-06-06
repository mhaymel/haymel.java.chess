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
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhitePromotionMove {

	public static void make(Game game, Move move, int pieceType) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert 
			move.type() == promotionQueen ||
			move.type() == promotionRook ||
			move.type() == promotionBishop ||
			move.type() == promotionKnight;
		assert game.piece(move.from()).type() == WhitePawn;
		assert game.piece(move.to()) == null;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert pieceType == WhiteQueen || pieceType == WhiteRook  || pieceType == WhiteBishop  || pieceType == WhiteKnight;
		assert game.containsWhitePiece(game.piece(move.from()));
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(pieceType);
		piece.field(move.to());
		piece.captured(false);
		game.addWhite(piece);
		game.place(piece);
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();

		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == pieceType;
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert 
			move.type() == MoveType.promotionQueen ||
			move.type() == MoveType.promotionRook ||
			move.type() == MoveType.promotionBishop ||
			move.type() == MoveType.promotionKnight;
		assert game.piece(move.from()) == null;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert file(move.from()) == file(move.to());
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(move.to()));
	
		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(WhitePawn);
		piece.field(move.from());
		piece.captured(false);
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
