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
import static com.haymel.chess.engine.board.Field.removed;
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

	public static void make(Game game, int move, int pieceType) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert 
			Move.type(move) == capturePromotionQueen ||
			Move.type(move) == capturePromotionRook ||
			Move.type(move) == capturePromotionBishop ||
			Move.type(move) == capturePromotionKnight;
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert rank(Move.from(move)) == 6;
		assert rank(Move.to(move)) == 7;
		assert game.halfMoveClock() >= 0;
		assert game.fullMoveNumber() >= 1;
		assert game.containsWhitePiece(game.piece(Move.from(move)));
		assert pieceType == WhiteQueen || pieceType == WhiteRook  || pieceType == WhiteBishop  || pieceType == WhiteKnight;

		Piece victim = game.piece(Move.to(move));
		
		assert PieceType.black(victim.type());
		assert game.containsBlackPiece(victim);
		assert victim.type() != BlackKing;
		assert victim.type() != BlackPawn;
		game.pushVictim(victim);
	
		game.pushCastlingRight();
		switch(Move.to(move)) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}

		Piece piece = game.piece(Move.from(move));
		game.clear(Move.from(move));
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(pieceType);
		piece.field(Move.to(move));
		piece.captured(false);
		game.addWhite(piece);
		game.place(piece);
		victim.captured(true);
		game.removeBlack(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.containsWhitePiece(piece);
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)).type() == pieceType;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == piece;
		assert game.halfMoveClock() == 0;
		assert game.fullMoveNumber() >= 1;
		assert victim.captured();
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert 
			Move.type(move) == capturePromotionQueen ||
			Move.type(move) == capturePromotionRook ||
			Move.type(move) == capturePromotionBishop ||
			Move.type(move) == capturePromotionKnight;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert rank(Move.from(move)) == 6;
		assert rank(Move.to(move)) == 7;
		assert Math.abs(file(Move.from(move)) - file(Move.to(move))) == 1;

		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		piece.captured(true);
		game.removeWhite(piece);
		piece.type(WhitePawn);
		piece.captured(false);
		game.addWhite(piece);
		piece.field(Move.from(move));
		game.place(piece);
		Piece victim = game.popVictim();

		assert PieceType.black(victim.type());
		assert victim.type() != BlackKing;
		assert victim.captured();
		
		victim.captured(false);
		game.addBlack(victim);
		game.place(victim);
		game.popCastlingRight();
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white;
		assert PieceType.white(game.piece(Move.from(move)).type());
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)) == victim;
		assert game.piece(Move.to(move)).type() != BlackKing;
		assert game.containsWhitePiece(piece);
		assert game.assertVerify();
	}

}
