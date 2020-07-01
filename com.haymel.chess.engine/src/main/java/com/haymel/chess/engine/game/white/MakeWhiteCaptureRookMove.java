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
import static com.haymel.chess.engine.board.Field.removed;
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

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white; 
		assert Move.type(move) == captureRookMove;
		assert game.piece(Move.from(move)).type() == WhiteRook;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.containsWhitePiece(game.piece(Move.from(move)));

		final int to = Move.to(move);
		Piece victim = game.piece(to);
		assert PieceType.black(victim.type());
		assert victim.type() != BlackKing;
		game.pushVictim(victim);
		
		game.pushCastlingRight();
		final int from = Move.from(move);
		switch(from) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		switch(to) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		Piece piece = game.piece(from);
 		game.whitePositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		victim.captured(true);
		game.removeBlack(victim);
		game.pushHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == piece;
		assert victim.captured();
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == captureRookMove;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());

		game.activeColorWhite();
		game.popHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		Piece victim = game.popVictim();

		assert PieceType.black(victim.type());
		assert victim.type() != BlackKing;
		assert victim.captured();

		victim.captured(false);
		game.addBlack(victim);
		game.place(victim);
		game.whitePositionValue(piece.type(), to, from);
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert PieceType.white(game.piece(Move.from(move)).type());
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == victim;
		assert game.piece(Move.to(move)).type() != BlackKing;
		assert game.containsWhitePiece(piece);
		assert game.activeColor() == white;
		assert game.assertVerify();
	}

}
