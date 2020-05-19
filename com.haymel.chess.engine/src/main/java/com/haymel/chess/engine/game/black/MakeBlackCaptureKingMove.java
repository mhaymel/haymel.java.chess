/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackCaptureKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == captureKingMove;
		assert game.piece(move.from()).type() == BlackKing;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.white(move.capturedPiece().type());
		assert game.containsWhitePiece(move.capturedPiece());
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.containsBlackPiece(game.piece(move.from()));
		
		game.pushCastlingRight();
		game.castlingRight().black().disable();
		switch(move.to()) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		
		Piece piece = game.piece(move.from());
 		game.blackPositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.removeWhite(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.piece(move.from()) == null;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert !game.containsWhitePiece(move.capturedPiece());
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == captureKingMove;
		assert game.piece(move.to()).type() == BlackKing;
		assert game.piece(move.from()) == null;
		assert PieceType.white(move.capturedPiece().type());
		assert !game.containsWhitePiece(move.capturedPiece());

		Piece piece = game.piece(move.to());
		piece.field(move.from());
		game.place(piece);
		game.addWhite(move.capturedPiece());
		game.place(move.capturedPiece());
		game.blackPositionValue(piece.type(), move.to(), move.from());
		game.popCastlingRight();

		assert game.halfMoveClock() >= 0;
		assert game.piece(move.from()).type() == BlackKing;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == move.capturedPiece();
		assert PieceType.white(move.capturedPiece().type());
		assert game.containsWhitePiece(move.capturedPiece());
		assert game.piece(move.to()).type() != WhiteKing;
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == black;
		assert game.assertVerify();
	}

}
