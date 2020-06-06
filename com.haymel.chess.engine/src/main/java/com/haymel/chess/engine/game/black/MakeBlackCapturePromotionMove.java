/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackCapturePromotionMove {

	public static void make(Game game, Move move, int pieceType) {
		assert PieceType.pieceTypeValid(pieceType);
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert 
			move.type() == capturePromotionQueen ||
			move.type() == capturePromotionRook ||
			move.type() == capturePromotionBishop ||
			move.type() == capturePromotionKnight;
		assert game.piece(move.from()).type() == BlackPawn;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.white(move.capturedPiece().type());
		assert game.containsWhitePiece(move.capturedPiece());
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.piece(move.to()).type() != WhitePawn;
		assert Field.rank(move.from()) == 1;
		assert Field.rank(move.to()) == 0;
		assert Math.abs(Field.file(move.from()) - Field.file(move.to())) == 1;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsBlackPiece(game.piece(move.from()));
		assert pieceType == BlackQueen || pieceType == BlackRook  || pieceType == BlackBishop  || pieceType == BlackKnight;
		
		game.pushCastlingRight();
		switch(move.to()) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(pieceType);
		piece.field(move.to());
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		move.capturedPiece().captured(true);
		game.removeWhite(move.capturedPiece());
		game.push(move);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.containsBlackPiece(piece);
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == pieceType;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert 
			move.type() == capturePromotionQueen ||
			move.type() == capturePromotionRook ||
			move.type() == capturePromotionBishop ||
			move.type() == capturePromotionKnight;
		assert game.piece(move.from()) == null;
		assert PieceType.black(game.piece(move.to()).type());
		assert PieceType.white(move.capturedPiece().type());
		assert game.containsWhitePiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert Field.rank(move.from()) == 1;
		assert Field.rank(move.to()) == 0;
		assert Math.abs(Field.file(move.from()) - Field.file(move.to())) == 1;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(BlackPawn);
		piece.captured(false);
		game.addBlack(piece);
		piece.field(move.from());
		game.place(piece);
		move.capturedPiece().captured(false);
		game.addWhite(move.capturedPiece());
		game.place(move.capturedPiece());
		game.popCastlingRight();
		game.blackPositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black;
		assert game.piece(move.from()).type() == BlackPawn;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.white(move.capturedPiece().type());
		assert game.containsWhitePiece(move.capturedPiece());
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

}
