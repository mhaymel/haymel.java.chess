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
import static com.haymel.chess.engine.board.Field.removed;
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

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == black; 
		assert Move.type(move) == captureKingMove;
		assert game.piece(Move.from(move)).type() == BlackKing;
		assert game.containsBlackPiece(game.piece(Move.from(move)));

		final int to = Move.to(move);
		Piece victim = game.piece(to);
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		game.pushVictim(victim);
		
		game.pushCastlingRight();
		game.castlingRight().black().disable();
		switch(to) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		
		final int from = Move.from(move);
		Piece piece = game.piece(from);
 		game.blackPositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		victim.captured(true);
		game.removeWhite(victim);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == piece;
		assert victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == captureKingMove;
		assert game.piece(Move.to(move)).type() == BlackKing;
		assert game.piece(Move.from(move)) == null;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		Piece victim = game.popVictim();
	
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		assert victim.captured();
	
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.blackPositionValue(piece.type(), to, from);
		game.popCastlingRight();

		assert game.halfMoveClock() >= 0;
		assert game.piece(Move.from(move)).type() == BlackKing;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == victim;
		assert !victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == black;
		assert game.assertVerify();
	}

}
