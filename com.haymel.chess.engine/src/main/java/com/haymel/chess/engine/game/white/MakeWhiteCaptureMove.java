/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteCaptureMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == capture;
		assert PieceType.white(game.piece(move.from()).type());
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.from()).type() != WhiteKing;
		assert game.piece(move.from()).type() != WhiteRook;
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.containsWhitePiece(game.piece(move.from()));
		
		game.pushCastlingRight();
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		Piece piece = game.piece(move.from());
 		game.whitePositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		move.capturedPiece().captured(true);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == capture;
		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.piece(move.to()).type() != WhiteRook;
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();

		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		piece.field(move.from());
		game.place(piece);
		move.capturedPiece().captured(false);
		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		game.whitePositionValue(piece.type(), move.to(), move.from());
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert PieceType.white(game.piece(move.from()).type());
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.containsWhitePiece(piece);
		assert game.activeColor() == white;
		assert game.assertVerify();
	}
	
}
