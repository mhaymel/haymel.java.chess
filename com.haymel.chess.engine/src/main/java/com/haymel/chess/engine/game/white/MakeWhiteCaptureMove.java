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

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert Move.type(move) == capture;
		assert PieceType.white(game.piece(Move.from(move)).type());
		assert PieceType.black(game.piece(Move.to(move)).type());
		assert game.piece(Move.from(move)).type() != WhiteKing;
		assert game.piece(Move.from(move)).type() != WhiteRook;
		assert game.containsWhitePiece(game.piece(Move.from(move)));
		
		Piece victim = game.piece(Move.to(move));
		assert PieceType.black(victim.type());
		assert victim.type() != BlackKing;
		game.pushVictim(victim);
		
		game.pushCastlingRight();
		switch(Move.to(move)) {
		case a8: game.castlingRight().black().disableQueenside(); break;
		case h8: game.castlingRight().black().disableKingside(); break;
		}
		Piece piece = game.piece(Move.from(move));
 		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		victim.captured(true);
		game.removeBlack(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();

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
		assert Move.type(move) == capture;
		assert game.piece(Move.from(move)) == null;
		assert PieceType.white(game.piece(Move.to(move)).type());
		assert game.piece(Move.to(move)).type() != WhiteKing;
		assert game.piece(Move.to(move)).type() != WhiteRook;

		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		Piece victim = game.popVictim();
	
		assert PieceType.black(victim.type());
		assert victim.type() != BlackKing;
		assert victim.captured();
		
		victim.captured(false);
		game.addBlack(victim);
		game.place(victim);
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
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
