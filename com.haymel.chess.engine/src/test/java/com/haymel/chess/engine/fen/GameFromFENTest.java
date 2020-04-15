/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	12.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.b1;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.b8;
import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c6;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.d1;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.d8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e3;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.f1;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f3;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.f8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.PieceType;
import com.haymel.util.exception.HaymelIllegalArgumentException;

public class GameFromFENTest {

	private Game game;
	
	@Before
	public void setup() {
		game = new Game();
	}
	
	@Test
	public void activeColorWhite() {
		String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 10";
		new GameFromFEN(game, fen).execute();
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void activeColorBlack() {
		String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 10";
		new GameFromFEN(game, fen).execute();
		assertThat(game.activeColor(), is(black));
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void invalidCharacterForActiveColorThrowsException() {
		String fen = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R x KQkq - 0 10";
		new GameFromFEN(game, fen).execute();
		assertThat(game.activeColor(), is(black));
	}

	@Test
	public void startposition() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		new GameFromFEN(game, fen).execute();

		//position
		assertPieces(BlackPawn, a7);
		assertPieces(BlackPawn, b7);
		assertPieces(BlackPawn, c7);
		assertPieces(BlackPawn, d7);
		assertPieces(BlackPawn, e7);
		assertPieces(BlackPawn, f7);
		assertPieces(BlackPawn, g7);
		assertPieces(BlackPawn, h7);
		assertPieces(BlackRook, a8);
		assertPieces(BlackKnight, b8);
		assertPieces(BlackBishop, c8);
		assertPieces(BlackQueen, d8);
		assertPieces(BlackKing, e8);
		assertPieces(BlackBishop, f8);
		assertPieces(BlackKnight, g8);
		assertPieces(BlackRook, h8);

		assertPieces(WhitePawn, a2);
		assertPieces(WhitePawn, b2);
		assertPieces(WhitePawn, c2);
		assertPieces(WhitePawn, d2);
		assertPieces(WhitePawn, e2);
		assertPieces(WhitePawn, f2);
		assertPieces(WhitePawn, g2);
		assertPieces(WhitePawn, h2);
		assertPieces(WhiteRook, a1);
		assertPieces(WhiteKnight, b1);
		assertPieces(WhiteBishop, c1);
		assertPieces(WhiteQueen, d1);
		assertPieces(WhiteKing, e1);
		assertPieces(WhiteBishop, f1);
		assertPieces(WhiteKnight, g1);
		assertPieces(WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(white));
		
		//castling		
		assertThat(game.piece(a1).moved(), is(false));
		assertThat(game.piece(e1).moved(), is(false));
		assertThat(game.piece(h1).moved(), is(false));
		assertThat(game.piece(a8).moved(), is(false));
		assertThat(game.piece(e8).moved(), is(false));
		assertThat(game.piece(h8).moved(), is(false));
		
		//enpassant
		assertThat(game.enPassant(), is(removed));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(1));
	}

