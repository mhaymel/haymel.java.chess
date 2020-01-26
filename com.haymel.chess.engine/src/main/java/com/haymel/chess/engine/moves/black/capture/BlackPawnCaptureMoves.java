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
	
	private final Board board;
	private final Moves moves;
	
	public BlackPawnCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece piece, Field epField) {
		assert piece != null;
		assert epField != null;
		assert epField == removed || epField.rank() == 2;
		assert epField == removed || board.piece(epField.up()).whitePawn();
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field().rank()) {
		case 2:
		case 4:
		case 5:
		case 6:
			capture(piece);
			break;
		case 1:
			capturePromotion(piece);
			break;
		case 3:
			enpassant(piece, epField);
			break;
		default:
			assert false;
		}
	}

	private void enpassant(Piece piece, Field epField) {
		assert piece.field().rank() == 3;
		
		Field from = piece.field();
		
		Field leftDown = from.leftDown();
		if (leftDown.equals(epField))
			moves.addEnpassant(from, leftDown, board.piece(epField.up()));
		else
			capture(from, leftDown);
		
		Field rightDown = from.rightDown();
		if (rightDown.equals(epField))
			moves.addEnpassant(from, rightDown, board.piece(epField.up()));
		else
			capture(from, rightDown);
	}

	private void capturePromotion(Piece piece) {
		assert piece.field().rank() == 1;

		Field from = piece.field();
		capturePromotion(from, from.leftDown());
		capturePromotion(from, from.rightDown());
	}
	
	private void capturePromotion(Field from, Field to) {
		Piece piece = board.piece(to);
		if (piece.white())
			moves.addBlackCapturePromotion(from, to, piece);
	}

	private void capture(Piece piece) {
		assert 
			piece.field().rank() == 2 || 
			piece.field().rank() == 4 || 
			piece.field().rank() == 5 || 
			piece.field().rank() == 6;
		
		Field from = piece.field();
		capture(from, from.leftDown());
		capture(from, from.rightDown());
	}

	private void capture(Field from, Field to) {
		Piece piece = board.piece(to);
		if (piece.white())
			moves.addCapture(from, to, piece);
	}

}
