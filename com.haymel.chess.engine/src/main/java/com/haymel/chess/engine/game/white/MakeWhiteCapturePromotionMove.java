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
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
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

public final class MakeWhiteCapturePromotionMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == capturePromotion;
		assert game.piece(move.from()).type() == WhitePawn;
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().black();
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.piece(move.to()).type() != BlackPawn;
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert Math.abs(file(move.from()) - file(move.to())) == 1;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(move.from()));
		assert 
			move.pieceType() == WhiteQueen || 
			move.pieceType() == WhiteRook  ||
			move.pieceType() == WhiteBishop  ||
			move.pieceType() == WhiteKnight;
		
		game.pushCastlingRight();
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}

		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.type(move.pieceType());
		game.whitePromotion(move);
		piece.field(move.to());
		game.place(piece);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert move.type() == capturePromotion;
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
		assert move.capturedPiece().black();
		assert !game.containsBlackPiece(move.capturedPiece());
		assert rank(move.from()) == 6;
		assert rank(move.to()) == 7;
		assert Math.abs(file(move.from()) - file(move.to())) == 1;

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		piece.type(WhitePawn);
		game.whiteUndoPromotion(move);
		game.place(piece);
		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white;
		assert game.piece(move.from()).white();
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().black();
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

}
