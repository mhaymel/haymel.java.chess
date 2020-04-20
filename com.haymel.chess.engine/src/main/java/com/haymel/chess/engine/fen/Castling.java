/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.02.2020
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
import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelException.throwHE;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.piece.Piece;

class Castling {

	private final Game game;
	private final String castling; 
	
	Castling(Game game, String castling) {
		this.game = nonNull(game, "game");
		this.castling = verify(castling);
	}
	
	void execute() {
		setWhiteKingMoved();
		setWhiteRookMoved(a1);
		setWhiteRookMoved(h1);

		setBlackKingMoved();
		setBlackRookMoved(a8);
		setBlackRookMoved(h8);
	
		int len = castling.length();
		for(int i = 0; i < len; i++) 
			handle(castling.charAt(i));
	}

	private void handle(char c) {
		switch (c) {
		case 'K': 
			whiteKingsideCastling();
			break;

		case 'Q': 
			whiteQueensideCastling();
			break;

		case 'k': 
			blackKingsideCastling();
			break;
			
		case 'q': 
			blackQueensideCastling();
			break;

		case '-': 
			break;
			
		default:
			throwIAE("Illegal character '%s' in castling availability '%s'", c, castling);
			break;
		}
	}

	private void whiteKingsideCastling() {
		whiteKing();
		whiteRook(h1);
	}

	private void whiteQueensideCastling() {
		whiteKing();
		whiteRook(a1);
	}
	
	private void whiteKing() {
		Piece piece = game.piece(e1);
		if (!piece.whiteKing()) 
			throwHE("Piece on field e1 must be a white king but is %s", piece);
		piece.setMoved(false);
	}

	private void whiteRook(int field) {
		Piece piece = game.piece(field);
		if (!piece.whiteRook()) 
			throwHE("Piece on field %s must be a white rook but is %s", field, piece);
		piece.setMoved(false);
	}
	
	private void blackKingsideCastling() {
		blackKing();
		blackRook(h8);
	}

	private void blackQueensideCastling() {
		blackKing();
		blackRook(a8);
	}

	private void blackKing() {
		Piece piece = game.piece(e8);
		if (!piece.blackKing()) 
			throwHE("Piece on field e8 must be a black king but is %s", piece);
		piece.setMoved(false);
	}

	private void blackRook(int field) {
		Piece piece = game.piece(field);
		if (!piece.blackRook()) 
			throwHE("Piece on field %s must be a white rook but is %s", field, piece);
		piece.setMoved(false);
	}
	
	private static String verify(String castling) {
		nonEmpty(castling, "castling");
		if (castling.length() > 4)
			return throwIAE("castling availability in FEN must have a length between 1 and 4 but is '%s'", castling);
		
		return castling;
	}
	
	private void setWhiteKingMoved() {
		Piece piece = game.piece(e1);
		if (piece != null && piece.whiteKing()) 
			piece.setMoved(true);
	}

	private void setWhiteRookMoved(int field) {
		Piece piece = game.piece(field);
		if (piece != null && piece.whiteRook()) 
			piece.setMoved(true);
	}

	private void setBlackKingMoved() {
		Piece piece = game.piece(e8);
		if (piece != null && piece.blackKing()) 
			piece.setMoved(true);
	}

	private void setBlackRookMoved(int field) {
		Piece piece = game.piece(field);
		if (piece != null && piece.blackRook()) 
			piece.setMoved(true);
	}
	
}
