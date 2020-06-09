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
		assert game.containsBlackPiece(game.piece(move.from()));

		Piece victim = game.piece(move.to());
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		game.pushVictim(victim);
		
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
		victim.captured(true);
		game.removeWhite(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.piece(move.from()) == null;
		assert PieceType.black(game.piece(move.to()).type());
		assert game.piece(move.to()) == piece;
		assert victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == white; 
		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == captureKingMove;
		assert game.piece(move.to()).type() == BlackKing;
		assert game.piece(move.from()) == null;

		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		piece.field(move.from());
		game.place(piece);
		Piece victim = game.popVictim();
	
		assert PieceType.white(victim.type());
		assert victim.type() != WhiteKing;
		assert victim.captured();
	
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.blackPositionValue(piece.type(), move.to(), move.from());
		game.popCastlingRight();

		assert game.halfMoveClock() >= 0;
		assert game.piece(move.from()).type() == BlackKing;
		assert PieceType.white(game.piece(move.to()).type());
		assert game.piece(move.to()) == victim;
		assert !victim.captured();
		assert game.containsBlackPiece(piece);
		assert game.activeColor() == black;
		assert game.assertVerify();
	}

}
