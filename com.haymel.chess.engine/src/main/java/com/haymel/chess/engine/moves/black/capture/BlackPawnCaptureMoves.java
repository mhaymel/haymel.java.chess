/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackPawnCaptureMoves {
	
	private final Piece[] pieces;
	
	public BlackPawnCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == removed || Field.rank(epField) == 2;
		assert epField == removed || pieces[Field.up(epField)].type() == WhitePawn;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field()) {
		case a2:
		case b2:
		case c2:
		case d2:
		case e2:
		case f2:
		case g2:
		case h2:
			return capturePromotion(piece, moves);
		case a4:
		case b4:
		case c4:
		case d4:
		case e4:
		case f4:
		case g4:
		case h4:
			return enpassant(piece, epField, moves);
		default:
			return capture(piece, moves);
		}
	}

	private boolean enpassant(Piece piece, int epField, Moves moves) {
		assert Field.rank(piece.field()) == 3;
		
		int from = piece.field();
		int leftDown = Field.leftDown(from);
		if (leftDown == epField)
			moves.add(from, leftDown, enpassant);
		else if (!capture(from, leftDown, moves))
			return false;
		
		int rightDown = Field.rightDown(from);
		if (rightDown == epField)
			moves.add(from, rightDown, enpassant);
		else if (!capture(from, rightDown, moves))
			return false;
		
		return true;
	}

	private boolean capturePromotion(Piece piece, Moves moves) {
		assert Field.rank(piece.field()) == 1;

		int from = piece.field();
		return 
			capturePromotion(from, Field.leftDown(from), moves) &&
			capturePromotion(from, Field.rightDown(from), moves);
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

	private boolean capture(Piece piece, Moves moves) {
		assert 
			Field.rank(piece.field()) == 2 || 
			Field.rank(piece.field()) == 4 || 
			Field.rank(piece.field()) == 5 || 
			Field.rank(piece.field()) == 6;
		
		int from = piece.field();
		return 
			capture(from, Field.leftDown(from), moves) &&
			capture(from, Field.rightDown(from), moves);
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

}
