/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.search.PieceValue.pieceValue;
import static java.lang.Integer.MAX_VALUE;

import java.util.Arrays;
import java.util.Comparator;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.MoveType;
import com.haymel.chess.engine.piece.PieceType;

public class SortWhiteMoves implements Comparator<Move> {		//TODO refactor, unit test

	private final static int VALUE_PV = 0;
	private final static int VALUE_CAPTURE = 10_000;
	private final static int VALUE_DEFAULT = MAX_VALUE;
	
	private final static int VALUE_PAWN_e_d = VALUE_DEFAULT - 10;
	private final static int VALUE_KNIGHT_BISHOP = VALUE_DEFAULT - 8;
	private final static int VALUE_ROOK = VALUE_DEFAULT - 7;
	private final static int VALUE_PAWN = VALUE_DEFAULT - 6;
	private final static int VALUE_QUEEN = VALUE_DEFAULT - 5;
	
	private final Game game;
	private final Move[] moves;
	private final Move pv;
	
	public SortWhiteMoves(Game game, Move[] moves, int size, Move pv) {
		assert game != null;
		assert moves != null;
		assert size >= 0 && size <= moves.length;
		
		this.game = game;
		this.moves = new Move[size];
		this.pv = pv;	// pv can be null
		
		System.arraycopy(moves, 0, this.moves, 0, size);
	}
	
	public Move[] sort() {
		Arrays.sort(moves, this);
		return moves;
	}

	private int value(Move move) {
		if (pv != null && pv.from() == move.from() && pv.to() == move.to())
			return VALUE_PV;
		
		if (isCapture(move))
			return VALUE_CAPTURE - pieceValue(move.capturedPiece().type());
		
		PieceType type = game.piece(move.from()).type();
		switch(type) {
		case WhitePawn:
		case BlackPawn:
			if (move.from().file() == Field.e1.file() || move.from().file() == Field.d1.file())
				return VALUE_PAWN_e_d - doubleMove(move);
			return VALUE_PAWN - doubleMove(move);
		
		case WhiteBishop:
		case BlackBishop:
		case WhiteKnight:
		case BlackKnight:
			return VALUE_KNIGHT_BISHOP;
		
		case WhiteRook:
		case BlackRook:
			return VALUE_ROOK;
			
			
		case WhiteQueen:
		case BlackQueen:
			return VALUE_QUEEN;

		default:
			return VALUE_DEFAULT;
		}
		
	}
	
	private int doubleMove(Move move) {
		return Math.abs(move.to().rank() - move.from().rank());
	}

	private static boolean isCapture(Move move) {
		return  
			move.type() == MoveType.capture ||
			move.type() == MoveType.enpassant ||
			move.type() == MoveType.capturePromotion;
	}

	@Override
	public int compare(Move a, Move b) {
		return value(a) - value(b);
	}
	
}
