/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;
import static java.lang.String.join;

import java.util.List;

import com.haymel.chess.engine.game.Position;
import com.haymel.chess.engine.piece.Piece;

//	https://en.wikipedia.org/wiki/Forsyth-Edwards_Notation

public class PositionFromFEN {
	
	public static final String initalFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private static final int numberOfFenFields = 6;
	
	private final Position position;
	private final String[] fields;

	public static final Position positionFromInitialFen() {
		return new PositionFromFEN(initalFen).value();
	}
	
	public PositionFromFEN(String fen) {
		this.position = new Position();
		this.fields = split(nonEmpty(fen, "fen"));
	}

	public Position value() {
		handlePiecePlacement(fields[0]);
		handleActiveColor(fields[1]);
		handleCastling(fields[2]);
		handleEnpassant(fields[3]);
		handleHalfmoveClock(fields[4]);
		handleFullmoveNumber(fields[5]);
		position.verify();
		return position;
	}

	private void handleFullmoveNumber(String fullmoveNumber) {
		new FullmoveNumber(position, fullmoveNumber).execute();
	}

	private void handleHalfmoveClock(String halfmoveClock) {
		new HalfmoveClock(position, halfmoveClock).execute();
	}

	private void handleEnpassant(String field) {
		new Enpassant(position, field).execute();
	}

	private void handleCastling(String castling) {
		new Castling(position.castlingRight(), castling).execute();
	}

	private void handleActiveColor(String activeColor) {
		if ("w".equals(activeColor))
			position.activeColorWhite();
		else if ("b".equals(activeColor))
			position.activeColorBlack();
		else
			throwIAE("illegal character for active color '%s' in fen '%s'", activeColor, join(" ", fields));
	}

	private void handlePiecePlacement(String piecePlacement) {
		List<Piece> pieces = new PiecePlacement(piecePlacement).value();
		for (Piece piece : pieces)
			position.board()[piece.field()] = piece;
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
