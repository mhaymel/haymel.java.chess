/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	29.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteQueenSideCastlingMove {

	public static void make(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.activeColor() == white;
		assert move.from() == e1;
		assert move.to() == c1;
		assert game.piece(e1).whiteKing();
		assert game.piece(a1).whiteRook();
		assert game.piece(b1) == null;
		assert game.piece(c1) == null;
		assert game.piece(d1) == null;
		assert game.castlingRight().white().queenside();

		game.pushCastlingRight();
		game.castlingRight().white().disable();

		Piece king = game.piece(e1);
		Piece rook = game.piece(a1);
		
		game.clear(e1);
		king.field(c1);
		game.place(king);
		
		game.clear(a1);
		rook.field(d1);
		game.place(rook);
		
		game.push(move);
		
		game.enPassant(removed);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(e1) == null;
		assert game.piece(a1) == null;
		assert game.piece(c1).whiteKing();
		assert game.piece(d1).whiteRook();
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();
		assert game.assertVerify();
	}

	public static void undo(Game game, Move move) {
		assert game != null;
		assert move != null;
		assert game.assertVerify();
		assert game.piece(e1) == null;
		assert game.piece(a1) == null;
		assert game.piece(c1).whiteKing();
		assert game.piece(d1).whiteRook();
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();

		Piece king = game.piece(c1);
		Piece rook = game.piece(d1);
		
		game.clear(c1);
		king.field(e1);
		game.place(king);
		
		game.clear(d1);
		rook.field(a1);
		game.place(rook);
		game.popCastlingRight();
		
		assert game.activeColor() == white;
		assert game.piece(e1).whiteKing();
		assert game.piece(a1).whiteRook();
		assert game.piece(b1) == null;
		assert game.piece(c1) == null;
		assert game.piece(d1) == null;
		assert game.castlingRight().white().queenside();
		assert game.assertVerify();
	}

}
