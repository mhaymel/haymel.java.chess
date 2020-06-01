/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	01.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteEnpassantMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).type() == WhitePawn;
		assert rank(move.from()) == 4;
		assert game.piece(game.enPassant()) == null;
		assert game.piece(down(game.enPassant())).type() == BlackPawn;
		assert game.piece(down(game.enPassant())) == move.capturedPiece();
		assert game.containsBlackPiece(move.capturedPiece());
		
		Piece piece = game.piece(move.from());
		game.whitePositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.clear(move.capturedPiece().field());
		move.capturedPiece().captured(true);
		game.removeBlack(move.capturedPiece());
		game.push(move);
		game.resetHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()) == piece;
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.activeColor() == black; 
		assert game.piece(move.to()).type() == WhitePawn;
		assert game.piece(move.from()) == null;
		assert game.containsBlackPiece(move.capturedPiece());
		assert move.capturedPiece().captured();
		assert game.piece(down(game.enPassant())) == null;
		assert game.assertVerify();
		
		game.activeColorWhite();
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		move.capturedPiece().captured(false);
		game.addBlack(move.capturedPiece());
		game.place(move.capturedPiece());
		game.whitePositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).type() == WhitePawn;
		assert rank(move.from()) == 4;
		assert game.piece(game.enPassant()) == null;
		assert game.piece(down(game.enPassant())).type() == BlackPawn;
		assert game.piece(down(game.enPassant())) == move.capturedPiece();
		assert game.containsBlackPiece(move.capturedPiece());
		assert !move.capturedPiece().captured();
		assert game.assertVerify();
	}

}
