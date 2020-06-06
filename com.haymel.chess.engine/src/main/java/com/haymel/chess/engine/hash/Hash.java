/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	05.06.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.hash;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.up;
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
import static com.haymel.chess.engine.piece.PieceType.pieceTypeValid;

import java.util.Random;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.ActiveColor;

public final class Hash {											//TODO unit test

	private final long activeColorWhite;
	private final long activeColorBlack;

	private final long whiteKingsideCastling;
	private final long whiteQueensideCastling;
	private final long blackKingsideCastling;
	private final long blackQueensideCastling;
	
	private final long[] whitePawn = new long[up*up];
	private final long[] whiteRook = new long[up*up];
	private final long[] whiteKnight = new long[up*up];
	private final long[] whiteBishop = new long[up*up];
	private final long[] whiteQueen = new long[up*up];
	private final long[] whiteKing = new long[up*up];

	private final long[] blackPawn = new long[up*up];
	private final long[] blackRook = new long[up*up];
	private final long[] blackKnight = new long[up*up];
	private final long[] blackBishop = new long[up*up];
	private final long[] blackQueen = new long[up*up];
	private final long[] blackKing = new long[up*up];

	private final long[] enpassant = new long[up*up + 1];
	
	public Hash() {
		Random rand = new Random();
		
		activeColorWhite = rand.nextLong();
		activeColorBlack = rand.nextLong();

		whiteKingsideCastling = rand.nextLong();
		whiteQueensideCastling = rand.nextLong();
		blackKingsideCastling = rand.nextLong();
		blackQueensideCastling = rand.nextLong();
		
		init(whitePawn, rand);
		init(whiteRook, rand);
		init(whiteKnight, rand);
		init(whiteBishop, rand);
		init(whiteQueen, rand);
		init(whiteKing, rand);
		
		init(blackPawn, rand);
		init(blackRook, rand);
		init(blackKnight, rand);
		init(blackBishop, rand);
		init(blackQueen, rand);
		init(blackKing, rand);

		init(enpassant, rand);
	}

	private static void init(long[] data, Random rand) {
		for(int i = 0; i < data.length; i++)
			data[i] = rand.nextLong();
	}
	
	public long piece(int type, int field) {
		assert pieceTypeValid(type);
		assert Field.valid(field);
		
		switch(type) {
		case WhitePawn:		return whitePawn[field];
		case WhiteRook:		return whiteRook[field];
		case WhiteKnight:	return whiteKnight[field];
		case WhiteBishop:	return whiteBishop[field];
		case WhiteQueen:	return whiteQueen[field];
	    case WhiteKing:		return whiteKing[field];
        case BlackPawn:		return blackPawn[field];
        case BlackRook:		return blackRook[field];
        case BlackKnight:	return blackKnight[field];
        case BlackBishop:	return blackBishop[field];
        case BlackQueen:	return blackQueen[field];
        case BlackKing:		return blackKing[field];
        default:
        	assert false;
        	return 0;
		}
	}
	
	public long activeColor(int activeColor) {
		switch (activeColor) {
		case ActiveColor.white: return activeColorWhite;
		case ActiveColor.black: return activeColorBlack;
		default:
        	assert false;
        	return 0;
		}
	}

	public long enpassant(int field) {
		assert Field.valid(field);
		assert rank(field) == rank(a3) || rank(field) == rank(a6) || field == removed : fieldAsString(field);
		
		return enpassant[field];
	}

	public long whiteKingsideCastling() {
		return whiteKingsideCastling;
	}

	public long whiteQueensideCastling() {
		return whiteQueensideCastling;
	}

	public long blackKingsideCastling() {
		return blackKingsideCastling;
	}

	public long blackQueensideCastling() {
		return blackQueensideCastling;
	}
	
}
