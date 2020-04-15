/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.h7;
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
		assert epField == removed || rank(epField) == 2;
		assert epField == removed || pieces[up(epField)].whitePawn();
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
			doubleStepMove(piece, moves);
			break;
		case a4:
		case b4:
		case c4:
		case d4:
		case e4:
		case f4:
		case g4:
		case h4:
			enpassant(piece, epField, moves);
			break;
		case a2:
		case b2:
		case c2:
		case d2:
		case e2:
		case f2:
		case g2:
		case h2:
			promotion(piece, moves);
			break;
		default:
			assert 
				rank(piece.field()) == 2 || 
				rank(piece.field()) == 4 || 
				rank(piece.field()) == 5;

			normal(piece, moves);
			break;
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
