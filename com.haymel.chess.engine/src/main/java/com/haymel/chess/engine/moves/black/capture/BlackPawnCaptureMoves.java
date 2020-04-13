/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackPawnCaptureMoves {
	
	private final Piece[] pieces;
	
	public BlackPawnCaptureMoves(Board board) {
		assert board != null;
		
		this.pieces = board.pieces;
	}
	
	public void generate(Piece piece, Field epField, Moves moves) {
		assert piece != null;
		assert epField != null;
		assert moves != null;
		assert epField == removed || epField.rank() == 2;
		assert epField == removed || pieces[epField.up().position()].whitePawn();
		assert piece.field() != removed;
		assert pieces[piece.field().position()] == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field().rank()) {
		case 2:
		case 4:
		case 5:
		case 6:
			capture(piece, moves);
			break;
		case 1:
			capturePromotion(piece, moves);
			break;
		case 3:
			enpassant(piece, epField, moves);
			break;
		default:
			assert false;
		}
	}

	private void enpassant(Piece piece, Field epField, Moves moves) {
		assert piece.field().rank() == 3;
		
		Field from = piece.field();
		
		Field leftDown = from.leftDown();
		if (leftDown.equals(epField))
			moves.addEnpassant(from, leftDown, pieces[epField.up().position()]);
		else
			capture(from, leftDown, moves);
		
		Field rightDown = from.rightDown();
		if (rightDown.equals(epField))
			moves.addEnpassant(from, rightDown, pieces[epField.up().position()]);
		else
			capture(from, rightDown, moves);
	}

	private void capturePromotion(Piece piece, Moves moves) {
		assert piece.field().rank() == 1;

		Field from = piece.field();
		capturePromotion(from, from.leftDown(), moves);
		capturePromotion(from, from.rightDown(), moves);
	}
	
	private void capturePromotion(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		if (piece.white())
			moves.addBlackCapturePromotion(from, to, piece);
	}

	private void capture(Piece piece, Moves moves) {
		assert 
			piece.field().rank() == 2 || 
			piece.field().rank() == 4 || 
			piece.field().rank() == 5 || 
			piece.field().rank() == 6;
		
		Field from = piece.field();
		capture(from, from.leftDown(), moves);
		capture(from, from.rightDown(), moves);
	}

	private void capture(Field from, Field to, Moves moves) {
		Piece piece = pieces[to.position()];
		if (piece.white())
			moves.addCapture(from, to, piece);
	}

}
