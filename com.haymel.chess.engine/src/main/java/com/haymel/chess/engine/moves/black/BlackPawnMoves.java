/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackPawnMoves {
	
	private final Piece[] pieces;
	
	public BlackPawnMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == removed || rank(epField) == 2;
		assert epField == removed || pieces[up(epField)].type() == WhitePawn;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field()) {
		case a7:
		case b7:
		case c7:
		case d7:
		case e7:
		case f7:
		case g7:
		case h7:
			return doubleStepMove(piece, moves);
		case a2:
		case b2:
		case c2:
		case d2:
		case e2:
		case f2:
		case g2:
		case h2:
			return promotion(piece, moves);
		default:
			return normal(piece, moves);
		}
	}

	private boolean promotion(Piece piece, Moves moves) {
		assert rank(piece.field()) == 1;

		int from = piece.field();
		int to = down(from);
		if (isFree(to)) {
			moves.add(from, to, promotionQueen);
			moves.add(from, to, promotionRook);
			moves.add(from, to, promotionBishop);
			moves.add(from, to, promotionKnight);
		}
		
		return capturePromotion(from, moves);
	}

	private boolean capturePromotion(int from, Moves moves) {
		return 
			capturePromotion(from, leftDown(from), moves) &&
			capturePromotion(from, rightDown(from), moves);
	}
	
	private boolean capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (white(piece)) {
			if (piece.type() == WhiteKing)
				return false;
			
			moves.add(from, to, capturePromotionQueen);
			moves.add(from, to, capturePromotionRook);
			moves.add(from, to, capturePromotionBishop);
			moves.add(from, to, capturePromotionKnight);
		}
		return true;
	}

	private boolean normal(Piece piece, Moves moves) {
		assert 
			rank(piece.field()) == 2 || 
			rank(piece.field()) == 3 || 
			rank(piece.field()) == 4 || 
			rank(piece.field()) == 5;
		
		int from = piece.field();
		int to = down(from);
		if (isFree(to)) 
			moves.add(from, to, pawn);
		
		return capture(from, moves);
	}

	private boolean doubleStepMove(Piece piece, Moves moves) {
		assert rank(piece.field()) == 6;
		
		int from = piece.field();
		int to = down(from);
		if (isFree(to)) {
			moves.add(from, to, pawn);
			int doubleTo = down(to);
			if (isFree(doubleTo))
				moves.add(from, doubleTo, pawnDoubleStep);		
		}
		
		return capture(from, moves);
	}

	private boolean capture(int from, Moves moves) {
		return 
			capture(from, leftDown(from), moves) &&
			capture(from, rightDown(from), moves);
	}
	
	private boolean capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (white(piece)) {
			if (piece.type() == WhiteKing)
				return false;
			
			moves.add(from, to, capture);
		}
		return true;
	}

	private static boolean white(Piece piece) {
		return piece != null && PieceType.white(piece.type());
	}

	private boolean isFree(int field) {
		return pieces[field] == null;
	}
		
}
