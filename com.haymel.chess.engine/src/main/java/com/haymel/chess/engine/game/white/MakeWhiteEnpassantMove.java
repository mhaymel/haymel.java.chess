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
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteEnpassantMove {

	public static void make(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert Move.type(move) == enpassant;
		assert Move.to(move) == game.enPassant();
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert rank(Move.from(move)) == 4;
		assert game.piece(game.enPassant()) == null;
		
		Piece victim = game.piece(down(game.enPassant()));
		assert PieceType.black(victim.type());
		assert victim.type() == BlackPawn;
		game.pushVictim(victim);
		
		Piece piece = game.piece(Move.from(move));
		game.whitePositionValue(piece.type(), Move.from(move), Move.to(move));
		game.clear(Move.from(move));
		piece.field(Move.to(move));
		game.place(piece);
		game.clear(victim.field());
		victim.captured(true);
		game.removeBlack(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.activeColorBlack();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.activeColor() == black; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)) == piece;
		assert victim.captured();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game != null;
		assert Move.validMove(move);
		assert game.assertVerify();
		assert Move.type(move) == enpassant;
		assert Move.to(move) == game.enPassant();
		assert game.activeColor() == black; 
		assert game.piece(Move.to(move)).type() == WhitePawn;
		assert game.piece(Move.from(move)) == null;
		assert game.piece(down(game.enPassant())) == null;
		assert game.assertVerify();
		
		game.activeColorWhite();
		game.popHalfMoveClock();
		Piece piece = game.piece(Move.to(move));
		game.clear(Move.to(move));
		piece.field(Move.from(move));
		game.place(piece);
		Piece victim = game.popVictim();

		assert PieceType.black(victim.type());
		assert victim.type() == BlackPawn;
		assert victim.captured();
		
		victim.captured(false);
		game.addBlack(victim);
		game.place(victim);
		game.whitePositionValue(piece.type(), Move.to(move), Move.from(move));
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert Move.to(move) == game.enPassant();
		assert game.piece(Move.from(move)).type() == WhitePawn;
		assert rank(Move.from(move)) == 4;
		assert game.piece(game.enPassant()) == null;
		assert game.piece(down(game.enPassant())).type() == BlackPawn;
		assert game.piece(down(game.enPassant())) == victim;
		assert !victim.captured();
		assert game.assertVerify();
	}

}
