/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
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
	
	public void generate(Piece piece, int epField, Moves moves) {
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
			capturePromotion(piece, moves);
			break;
		default:
			capture(piece, moves);
			break;
		}
	}

	private void capturePromotion(Piece piece, Moves moves) {
		assert Field.rank(piece.field()) == 1;

		int from = piece.field();
		capturePromotion(from, Field.leftDown(from), moves);
		capturePromotion(from, Field.rightDown(from), moves);
	}
	
	private void capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (white(piece)) {
			assert piece.type() != WhiteKing;
			
			moves.add(from, to, capturePromotionQueen);
			moves.add(from, to, capturePromotionRook);
			moves.add(from, to, capturePromotionBishop);
			moves.add(from, to, capturePromotionKnight);
		}
	}

	private void capture(Piece piece, Moves moves) {
		assert 
			Field.rank(piece.field()) == 2 || 
			Field.rank(piece.field()) == 3 || 
			Field.rank(piece.field()) == 4 || 
			Field.rank(piece.field()) == 5 || 
			Field.rank(piece.field()) == 6;
		
		int from = piece.field();
		capture(from, Field.leftDown(from), moves);
		capture(from, Field.rightDown(from), moves);
	}

	private void capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (white(piece)) {
			assert piece.type() != WhiteKing;
			
			moves.add(from, to, capture);
		}
	}

	private static boolean white(Piece piece) {
		return piece != null && PieceType.white(piece.type());
	}

}
