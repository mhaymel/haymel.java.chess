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
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackPromotionMove {

	public static void make(Game game, int move, int pieceType) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert 
			Move.type(move) == promotionQueen ||
			Move.type(move) == promotionRook ||
			Move.type(move) == promotionBishop ||
			Move.type(move) == promotionKnight;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.piece(Move.to(move)) == null;
		assert rank(Move.from(move)) == 1;
		assert rank(Move.to(move)) == 0;
		assert file(Move.from(move)) == file(Move.to(move));
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert pieceType == BlackQueen || pieceType == BlackRook  || pieceType == BlackBishop  || pieceType == BlackKnight;
		assert game.containsBlackPiece(game.piece(Move.from(move)));
		
		Piece piece = game.piece(Move.from(move));
		game.clear(Move.from(move));
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(pieceType);
		piece.field(Move.to(move));
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == pieceType;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 2;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert 
			Move.type(move) == promotionQueen ||
			Move.type(move) == promotionRook ||
			Move.type(move) == promotionBishop ||
			Move.type(move) == promotionKnight;
		assert game.piece(Move.from(move)) == null;
		assert rank(Move.from(move)) == 1;
		assert rank(Move.to(move)) == 0;
		assert file(Move.from(move)) == file(Move.to(move));
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsBlackPiece(game.piece(Move.to(move)));
	
		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(BlackPawn);
		piece.field(Move.from(move));
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		
		assert game.containsBlackPiece(piece);
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.activeColor() == black; 
		assert game.piece(Move.to(move)) == null;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert game.assertVerify();
	}

}
