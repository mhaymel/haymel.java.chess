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
import static com.haymel.chess.engine.board.Field.removed;
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

	public static void make(Game game, int move, int pieceType) {
		assert Move.validMove(move);
		assert PieceType.pieceTypeValid(pieceType);
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert 
			Move.type(move) == capturePromotionQueen ||
			Move.type(move) == capturePromotionRook ||
			Move.type(move) == capturePromotionBishop ||
			Move.type(move) == capturePromotionKnight;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert Field.rank(Move.from(move)) == 1;
		assert Field.rank(Move.to(move)) == 0;
		assert Math.abs(Field.file(Move.from(move)) - Field.file(Move.to(move))) == 1;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert pieceType == BlackQueen || pieceType == BlackRook  || pieceType == BlackBishop  || pieceType == BlackKnight;
		
		Piece victim = game.piece(Move.to(move));
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		assert victim.type() != WhitePawn;
		game.pushVictim(victim);
		
		game.pushCastlingRight();
		switch(Move.to(move)) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		
		Piece piece = game.piece(Move.from(move));
		game.clear(Move.from(move));
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(pieceType);
		piece.field(Move.to(move));
		piece.captured(false);
		game.addBlack(piece);
		game.place(piece);
		victim.captured(true);
		game.removeWhite(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == pieceType;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert victim.captured();
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert 
			Move.type(move) == capturePromotionQueen ||
			Move.type(move) == capturePromotionRook ||
			Move.type(move) == capturePromotionBishop ||
			Move.type(move) == capturePromotionKnight;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert Field.rank(Move.from(move)) == 1;
		assert Field.rank(Move.to(move)) == 0;
		assert Math.abs(Field.file(Move.from(move)) - Field.file(Move.to(move))) == 1;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		piece.captured(true);
		game.removeBlack(piece);
		piece.type(BlackPawn);
		piece.captured(false);
		game.addBlack(piece);
		piece.field(Move.from(move));
		game.place(piece);
		Piece victim = game.popVictim();
	
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		assert victim.captured();
	
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.popCastlingRight();
		game.blackPositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black;
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == victim;
		assert !victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.assertVerify();
	}

}
