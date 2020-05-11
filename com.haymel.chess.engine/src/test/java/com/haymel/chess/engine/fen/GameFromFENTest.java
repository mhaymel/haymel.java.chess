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

import org.junit.Test;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.PieceType;
import com.haymel.util.exception.HaymelIllegalArgumentException;

public class GameFromFENTest {

	@Test
	public void activeColorWhite() {
		Game game = from("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 10");
		assertThat(game.activeColor(), is(white));
	}
	
	@Test
	public void activeColorBlack() {
		Game game = from("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R b KQkq - 0 10");
		assertThat(game.activeColor(), is(black));
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void invalidCharacterForActiveColorThrowsException() {
		Game game = from("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R x KQkq - 0 10");
		assertThat(game.activeColor(), is(black));
	}

	@Test
	public void startposition() {
		Game game = from("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

		//position
		assertPieces(game, BlackPawn, a7);
		assertPieces(game, BlackPawn, b7);
		assertPieces(game, BlackPawn, c7);
		assertPieces(game, BlackPawn, d7);
		assertPieces(game, BlackPawn, e7);
		assertPieces(game, BlackPawn, f7);
		assertPieces(game, BlackPawn, g7);
		assertPieces(game, BlackPawn, h7);
		assertPieces(game, BlackRook, a8);
		assertPieces(game, BlackKnight, b8);
		assertPieces(game, BlackBishop, c8);
		assertPieces(game, BlackQueen, d8);
		assertPieces(game, BlackKing, e8);
		assertPieces(game, BlackBishop, f8);
		assertPieces(game, BlackKnight, g8);
		assertPieces(game, BlackRook, h8);

		assertPieces(game, WhitePawn, a2);
		assertPieces(game, WhitePawn, b2);
		assertPieces(game, WhitePawn, c2);
		assertPieces(game, WhitePawn, d2);
		assertPieces(game, WhitePawn, e2);
		assertPieces(game, WhitePawn, f2);
		assertPieces(game, WhitePawn, g2);
		assertPieces(game, WhitePawn, h2);
		assertPieces(game, WhiteRook, a1);
		assertPieces(game, WhiteKnight, b1);
		assertPieces(game, WhiteBishop, c1);
		assertPieces(game, WhiteQueen, d1);
		assertPieces(game, WhiteKing, e1);
		assertPieces(game, WhiteBishop, f1);
		assertPieces(game, WhiteKnight, g1);
		assertPieces(game, WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(white));
		
		//castling		
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		//enpassant
		assertThat(game.enPassant(), is(removed));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(1));
	}

	@Test
	public void startposition_e4() {
		Game game = from("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");

		//position
		assertPieces(game, BlackPawn, a7);
		assertPieces(game, BlackPawn, b7);
		assertPieces(game, BlackPawn, c7);
		assertPieces(game, BlackPawn, d7);
		assertPieces(game, BlackPawn, e7);
		assertPieces(game, BlackPawn, f7);
		assertPieces(game, BlackPawn, g7);
		assertPieces(game, BlackPawn, h7);
		assertPieces(game, BlackRook, a8);
		assertPieces(game, BlackKnight, b8);
		assertPieces(game, BlackBishop, c8);
		assertPieces(game, BlackQueen, d8);
		assertPieces(game, BlackKing, e8);
		assertPieces(game, BlackBishop, f8);
		assertPieces(game, BlackKnight, g8);
		assertPieces(game, BlackRook, h8);

		assertPieces(game, WhitePawn, a2);
		assertPieces(game, WhitePawn, b2);
		assertPieces(game, WhitePawn, c2);
		assertPieces(game, WhitePawn, d2);
		assertPieces(game, WhitePawn, e4);
		assertPieces(game, WhitePawn, f2);
		assertPieces(game, WhitePawn, g2);
		assertPieces(game, WhitePawn, h2);
		assertPieces(game, WhiteRook, a1);
		assertPieces(game, WhiteKnight, b1);
		assertPieces(game, WhiteBishop, c1);
		assertPieces(game, WhiteQueen, d1);
		assertPieces(game, WhiteKing, e1);
		assertPieces(game, WhiteBishop, f1);
		assertPieces(game, WhiteKnight, g1);
		assertPieces(game, WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(black));
		
		//castling		
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		//enpassant
		assertThat(game.enPassant(), is(e3));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(1));
	}

