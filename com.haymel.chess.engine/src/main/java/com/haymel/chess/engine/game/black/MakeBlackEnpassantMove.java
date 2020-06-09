/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	03.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

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

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert game.activeColor() == black; 
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).type() == BlackPawn;
		assert Field.rank(move.from()) == 3;
		assert game.piece(game.enPassant()) == null;

		Piece victim = game.piece(Field.up(game.enPassant()));
		assert PieceType.white(victim.type());
		assert victim.type() == WhitePawn;
		game.pushVictim(victim);
		
		Piece piece = game.piece(move.from());
		game.blackPositionValue(piece.type(), move.from(), move.to());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		game.clear(victim.field());
		victim.captured(true);
		game.removeWhite(victim);
		game.push(move);
		game.pushHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()) == piece;
		assert victim.captured();
		assert game.halfMoveClock() == 0;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == enpassant;
		assert move.to() == game.enPassant();
		assert game.activeColor() == white; 
		assert game.piece(move.to()).type() == BlackPawn;
		assert game.piece(move.from()) == null;
		assert game.piece(Field.up(game.enPassant())) == null;
		assert game.assertVerify();
		
		game.decFullMoveNumber();
		game.activeColorBlack();
		game.popHalfMoveClock();
		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		Piece victim = game.popVictim();
		
		assert PieceType.white(victim.type());
		assert victim.type() == WhitePawn;
		assert victim.captured();
		
		victim.captured(false);
		game.addWhite(victim);
		game.place(victim);
		game.blackPositionValue(piece.type(), move.to(), move.from());
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == black; 
		assert move.to() == game.enPassant();
		assert game.piece(move.from()).type() == BlackPawn;
		assert Field.rank(move.from()) == 3;
		assert game.piece(game.enPassant()) == null;
		assert game.piece(Field.up(game.enPassant())).type() == WhitePawn;
		assert game.piece(Field.up(game.enPassant())) == victim;
		assert game.assertVerify();
	}

}
