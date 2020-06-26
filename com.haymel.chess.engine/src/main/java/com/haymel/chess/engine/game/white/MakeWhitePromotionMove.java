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
import static com.haymel.chess.engine.board.Field.removed;
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

	public static void make(Game game, int move, int pieceType) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white; 
		assert 
			Move.type(move) == promotionQueen ||
			Move.type(move) == promotionRook ||
			Move.type(move) == promotionBishop ||
			Move.type(move) == promotionKnight;
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) == 6;
		assert rank(Move.to(move)) == 7;
		assert file(Move.from(move)) == file(Move.to(move));
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert pieceType == WhiteQueen || pieceType == WhiteRook  || pieceType == WhiteBishop  || pieceType == WhiteKnight;
		assert game.containsWhitePiece(game.piece(Move.from(move)));
		
		Piece piece = game.piece(Move.from(move));
		game.clear(Move.from(move));
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(pieceType);
		piece.field(Move.to(move));
		piece.captured(false);
		game.addWhite(piece);
		game.place(piece);
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == pieceType;
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == black; 
		assert 
			Move.type(move) == MoveType.promotionQueen ||
			Move.type(move) == MoveType.promotionRook ||
			Move.type(move) == MoveType.promotionBishop ||
			Move.type(move) == MoveType.promotionKnight;
		assert game.piece(Move.from(move)) == null;
		assert rank(Move.from(move)) == 6;
		assert rank(Move.to(move)) == 7;
		assert file(Move.from(move)) == file(Move.to(move));
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(Move.to(move)));
	
		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(WhitePawn);
		piece.field(Move.from(move));
		piece.captured(false);
		game.addWhite(piece);
		game.place(piece);
		
		assert game.containsWhitePiece(piece);
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == white; 
		assert game.piece(Move.to(move)) == null;
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert game.assertVerify();
	}

}
