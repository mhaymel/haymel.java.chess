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
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhitePawnMoves {
	
	private final Piece[] pieces;
	
	public WhitePawnMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == removed || rank(epField) == 5;
		assert epField == removed || pieces[down(epField)].blackPawn();
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
			doubleStepMove(piece, moves);
			break;
		case a5:
		case b5:
		case c5:
		case d5:
		case e5:
		case f5:
		case g5:
		case h5:
			enpassant(piece, epField, moves);
			break;
		case a7:
		case b7:
		case c7:
		case d7:
		case e7:
		case f7:
		case g7:
		case h7:
			promotion(piece, moves);
			break;
		default:
			assert rank(piece.field()) == 2 || rank(piece.field()) == 3 || rank(piece.field()) == 5;
			normal(piece, moves);
			break;
		}
	}

	private void enpassant(Piece piece, int epField, Moves moves) {
		assert rank(piece.field()) == 4;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		int leftUp = leftUp(from);
		if (leftUp == epField)
			moves.addEnpassant(from, leftUp, pieces[down(epField)]);
		else
			capture(from, leftUp, moves);
		
		int rightUp = Field.rightUp(from);
		if (rightUp == epField)
			moves.addEnpassant(from, rightUp, pieces[down(epField)]);
		else
			capture(from, rightUp, moves);
	}

	private void promotion(Piece piece, Moves moves) {
		assert rank(piece.field()) == 6;

		int from = piece.field();
		int to = up(from);
		if (isFree(to))
			moves.addWhitePromotion(from);
		
		capturePromotion(from, moves);
	}

	private void capturePromotion(int from, Moves moves) {
		capturePromotion(from, leftUp(from), moves);
		capturePromotion(from, rightUp(from), moves);
	}
	
	private void capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.black())
			moves.addWhiteCapturePromotion(from, to, piece);
	}

	private void normal(Piece piece, Moves moves) {
		assert 
			rank(piece.field()) == 2 || 
			rank(piece.field()) == 3 || 
			rank(piece.field()) == 5;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		capture(from, moves);
	}

	private void doubleStepMove(Piece piece, Moves moves) {
		assert rank(piece.field()) == 1;
		
		int from = piece.field();
		int to = up(from);
		if (isFree(to)) {
			moves.addPawnMove(from, to);
			int doubleTo = up(to);
			if (isFree(doubleTo))
				moves.addPawnDoubleStep(from, doubleTo);		
		}
		
		capture(from, moves);
	}

	private void capture(int from, Moves moves) {
		capture(from, leftUp(from), moves);
		capture(from, rightUp(from), moves);
	}
	
	private void capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.black())
			moves.addCapture(from, to, piece);
	}

	private boolean isFree(int field) {
		return pieces[field].free();
	}
		
}
