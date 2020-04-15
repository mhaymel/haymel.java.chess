/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackPawnMoves {
	
	private final Piece[] pieces;
	
	public BlackPawnMoves(Piece[] pieces) {
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == removed || Field.rank(epField) == 2;
		assert epField == removed || pieces[Field.up(epField)].whitePawn();
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackPawn;
		
		switch(Field.rank(piece.field())) {
		case 6:
			doubleStepMove(piece, moves);
			break;
		case 3:
			enpassant(piece, epField, moves);
			break;
		case 1:
			promotion(piece, moves);
			break;
		case 2:
		case 4:
		case 5:
			normal(piece, moves);
			break;
		default:
			assert false;
		}
	}

	private void enpassant(Piece piece, int epField, Moves moves) {
		assert rank(piece.field()) == 3;
		
		int from = piece.field();
		int to = down(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		int leftDown = leftDown(from);
		if (leftDown == epField)
			moves.addEnpassant(from, leftDown, pieces[up(epField)]);
		else
			capture(from, leftDown, moves);
		
		int rightDown = Field.rightDown(from);
		if (rightDown == epField)
			moves.addEnpassant(from, rightDown, pieces[up(epField)]);
		else
			capture(from, rightDown, moves);
	}

	private void promotion(Piece piece, Moves moves) {
		assert rank(piece.field()) == 1;

		int from = piece.field();
		int to = down(from);
		if (isFree(to))
			moves.addBlackPromotion(from);
		
		capturePromotion(from, moves);
	}

	private void capturePromotion(int from, Moves moves) {
		capturePromotion(from, leftDown(from), moves);
		capturePromotion(from, rightDown(from), moves);
	}
	
	private void capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.white())
			moves.addBlackCapturePromotion(from, to, piece);
	}

	private void normal(Piece piece, Moves moves) {
		assert 
			rank(piece.field()) == 2 || 
			rank(piece.field()) == 4 || 
			rank(piece.field()) == 5;
		
		int from = piece.field();
		int to = down(from);
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		capture(from, moves);
	}

	private void doubleStepMove(Piece piece, Moves moves) {
		assert rank(piece.field()) == 6;
		
		int from = piece.field();
		int to = down(from);
		if (isFree(to)) {
			moves.addPawnMove(from, to);
			int doubleTo = down(to);
			if (isFree(doubleTo))
				moves.addPawnDoubleStep(from, doubleTo);		
		}
		
		capture(from, moves);
	}

	private void capture(int from, Moves moves) {
		capture(from, Field.leftDown(from), moves);
		capture(from, Field.rightDown(from), moves);
	}
	
	private void capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.white())
			moves.addCapture(from, to, piece);
	}

	private boolean isFree(int field) {
		return pieces[field].free();
	}
		
}
