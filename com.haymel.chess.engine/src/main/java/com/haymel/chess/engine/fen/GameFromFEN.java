/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;
import static java.lang.String.join;

import java.util.List;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.castling.PositionCastlingRight;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.Piece;

//	https://en.wikipedia.org/wiki/Forsyth-Edwards_Notation

// rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR    w    KQkq   -   0   1
// r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -

public class GameFromFEN {
	
	public static final String initalFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private static final int numberOfFenFields = 6;
	
	private final Game game;
	private final String[] fields;

	public static final Game gameFromInitialFen() {
		return new GameFromFEN(initalFen).execute();
	}
	
	public GameFromFEN(String fen) {
		this.game = new Game();
		this.fields = split(nonEmpty(fen, "fen"));
	}

	public Game execute() {
		handlePiecePlacement(fields[0]);
		handleActiveColor(fields[1]);
		handleCastling(fields[2]);
		handleEnpassant(fields[3]);
		handleHalfmoveClock(fields[4]);
		handleFullmoveNumber(fields[5]);
		verifyCastling();
		return game;
	}
	
	private void verifyCastling() {
		PositionCastlingRight castling = game.castlingRight();
		if (castling.white().kingside() && (!whiteKing(e1)|| !whiteRook(h1)))
			throwIAE("White kingside castling is not possible in FEN '%s'", join(" ", fields));
		if (castling.white().queenside() && (!whiteKing(e1) || !whiteRook(a1)))
			throwIAE("White queenside castling is not possible in FEN '%s'", join(" ", fields));
		if (castling.black().kingside() && (!blackKing(e8) || !blackRook(h8)))
			throwIAE("Black kingside castling is not possible in FEN '%s'", join(" ", fields));
		if (castling.black().queenside() && (!blackKing(e8) || !blackRook(a8)))
			throwIAE("Black queenside castling is not possible in FEN '%s'", join(" ", fields));
	}
	
	private boolean whiteKing(int field) {
		assert Field.valid(field);
		return !free(field) && game.piece(field).type() == WhiteKing;
	}
	
	private boolean whiteRook(int field) {
		assert Field.valid(field);
		return !free(field) && game.piece(field).whiteRook();
	}
	
	private boolean blackKing(int field) {
		assert Field.valid(field);
		return !free(field) && game.piece(field).type() == BlackKing;
	}
	
	private boolean blackRook(int field) {
		assert Field.valid(field);
		return !free(field) && game.piece(field).blackRook();
	}
	
	private boolean free(int field) {
		assert Field.valid(field);
		return game.piece(field) == null;
	}

	private void handleFullmoveNumber(String fullmoveNumber) {
		new FullmoveNumber(game, fullmoveNumber).execute();
	}

	private void handleHalfmoveClock(String halfmoveClock) {
		new HalfmoveClock(game, halfmoveClock).execute();
	}

	private void handleEnpassant(String field) {
		new Enpassant(game, field).execute();
	}

	private void handleCastling(String castling) {
		new Castling(game.castlingRight(), castling).execute();
	}

	private void handleActiveColor(String activeColor) {
		if ("w".equals(activeColor))
			game.activeColorWhite();
		else if ("b".equals(activeColor))
			game.activeColorBlack();
		else
			throwIAE("illegal character for active color '%s' in fen '%s'", activeColor, join(" ", fields));
	}

	private void handlePiecePlacement(String piecePlacement) {
		List<Piece> pieces = new PiecePlacement(piecePlacement).value();
		for (Piece piece : pieces) {
			if (piece.white())
				game.addWhite(piece);
			else
				game.addBlack(piece);
			
			game.place(piece);
		}
	}

	private static String[] split(String fen) {
		String trimmed = fen.trim();
		if (trimmed.isEmpty())
			return throwIAE("fen must not be empty");
		
		String[] splitted = trimmed.split("\\s+");
		if (splitted.length != numberOfFenFields)
			return throwIAE("fen must contain exactly 6 fields but has %s in '%s'", splitted.length, fen);
		
		return splitted;
	}
	
}