	@Test
	public void startposition_e4_c5() {
		Game game = from("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");

		//position
		assertPieces(game, BlackPawn, a7);
		assertPieces(game, BlackPawn, b7);
		assertPieces(game, BlackPawn, c5);
		assertPieces(game, BlackPawn, d7);
		assertPieces(game, BlackPawn, e7);
		assertPieces(game, BlackPawn, f7);
		assertPieces(game, BlackPawn, g7);
		assertPieces(game, BlackPawn, h7);
		assertPieces(game, BlackRook, a8);
		assertPieces(game, BlackKnight, b8);
		assertPieces(game, BlackBishop, c8);
		assertPieces(game, BlackQueen, d8);
		assertPieces(game, BlackKing, e8);
		assertPieces(game, BlackBishop, f8);
		assertPieces(game, BlackKnight, g8);
		assertPieces(game, BlackRook, h8);

		assertPieces(game, WhitePawn, a2);
		assertPieces(game, WhitePawn, b2);
		assertPieces(game, WhitePawn, c2);
		assertPieces(game, WhitePawn, d2);
		assertPieces(game, WhitePawn, e4);
		assertPieces(game, WhitePawn, f2);
		assertPieces(game, WhitePawn, g2);
		assertPieces(game, WhitePawn, h2);
		assertPieces(game, WhiteRook, a1);
		assertPieces(game, WhiteKnight, b1);
		assertPieces(game, WhiteBishop, c1);
		assertPieces(game, WhiteQueen, d1);
		assertPieces(game, WhiteKing, e1);
		assertPieces(game, WhiteBishop, f1);
		assertPieces(game, WhiteKnight, g1);
		assertPieces(game, WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(white));
		
		//castling		
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		//enpassant
		assertThat(game.enPassant(), is(c6));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(0));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(2));
	}
	
	@Test
	public void startposition_e4_c5_Nf3() {
		Game game = from("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");

		//position
		assertPieces(game, BlackPawn, a7);
		assertPieces(game, BlackPawn, b7);
		assertPieces(game, BlackPawn, c5);
		assertPieces(game, BlackPawn, d7);
		assertPieces(game, BlackPawn, e7);
		assertPieces(game, BlackPawn, f7);
		assertPieces(game, BlackPawn, g7);
		assertPieces(game, BlackPawn, h7);
		assertPieces(game, BlackRook, a8);
		assertPieces(game, BlackKnight, b8);
		assertPieces(game, BlackBishop, c8);
		assertPieces(game, BlackQueen, d8);
		assertPieces(game, BlackKing, e8);
		assertPieces(game, BlackBishop, f8);
		assertPieces(game, BlackKnight, g8);
		assertPieces(game, BlackRook, h8);

		assertPieces(game, WhitePawn, a2);
		assertPieces(game, WhitePawn, b2);
		assertPieces(game, WhitePawn, c2);
		assertPieces(game, WhitePawn, d2);
		assertPieces(game, WhitePawn, e4);
		assertPieces(game, WhitePawn, f2);
		assertPieces(game, WhitePawn, g2);
		assertPieces(game, WhitePawn, h2);
		assertPieces(game, WhiteRook, a1);
		assertPieces(game, WhiteKnight, b1);
		assertPieces(game, WhiteBishop, c1);
		assertPieces(game, WhiteQueen, d1);
		assertPieces(game, WhiteKing, e1);
		assertPieces(game, WhiteBishop, f1);
		assertPieces(game, WhiteKnight, f3);
		assertPieces(game, WhiteRook, h1);
		
		//active color
		assertThat(game.activeColor(), is(black));
		
		//castling		
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
		
		//enpassant
		assertThat(game.enPassant(), is(removed));
		
		//halfmove clock
		assertThat(game.halfMoveClock(), is(1));
		
		//fullmove number
		assertThat(game.fullMoveNumber(), is(2));
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void invalidWhiteKingsideCastling() {
		from("2k5/8/8/8/8/8/8/4RRK1 w K - 0 1");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void invalidWhiteQueensideCastling() {
		from("2k5/8/8/8/8/8/8/4RRK1 w Q - 0 1");
	}
	
	@Test(expected=HaymelIllegalArgumentException.class)
	public void invalidBlackKingsideCastling() {
		from("2k5/8/8/8/8/8/8/4RRK1 w k - 0 1");
	}

	@Test(expected=HaymelIllegalArgumentException.class)
	public void invalidBlackQueensideCastling() {
		from("2k5/8/8/8/8/8/8/4RRK1 w q - 0 1");
	}

	@Test
	public void whiteKingsideCastling() {
		Game game = from("r3k2r/8/8/8/8/8/8/R3K2R w K - 1 2 ");
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}
	
	@Test
	public void whiteQueensideCastling() {
		Game game = from("r3k2r/8/8/8/8/8/8/R3K2R w Q - 1 2 ");
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}

	@Test
	public void blackKingsideCastling() {
		Game game = from("r3k2r/8/8/8/8/8/8/R3K2R w k - 1 2 ");
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(false));
	}

	@Test
	public void blackQueensideCastling() {
		Game game = from("r3k2r/8/8/8/8/8/8/R3K2R w q - 1 2 ");
		assertThat(game.castlingRight().white().kingside(), is(false));
		assertThat(game.castlingRight().white().queenside(), is(false));
		assertThat(game.castlingRight().black().kingside(), is(false));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	@Test
	public void castling() {
		Game game = from("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 1 2 ");
		assertThat(game.castlingRight().white().kingside(), is(true));
		assertThat(game.castlingRight().white().queenside(), is(true));
		assertThat(game.castlingRight().black().kingside(), is(true));
		assertThat(game.castlingRight().black().queenside(), is(true));
	}

	private void assertPieces(Game game, int type, int field) {
		assert Field.valid(field);
		assert PieceType.pieceTypeValid(type);
		
		assertThat(game.piece(field).type(), is(type));
	}
	
	private static Game from(String fen) {
		return new GameFromFEN(fen).execute();
	}

}
