/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class MakeBlackPromotionMoveTest {

	@Test
	public void promotion() {
		test(fromFen("7k/8/7K/8/8/8/pppppppp/8 b - - 30 10"));
	}

	@Test
	public void promotionWithPossibleEnpassant() {
		test(fromFen("7k/8/7K/8/4P3/8/pppppppp/8 b - e3 30 10 "));
	}
	
	private void test(Game game) {
		int count = 0;
		Moves moves = game.moves();
		int size = moves.size();
		for(int i = 0; i < size; i++) {
			Move move = moves.move(i);
			if (move.type() == promotion) {
				count++;
				test(move, game);
			}
		}
		
		assertThat(count, is(8*4));
	}
	
	private void test(Move move, Game game) {
		game.assertVerify();
		assertThat(move.type(), is(promotion));
		
		Piece piece = game.piece(move.from());
		int enPassant = game.enPassant();
		makeMove(move, game);
		
		game.assertVerify();
		assertThat(piece.field(), is(move.to()));
		assertThat(game.piece(move.from()) == null, is(true));
		assertThat(piece.type(), is(move.pieceType()));
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(0));
		assertThat(game.fullMoveNumber(), is(11));
		assertThat(game.enPassant(), is(removed));
		assertThat(game.activeColor(), is(white));
		
		undoMove(game);

		game.assertVerify();
		assertThat(piece.field(), is(move.from()));
		assertThat(game.piece(move.to()) == null, is(true));
		assertThat(piece.blackPawn(), is(true));
		assertThat(game.containsBlackPiece(piece), is(true));
		assertThat(game.halfMoveClock(), is(30));
		assertThat(game.fullMoveNumber(), is(10));
		assertThat(game.enPassant(), is(enPassant));
		assertThat(game.activeColor(), is(black));
	}

	private void undoMove(Game game) {
		new MakeMove(game).undoMove();
	}

	private static void makeMove(Move move, Game game) {
		new MakeMove(game).makeMove(move);
	}

	private static Game fromFen(String fen) {
		return new GameFromFEN(fen).execute();		
	}
	
}
