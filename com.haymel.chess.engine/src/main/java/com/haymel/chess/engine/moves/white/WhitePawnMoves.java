/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	31.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.board.Field.up;

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
	
	public void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == Field.removed || rank(epField) == 5;
		assert epField == Field.removed || pieces[down(epField)].blackPawn();
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == PieceType.WhitePawn;
		
		switch(rank(piece.field())) {
		case 1:
			doubleStepMove(piece, moves);
			break;
		case 4:
			enpassant(piece, epField, moves);
			break;
		case 6:
			promotion(piece, moves);
			break;
		case 2:
		case 3:
		case 5:
			normal(piece, moves);
			break;
		default:
			assert false;
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
