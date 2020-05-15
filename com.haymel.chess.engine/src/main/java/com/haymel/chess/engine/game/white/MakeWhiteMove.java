/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.moves.MoveType.normalRookMove;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class MakeWhiteMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();

		doMake(game, move);
		
		assert game.assertVerify();
	}
	
	static void doMake(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.activeColor() == white; 
		assert move.type() == normal || move.type() == normalKingMove || move.type() == normalRookMove;
		assert game.piece(move.from()).white();
		assert game.piece(move.from()).type() != WhitePawn;
		assert game.piece(move.to()) == null;
		
		Piece piece = game.piece(move.from());
		game.clear(move.from());
		piece.field(move.to());
		game.place(piece);
		
		game.push(move);
		
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
	}

	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		
		doUndo(game, move);
		
		assert game.assertVerify();
	}
	
	static void doUndo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.activeColor() == white; 
		assert move.type() == normal || move.type() == normalKingMove || move.type() == normalRookMove;
		assert game.piece(move.to()).white();
		assert game.piece(move.to()).type() != PieceType.WhitePawn;
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		game.place(piece);
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(move.to()) == null;
		assert game.piece(move.from()).white();
	}

}
