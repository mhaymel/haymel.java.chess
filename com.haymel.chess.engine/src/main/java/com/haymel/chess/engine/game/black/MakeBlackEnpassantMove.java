/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeBlackEnpassantMove {

	public static void make(Game game, int move) {
		assert Move.validMove(move);
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert Move.type(move) == enpassant;
		assert Move.to(move) == game.enPassant();
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert Field.rank(Move.from(move)) == 3;
		assert game.piece(game.enPassant()) == null;

		Piece victim = game.piece(Field.up(game.enPassant()));
		assert PieceType.white(victim.type());
		assert victim.type() == WhitePawn;
		game.pushVictim(victim);
		
		final int from = Move.from(move);
		Piece piece = game.piece(from);
		final int to = Move.to(move);
		game.blackPositionValue(piece.type(), from, to);
		game.clear(from);
		piece.field(to);
		game.place(piece);
		game.clear(victim.field());
		victim.captured(true);
		game.removeWhite(victim);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();
		game.resetEnPassant();

		assert game.enPassant() == removed;
		assert game.activeColor() == white; 
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Move.to(move)) == piece;
		assert victim.captured();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == enpassant;
		assert Move.to(move) == game.enPassant();
		assert game.activeColor() == white; 
		assert game.piece(Move.to(move)).type() == BlackPawn;
		assert game.piece(Move.from(move)) == null;
		assert game.piece(Field.up(game.enPassant())) == null;
		assert game.assertVerify();
		
		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		final int to = Move.to(move);
		Piece piece = game.piece(to);
		game.clear(to);
		final int from = Move.from(move);
		piece.field(from);
		game.place(piece);
		Piece victim = game.popVictim();
		
		assert PieceType.white(victim.type());
		assert victim.type() == WhitePawn;
		assert victim.captured();
		
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.blackPositionValue(piece.type(), to, from);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert Move.to(move) == game.enPassant();
		assert game.piece(Move.from(move)).type() == BlackPawn;
		assert Field.rank(Move.from(move)) == 3;
		assert game.piece(game.enPassant()) == null;
		assert game.piece(Field.up(game.enPassant())).type() == WhitePawn;
		assert game.piece(Field.up(game.enPassant())) == victim;
		assert game.assertVerify();
	}

}
