/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackCaptureMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == black; 
		assert Move.type(move) == capture;
		assert game.piece(Move.from(move)).type() != BlackKing;
		assert game.piece(Move.from(move)).type() != BlackRook;
		assert PieceType.black(game.piece(Move.from(move)).type());
		assert game.containsBlackPiece(game.piece(Move.from(move)));

		Piece victim = game.piece(Move.to(move));
		assert PieceType.white(victim.type());
		assert game.containsWhitePiece(victim);
		assert victim.type() != WhiteKing;
		game.pushVictim(victim);
		
		game.pushCastlingRight();
		switch(Move.to(move)) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}

		Piece piece = game.piece(Move.from(move));
 		game.blackPositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		victim.captured(true);
		game.removeWhite(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == piece;
		assert game.containsWhitePiece(victim);
		assert victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == capture;
		assert game.piece(Move.to(move)).type() != BlackKing;
		assert game.piece(Move.to(move)).type() != BlackRook;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		Piece victim = game.popVictim();

		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		assert victim.captured();
		
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.blackPositionValue(piece.type(), Move.to(move), Move.from(move));
		game.popCastlingRight();
		
		assert game.halfMoveClock() >= 0;
		assert PieceType.black(game.piece(Move.from(move)).type());
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == victim;
		assert !victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == black;
		assert game.assertVerify();
	}
	
}
