/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.white;

import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class MakeWhiteKingSideCastlingMove {

	public static void make(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert game.activeColor() == white;
		assert Move.type(move) == kingsideCastling;
		assert Move.from(move) == e1;
		assert Move.to(move) == g1;
		assert game.piece(e1).type() == WhiteKing;
		assert game.piece(h1).type() == WhiteRook;
		assert game.piece(f1) == null;
		assert game.piece(g1) == null;
		assert game.castlingRight().white().kingside();

		game.whitePositionValue(WhiteKing, e1, g1);
		game.whitePositionValue(WhiteRook, h1, f1);
		game.pushCastlingRight();
		game.castlingRight().white().disable();
		
		Piece king = game.piece(e1);
		Piece rook = game.piece(h1);
		
		game.clear(e1);
		king.field(g1);
		game.place(king);
		
		game.clear(h1);
		rook.field(f1);
		game.place(rook);
		
		game.push(move);
		game.incHalfMoveClock();
		game.activeColorBlack();

		assert game.activeColor() == black; 
		assert game.piece(e1) == null;
		assert game.piece(h1) == null;
		assert game.piece(g1).type() == WhiteKing;
		assert game.piece(f1).type() == WhiteRook;
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();
		assert game.assertVerify();
	}

	public static void undo(Game game, int move) {
		assert game.assertVerify();
		assert Move.validMove(move);
		assert Move.type(move) == kingsideCastling;
		assert game.piece(e1) == null;
		assert game.piece(h1) == null;
		assert game.piece(g1).type() == WhiteKing;
		assert game.piece(f1).type() == WhiteRook;
		assert !game.castlingRight().white().kingside();
		assert !game.castlingRight().white().queenside();

		game.activeColorWhite();
		game.decHalfMoveClock();
		Piece king = game.piece(g1);
		Piece rook = game.piece(f1);
		
		game.clear(g1);
		king.field(e1);
		game.place(king);
		
		game.clear(f1);
		rook.field(h1);
		game.place(rook);
		game.popCastlingRight();
		game.whitePositionValue(WhiteKing, g1, e1);
		game.whitePositionValue(WhiteRook, f1, h1);
		
		assert game.activeColor() == white;
		assert Move.from(move) == e1;
		assert Move.to(move) == g1;
		assert game.piece(e1).type() == WhiteKing;
		assert game.piece(h1).type() == WhiteRook;
		assert game.piece(f1) == null;
		assert game.piece(g1) == null;
		assert game.castlingRight().white().kingside();
		assert game.assertVerify();
	}

}
