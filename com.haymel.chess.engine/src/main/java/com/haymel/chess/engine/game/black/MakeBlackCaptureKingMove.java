/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;

public final class MakeBlackCaptureKingMove {

	public static void make(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == captureKingMove;
		assert game.piece(move.from()).type() == BlackKing;
		
		game.pushCastlingRight();
		game.castlingRight().black().disable();
		switch(move.to()) {
		case a1: game.castlingRight().white().disableQueenside(); break;
		case h1: game.castlingRight().white().disableKingside(); break;
		}
		MakeBlackCaptureMove.doMake(game, move);

		assert game.assertVerify();
	}
	
	public static void undo(Game game, Move move) {
		assert game.assertVerify();
		assert move.type() == captureKingMove;
		assert game.piece(move.to()).type() == BlackKing;

		MakeBlackCaptureMove.doUndo(game, move);
		game.popCastlingRight();

		assert game.piece(move.from()).type() == BlackKing;
		assert game.assertVerify();
	}

}