	@Test
	public void startposition_e4() {
		String fen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		new GameFromFEN(game, fen).execute();

		//position
		assertPieces(BlackPawn, a7);
		assertPieces(BlackPawn, b7);
		assertPieces(BlackPawn, c7);
		assertPieces(BlackPawn, d7);
		assertPieces(BlackPawn, e7);
		assertPieces(BlackPawn, f7);
		assertPieces(BlackPawn, g7);
		assertPieces(BlackPawn, h7);
		assertPieces(BlackRook, a8);
		assertPieces(BlackKnight, b8);
		assertPieces(BlackBishop, c8);
		assertPieces(BlackQueen, d8);
		assertPieces(BlackKing, e8);
		assertPieces(BlackBishop, f8);
		assertPieces(BlackKnight, g8);
		assertPieces(BlackRook, h8);

		assertPieces(WhitePawn, a2);
		assertPieces(WhitePawn, b2);
		assertPieces(WhitePawn, c2);
		assertPieces(WhitePawn, d2);
		assertPieces(WhitePawn, e4);
		assertPieces(WhitePawn, f2);
		assertPieces(WhitePawn, g2);
		assertPieces(WhitePawn, h2);
		assertPieces(WhiteRook, a1);
		assertPieces(WhiteKnight, b1);
		assertPieces(WhiteBishop, c1);
		assertPieces(WhiteQueen, d1);
		assertPieces(WhiteKing, e1);
		assertPieces(WhiteBishop, f1);
		assertPieces(WhiteKnight, g1);
		assertPieces(WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(black));
		
		//castling		
		assertThat(game.piece(a1).moved(), is(false));
		assertThat(game.piece(e1).moved(), is(false));
		assertThat(game.piece(h1).moved(), is(false));
		assertThat(game.piece(a8).moved(), is(false));
		assertThat(game.piece(e8).moved(), is(false));
		assertThat(game.piece(h8).moved(), is(false));
		
		//enpassant
		assertThat(game.enPassant(), is(e3));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(1));
	}

	@Test
	public void startposition_e4_c5() {
		String fen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
		new GameFromFEN(game, fen).execute();

		//position
		assertPieces(BlackPawn, a7);
		assertPieces(BlackPawn, b7);
		assertPieces(BlackPawn, c5);
		assertPieces(BlackPawn, d7);
		assertPieces(BlackPawn, e7);
		assertPieces(BlackPawn, f7);
		assertPieces(BlackPawn, g7);
		assertPieces(BlackPawn, h7);
		assertPieces(BlackRook, a8);
		assertPieces(BlackKnight, b8);
		assertPieces(BlackBishop, c8);
		assertPieces(BlackQueen, d8);
		assertPieces(BlackKing, e8);
		assertPieces(BlackBishop, f8);
		assertPieces(BlackKnight, g8);
		assertPieces(BlackRook, h8);

		assertPieces(WhitePawn, a2);
		assertPieces(WhitePawn, b2);
		assertPieces(WhitePawn, c2);
		assertPieces(WhitePawn, d2);
		assertPieces(WhitePawn, e4);
		assertPieces(WhitePawn, f2);
		assertPieces(WhitePawn, g2);
		assertPieces(WhitePawn, h2);
		assertPieces(WhiteRook, a1);
		assertPieces(WhiteKnight, b1);
		assertPieces(WhiteBishop, c1);
		assertPieces(WhiteQueen, d1);
		assertPieces(WhiteKing, e1);
		assertPieces(WhiteBishop, f1);
		assertPieces(WhiteKnight, g1);
		assertPieces(WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(white));
		
		//castling		
		assertThat(game.piece(a1).moved(), is(false));
		assertThat(game.piece(e1).moved(), is(false));
		assertThat(game.piece(h1).moved(), is(false));
		assertThat(game.piece(a8).moved(), is(false));
		assertThat(game.piece(e8).moved(), is(false));
		assertThat(game.piece(h8).moved(), is(false));
		
		//enpassant
		assertThat(game.enPassant(), is(c6));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(2));
	}
	
	@Test
	public void startposition_e4_c5_Nf3() {
		String fen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
		new GameFromFEN(game, fen).execute();

		//position
		assertPieces(BlackPawn, a7);
		assertPieces(BlackPawn, b7);
		assertPieces(BlackPawn, c5);
		assertPieces(BlackPawn, d7);
		assertPieces(BlackPawn, e7);
		assertPieces(BlackPawn, f7);
		assertPieces(BlackPawn, g7);
		assertPieces(BlackPawn, h7);
		assertPieces(BlackRook, a8);
		assertPieces(BlackKnight, b8);
		assertPieces(BlackBishop, c8);
		assertPieces(BlackQueen, d8);
		assertPieces(BlackKing, e8);
		assertPieces(BlackBishop, f8);
		assertPieces(BlackKnight, g8);
		assertPieces(BlackRook, h8);

		assertPieces(WhitePawn, a2);
		assertPieces(WhitePawn, b2);
		assertPieces(WhitePawn, c2);
		assertPieces(WhitePawn, d2);
		assertPieces(WhitePawn, e4);
		assertPieces(WhitePawn, f2);
		assertPieces(WhitePawn, g2);
		assertPieces(WhitePawn, h2);
		assertPieces(WhiteRook, a1);
		assertPieces(WhiteKnight, b1);
		assertPieces(WhiteBishop, c1);
		assertPieces(WhiteQueen, d1);
		assertPieces(WhiteKing, e1);
		assertPieces(WhiteBishop, f1);
		assertPieces(WhiteKnight, f3);
		assertPieces(WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(black));
		
		//castling		
		assertThat(game.piece(a1).moved(), is(false));
		assertThat(game.piece(e1).moved(), is(false));
		assertThat(game.piece(h1).moved(), is(false));
		assertThat(game.piece(a8).moved(), is(false));
		assertThat(game.piece(e8).moved(), is(false));
		assertThat(game.piece(h8).moved(), is(false));
		
		//enpassant
		assertThat(game.enPassant(), is(removed));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(1));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(2));
	}
	
	private void assertPieces(PieceType type, int field) {
		assertThat(game.piece(field).type(), is(type));
		
	}
	
}
