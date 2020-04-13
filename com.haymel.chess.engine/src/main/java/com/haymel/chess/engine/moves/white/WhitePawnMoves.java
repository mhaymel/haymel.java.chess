/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	31.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhitePawnMoves {
	
	private final Board board;
	private final Piece[] pieces;
	
	public WhitePawnMoves(Board board) {
		assert board != null;
		
		this.board = board;
		this.pieces = board.pieces;
	}
	
	public void generate(Piece piece, Field epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField != null;
		assert epField == Field.removed || epField.rank() == 5;
		assert epField == Field.removed || pieces[epField.down().position()].blackPawn();
		assert piece.field() != removed;
		assert pieces[piece.field().position()] == piece;
		assert piece.type() == PieceType.WhitePawn;
		
		switch(piece.field().rank()) {
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

	private void enpassant(Piece piece, Field epField, Moves moves) {
		assert piece.field().rank() == 4;
		
		Field from = piece.field();
		Field to = from.up();
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		Field leftUp = from.leftUp();
		if (leftUp.equals(epField))
			moves.addEnpassant(from, leftUp, pieces[epField.down().position()]);
		else
			capture(from, leftUp, moves);
		
		Field rightUp = from.rightUp();
		if (rightUp.equals(epField))
			moves.addEnpassant(from, rightUp, pieces[epField.down().position()]);
		else
			capture(from, rightUp, moves);
	}

	private void promotion(Piece piece, Moves moves) {
		assert piece.field().rank() == 6;

		Field from = piece.field();
		Field to = from.up();
		if (isFree(to))
			moves.addWhitePromotion(from);
		
		capturePromotion(from, moves);
	}

	private void capturePromotion(Field from, Moves moves) {
		capturePromotion(from, from.leftUp(), moves);
		capturePromotion(from, from.rightUp(), moves);
	}
	
	private void capturePromotion(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		if (piece.black())
			moves.addWhiteCapturePromotion(from, to, piece);
	}

	private void normal(Piece piece, Moves moves) {
		assert 
			piece.field().rank() == 2 || 
			piece.field().rank() == 3 || 
			piece.field().rank() == 5;
		
		Field from = piece.field();
		Field to = from.up();
		if (isFree(to)) 
			moves.addPawnMove(from, to);
		
		capture(from, moves);
	}

	private void doubleStepMove(Piece piece, Moves moves) {
		assert piece.field().rank() == 1;
		
		Field from = piece.field();
		Field to = from.up();
		if (isFree(to)) {
			moves.addPawnMove(from, to);
			Field doubleTo = to.up();
			if (isFree(doubleTo))
				moves.addPawnDoubleStep(from, doubleTo);		
		}
		
		capture(from, moves);
	}

	private void capture(Field from, Moves moves) {
		capture(from, from.leftUp(), moves);
		capture(from, from.rightUp(), moves);
	}
	
	private void capture(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		if (piece.black())
			moves.addCapture(from, to, piece);
	}

	private boolean isFree(Field f) {
		return board.isFree(f);
	}
		
}
