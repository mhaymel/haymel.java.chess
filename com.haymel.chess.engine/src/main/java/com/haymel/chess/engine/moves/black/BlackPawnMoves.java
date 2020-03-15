/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackPawnMoves {
	
	private final Board board;
	
	public BlackPawnMoves(Board board) {
		assert board != null;
		
		this.board = board;
	}
	
	public void generate(Piece piece, Field epField, Moves moves) {
		assert piece != null;
		assert epField != null;
		assert moves != null;
		assert epField == removed || epField.rank() == 2;
		assert epField == removed || board.piece(epField.up()).whitePawn();
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field().rank()) {
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

	private void enpassant(Piece piece, Field epField, Moves moves) {
		assert piece.field().rank() == 3;
		
		Field from = piece.field();
		Field to = from.down();
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		Field leftDown = from.leftDown();
		if (leftDown.equals(epField))
			moves.addEnpassant(from, leftDown, board.piece(epField.up()));
		else
			capture(from, leftDown, moves);
		
		Field rightDown = from.rightDown();
		if (rightDown.equals(epField))
			moves.addEnpassant(from, rightDown, board.piece(epField.up()));
		else
			capture(from, rightDown, moves);
	}

	private void promotion(Piece piece, Moves moves) {
		assert piece.field().rank() == 1;

		Field from = piece.field();
		Field to = from.down();
		if (isFree(to))
			moves.addBlackPromotion(from);
		
		capturePromotion(from, moves);
	}

	private void capturePromotion(Field from, Moves moves) {
		capturePromotion(from, from.leftDown(), moves);
		capturePromotion(from, from.rightDown(), moves);
	}
	
	private void capturePromotion(Field from, Field to, Moves moves) {
		Piece piece = board.piece(to);
		if (piece.white())
			moves.addBlackCapturePromotion(from, to, piece);
	}

	private void normal(Piece piece, Moves moves) {
		assert 
			piece.field().rank() == 2 || 
			piece.field().rank() == 4 || 
			piece.field().rank() == 5;
		
		Field from = piece.field();
		Field to = from.down();
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		capture(from, moves);
	}

	private void doubleStepMove(Piece piece, Moves moves) {
		assert piece.field().rank() == 6;
		
		Field from = piece.field();
		Field to = from.down();
		if (isFree(to)) {
			moves.addPawnMove(from, to);
			Field doubleTo = to.down();
			if (isFree(doubleTo))
				moves.addPawnDoubleStep(from, doubleTo);		
		}
		
		capture(from, moves);
	}

	private void capture(Field from, Moves moves) {
		capture(from, from.leftDown(), moves);
		capture(from, from.rightDown(), moves);
	}
	
	private void capture(Field from, Field to, Moves moves) {
		Piece piece = board.piece(to);
		if (piece.white())
			moves.addCapture(from, to, piece);
	}

	private boolean isFree(Field f) {
		return board.isFree(f);
	}
		
}
