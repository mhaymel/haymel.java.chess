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
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteQueenSideCastlingMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white;
		assert Move.type(move) == queensideCastling;
		assert Move.from(move) == e1;
		assert Move.to(move) == c1;
		assert game.piece(e1).type() == WhiteKing;
		assert game.piece(a1).type() == WhiteRook;
		assert game.piece(b1) == null;
		assert game.piece(c1) == null;
		assert game.piece(d1) == null;
		assert game.castlingRight().white().queenside();

		game.whitePositionValue(WhiteKing, e1, c1);
		game.whitePositionValue(WhiteRook, a1, d1);
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
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(e1) == null;
		assert game.piece(a1) == null;
		assert game.piece(c1).type() == WhiteKing;
		assert game.piece(d1).type() == WhiteRook;
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == queensideCastling;
		assert game.piece(e1) == null;
		assert game.piece(a1) == null;
		assert game.piece(c1).type() == WhiteKing;
		assert game.piece(d1).type() == WhiteRook;
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();

		game.activeColorWhite();
		game.decHalfMoveClock();
		Piece king = game.piece(c1);
		Piece rook = game.piece(d1);
		game.clear(c1);
		king.field(e1);
		game.place(king);
		game.clear(d1);
		rook.field(a1);
		game.place(rook);
		game.popCastlingRight();
		game.whitePositionValue(WhiteKing, c1, e1);
		game.whitePositionValue(WhiteRook, d1, a1);
		
		assert game.activeColor() == white;
		assert game.piece(e1).type() == WhiteKing;
		assert game.piece(a1).type() == WhiteRook;
		assert game.piece(b1) == null;
		assert game.piece(c1) == null;
		assert game.piece(d1) == null;
		assert game.castlingRight().white().queenside();
		assert game.assertVerify();
	}

}
