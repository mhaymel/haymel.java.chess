/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	11.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.algebraic;

import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.fen.GameFromFEN.initalFen;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.fen.GameFromFEN;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.PieceType;

public class MoveFinderTest {

	@Test
	public void findWhiteQueenPromotion() {
		Moves moves = whiteMoves("6k1/4P3/8/8/8/8/8/4K3 w - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e7e8q"), e7, e8, WhiteQueen);
	}

	@Test
	public void findWhiteRookPromotion() {
		Moves moves = whiteMoves("6k1/4P3/8/8/8/8/8/4K3 w - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e7e8r"), e7, e8, WhiteRook);
	}

	@Test
	public void findWhiteBishopPromotion() {
		Moves moves = whiteMoves("6k1/4P3/8/8/8/8/8/4K3 w - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e7e8b"), e7, e8, WhiteBishop);
	}
	
	@Test
	public void findWhiteKnightPromotion() {
		Moves moves = whiteMoves("6k1/4P3/8/8/8/8/8/4K3 w - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e7e8n"), e7, e8, WhiteKnight);
	}

	@Test
	public void findWhiteE2E4() {
		Moves moves = whiteMoves(initalFen);
		MoveFinder find = new MoveFinder(moves);
		test(find.move("e2e4"), e2, e4, pawnDoubleStep);
	}
	
	@Test
	public void findBlackQueenPromotion() {
		Moves moves = blackMoves("6k1/8/8/8/8/8/4p3/6K1 b - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e2e1q"), e2, e1, BlackQueen);
	}

	@Test
	public void findBlackRookPromotion() {
		Moves moves = blackMoves("6k1/8/8/8/8/8/4p3/6K1 b - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e2e1r"), e2, e1, BlackRook);
	}
	
	@Test
	public void findBlackBishopPromotion() {
		Moves moves = blackMoves("6k1/8/8/8/8/8/4p3/6K1 b - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e2e1b"), e2, e1, BlackBishop);
	}

	@Test
	public void findBlackKnightPromotion() {
		Moves moves = blackMoves("6k1/8/8/8/8/8/4p3/6K1 b - - 2 1");
		MoveFinder find = new MoveFinder(moves);
		testPromotion(find.move("e2e1n"), e2, e1, BlackKnight);
	}

	@Test
	public void findWhiteD7E5() {
		Moves moves = blackMoves("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1");
		MoveFinder find = new MoveFinder(moves);
		test(find.move("e7e5"), e7, e5, pawnDoubleStep);
	}
	
	private static void testPromotion(Move move, int from, int to, int promoted) {
		assert valid(from);
		assert valid(to);
		assert PieceType.pieceTypeValid(promoted);
		
		assertThat(move, notNullValue());
		assertThat(move.type(), is(promotion));
		assertThat(move.pieceType(), is(promoted));
		assertThat(move.from(), is(from));
		assertThat(move.to(), is(to));
	}

	private static void test(Move move, int from, int to, MoveType type) {
		assert valid(from);
		assert valid(to);
		
		assertThat(move, notNullValue());
		assertThat(move.type(), is(type));
		assertThat(move.from(), is(from));
		assertThat(move.to(), is(to));
	}
	
	
	private static Moves whiteMoves(String fen) {
		return new GameFromFEN(fen).execute().whiteMoves();
	}

	private static Moves blackMoves(String fen) {
		return new GameFromFEN(fen).execute().blackMoves();
	}

}
