/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteCaptureRookMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == captureRookMove;
		assert game.piece(move.from()).type() == WhiteRook;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.black(move.capturedPiece().type());
		assert game.containsBlackPiece(move.capturedPiece());
		assert game.piece(move.to()).type() != BlackKing;
		assert game.containsWhitePiece(game.piece(move.from()));
		
		game.pushCastlingRight();
		switch(move.from()) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		switch(move.to()) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		Piece piece = game.piece(move.from());
 		game.whitePositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert !game.containsBlackPiece(move.capturedPiece());
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == captureRookMove;
		assert game.piece(move.from()) == null;
		assert PieceType.white(game.piece(move.to()).type());
		assert PieceType.black(move.capturedPiece().type());
		assert !game.containsBlackPiece(move.capturedPiece());

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		game.place(piece);
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
