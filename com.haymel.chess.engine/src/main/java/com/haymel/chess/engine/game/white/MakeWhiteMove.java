/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.normal;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == normal;
		assert game.piece(move.from()).white();
		
		Piece piece = game.piece(move.from());
		boolean moved = piece.moved();
		game.clear(move.from());
		piece.field(move.to());
		piece.setMoved(true);
		game.place(piece);
		
		game.push(move, moved);

		if (piece.whitePawn() && move.to() - move.from() == Field.up*2)
			game.enPassant(up(move.from()));

		if (piece.whitePawn() && enpassant(move))
			game.clear(move.capturedPiece().field());
		
		if (move.capturedPiece() != null)
			game.removeBlack(move.capturedPiece());	
			
		if (piece.whitePawn() || move.capturedPiece() != null)
			game.resetHalfMoveClock();
		else
			game.incHalfMoveClock();
		
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(move.from()) == null;
		assert game.piece(move.to()).white();
		assert game.piece(move.to()) == piece;
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move, boolean moved) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white; 
		assert move.type() == normal;
		assert game.piece(move.to()).white();
		assert game.piece(move.from()) == null;

		Piece piece = game.piece(move.to());
		game.clear(move.to());
		piece.field(move.from());
		piece.setMoved(moved);
		game.place(piece);
	
		if (move.capturedPiece() != null) {
			game.addBlack(move.capturedPiece());
			game.place(move.capturedPiece());
		}
		
		assert game.halfMoveClock() >= 0;
		assert game.activeColor() == white; 
		assert game.piece(move.from()).white();
		assert game.assertVerify();
	}

	private static boolean enpassant(Move move) {
		return move.capturedPiece() != null && down(move.to()) == move.capturedPiece().field(); 
	}

}
