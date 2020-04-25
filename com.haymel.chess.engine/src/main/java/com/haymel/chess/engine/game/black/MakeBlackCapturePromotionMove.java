/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackCapturePromotionMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == capturePromotion;
		assert game.piece(move.from()).blackPawn();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().white();
		assert game.containsWhitePiece(move.capturedPiece());
		assert !game.piece(move.to()).whiteKing();
		assert !game.piece(move.to()).whitePawn();
		assert Field.rank(move.from()) == 1;
		assert Field.rank(move.to()) == 0;
		assert Math.abs(Field.file(move.from()) - Field.file(move.to())) == 1;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsBlackPiece(game.piece(move.from()));
		assert 
			move.pieceType() == BlackQueen || 
			move.pieceType() == BlackRook  ||
			move.pieceType() == BlackBishop  ||
			move.pieceType() == BlackKnight;
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.type(move.pieceType());
		game.blackPromotion(move);
		piece.field(move.to());
		game.place(piece);
		game.removeWhite(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.to()).black();
		assert game.piece(move.to()) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert !game.containsWhitePiece(move.capturedPiece());
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert move.type() == capturePromotion;
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).type() == move.pieceType();
		assert game.piece(move.to()).black();
		assert move.capturedPiece().white();
		assert !game.containsWhitePiece(move.capturedPiece());
		assert Field.rank(move.from()) == 1;
		assert Field.rank(move.to()) == 0;
		assert Math.abs(Field.file(move.from()) - Field.file(move.to())) == 1;

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		piece.type(BlackPawn);
		game.blackUndoPromotion(move);
		game.place(piece);
		game.addWhite(move.capturedPiece());
		game.place(move.capturedPiece());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black;
		assert game.piece(move.from()).blackPawn();
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == move.capturedPiece();
		assert move.capturedPiece().white();
		assert game.containsWhitePiece(move.capturedPiece());
		assert !game.piece(move.to()).whiteKing();
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

}
