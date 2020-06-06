/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.file;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteCapturePromotionMove {

	public static void make(Game game, Move move, int pieceType) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert 
			move.type() == capturePromotionQueen ||
			move.type() == capturePromotionRook ||
			move.type() == capturePromotionBishop ||
			move.type() == capturePromotionKnight;
		assert game.piece(move.from()).type() == WhitePawn;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.piece(move.to()).type() != BlackPawn;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert Math.abs(file(move.from()) - file(move.to())) == 1;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(move.from()));
		assert pieceType == WhiteQueen || pieceType == WhiteRook  || pieceType == WhiteBishop  || pieceType == WhiteKnight;
		
		game.pushCastlingRight();
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}

		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(pieceType);
		piece.field(move.to());
		piece.captured(false);
		game.addWhite(piece);
		game.place(piece);
		move.capturedPiece().captured(true);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();

		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == pieceType;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert 
			move.type() == capturePromotionQueen ||
			move.type() == capturePromotionRook ||
			move.type() == capturePromotionBishop ||
			move.type() == capturePromotionKnight;
		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert Math.abs(file(move.from()) - file(move.to())) == 1;

		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(WhitePawn);
		piece.captured(false);
		game.addWhite(piece);
		piece.field(move.from());
		game.place(piece);
		move.capturedPiece().captured(false);
		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		game.popCastlingRight();
		game.whitePositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white;
		assert PieceType.white(game.piece(move.from()).type());
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

}
