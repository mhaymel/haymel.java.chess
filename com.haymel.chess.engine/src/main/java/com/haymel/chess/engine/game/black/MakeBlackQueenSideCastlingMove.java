/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeBlackQueenSideCastlingMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == black;
		assert move.from() == e8;
		assert move.to() == c8;
		assert game.piece(e8).type() == BlackKing;
		assert game.piece(a8).type() == BlackRook;
		assert game.piece(b8) == null;
		assert game.piece(c8) == null;
		assert game.piece(d8) == null;
		assert game.castlingRight().black().queenside();

		game.blackPositionValue(BlackKing, e8, c8);
		game.blackPositionValue(BlackRook, a8, d8);
		game.pushCastlingRight();
		game.castlingRight().black().disable();

		Piece king = game.piece(e8);
		Piece rook = game.piece(a8);
		
		game.clear(e8);
		king.field(c8);
		game.place(king);
		
		game.clear(a8);
		rook.field(d8);
		game.place(rook);
		
		game.push(move);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.incFullMoveNumber();
		game.activeColorWhite();

		assert game.activeColor() == white; 
		assert game.piece(e8) == null;
		assert game.piece(a8) == null;
		assert game.piece(c8).type() == BlackKing;
		assert game.piece(d8).type() == BlackRook;
		assert !game.castlingRight().black().queenside();
		assert !game.castlingRight().black().kingside();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(e8) == null;
		assert game.piece(a8) == null;
		assert game.piece(c8).type() == BlackKing;
		assert game.piece(d8).type() == BlackRook;
		assert !game.castlingRight().black().queenside();
		assert !game.castlingRight().black().kingside();

		game.activeColorBlack();
		Piece king = game.piece(c8);
		Piece rook = game.piece(d8);
		game.clear(c8);
		king.field(e8);
		game.place(king);
		game.clear(d8);
		rook.field(a8);
		game.place(rook);
		game.popCastlingRight();
		game.blackPositionValue(BlackKing, c8, e8);
		game.blackPositionValue(BlackRook, d8, a8);
		
		assert game.activeColor() == black;
		assert game.piece(e8).type() == BlackKing;
		assert game.piece(a8).type() == BlackRook;
		assert game.piece(b8) == null;
		assert game.piece(c8) == null;
		assert game.piece(d8) == null;
		assert game.castlingRight().black().queenside();
		assert game.assertVerify();
	}

}
