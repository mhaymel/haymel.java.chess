/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	31.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h7;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhitePawnMoves {
	
	private final Piece[] pieces;
	
	public WhitePawnMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public boolean generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == removed || rank(epField) == 5;
		assert epField == removed || pieces[down(epField)].type() == BlackPawn;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == WhitePawn;
		
		switch(piece.field()) {
		case a2:
		case b2:
		case c2:
		case d2:
		case e2:
		case f2:
		case g2:
		case h2:
			return doubleStepMove(piece, moves);
		case a5:
		case b5:
		case c5:
		case d5:
		case e5:
		case f5:
		case g5:
		case h5:
			return enpassant(piece, epField, moves);
		case a7:
		case b7:
		case c7:
		case d7:
		case e7:
		case f7:
		case g7:
		case h7:
			return promotion(piece, moves);
		default:
			return normal(piece, moves);
		}
	}

	private boolean enpassant(Piece piece, int epField, Moves moves) {
		assert rank(piece.field()) == 4;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		int leftUp = leftUp(from);
		if (leftUp == epField)
			moves.addEnpassant(from, leftUp);
		else if (!capture(from, leftUp, moves))
			return false;
		
		int rightUp = Field.rightUp(from);
		if (rightUp == epField)
			moves.addEnpassant(from, rightUp);
		else if (!capture(from, rightUp, moves))
			return false;
		
		return true;
	}

	private boolean promotion(Piece piece, Moves moves) {
		assert rank(piece.field()) == 6;

		int from = piece.field();
		int to = up(from);
		if (isFree(to))
			moves.addWhitePromotion(from);
		
		return capturePromotion(from, moves);
	}

	private boolean capturePromotion(int from, Moves moves) {
		return 
				capturePromotion(from, leftUp(from), moves) &&
				capturePromotion(from, rightUp(from), moves);
	}
	
	private boolean capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (black(piece)) {
			if (piece.type() == BlackKing)
				return false;
			
			moves.addWhiteCapturePromotion(from, to);
		}
		return true;
	}

	private boolean normal(Piece piece, Moves moves) {
		assert 
			rank(piece.field()) == 2 || 
			rank(piece.field()) == 3 || 
			rank(piece.field()) == 5;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		return capture(from, moves);
	}

	private boolean doubleStepMove(Piece piece, Moves moves) {
		assert rank(piece.field()) == 1;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) {
			moves.addPawnMove(from, to);
			int doubleTo = up(to);
			if (isFree(doubleTo))
				moves.addPawnDoubleStep(from, doubleTo);		
		}
		
		return capture(from, moves);
	}

	private boolean capture(int from, Moves moves) {
		return
			capture(from, leftUp(from), moves) &&
			capture(from, rightUp(from), moves);
	}
	
	private boolean capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (black(piece)) {
			if (piece.type() == BlackKing)
				return false;

			moves.addCapture(from, to);
		}
		return true;
	}

	private static boolean black(Piece piece) {
		return piece != null && PieceType.black(piece.type());
	}

	private boolean isFree(int field) {
		return pieces[field] == null;
	}
		
}
