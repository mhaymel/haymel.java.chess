/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhitePawnCaptureMoves {	//TODO unit test
	
	private final Board board;
	private final Moves moves;
	
	public WhitePawnCaptureMoves(Board board, Moves moves) {	
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
	}
	
	public void generate(Piece piece, Field epField) {
		assert piece != null;
		assert epField != null;
		assert epField == Field.removed || epField.rank() == 5;
		assert epField == Field.removed || board.piece(epField.down()).blackPawn();
		assert piece.field() != removed;
		assert board.piece(piece.field()) == piece;
		assert piece.type() == PieceType.WhitePawn;
		
		switch(piece.field().rank()) {
		case 1:
		case 2:
		case 3:
		case 5:
			capture(piece);
			break;
		case 4:
			enpassant(piece, epField);
			break;
		case 6:
			capturePromotion(piece);
			break;
		default:
			assert false;
		}
	}

	private void enpassant(Piece piece, Field epField) {
		assert piece.field().rank() == 4;
		
		Field from = piece.field();
		
		Field leftUp = from.leftUp();
		if (leftUp.equals(epField))
			moves.addEnpassant(from, leftUp, board.piece(epField.down()));
		else
			capture(from, leftUp);
		
		Field rightUp = from.rightUp();
		if (rightUp.equals(epField))
			moves.addEnpassant(from, rightUp, board.piece(epField.down()));
		else
			capture(from, rightUp);
	}

	private void capturePromotion(Piece piece) {
		assert piece.field().rank() == 6;

		Field from = piece.field();
		capturePromotion(from, from.leftUp());
		capturePromotion(from, from.rightUp());
	}

	private void capturePromotion(Field from, Field to) {
		Piece piece = board.piece(to);
		if (piece.black())
			moves.addWhiteCapturePromotion(from, to, piece);
	}

	private void capture(Piece piece) {
		assert 
			piece.field().rank() == 1 || 
			piece.field().rank() == 2 || 
			piece.field().rank() == 3 || 
			piece.field().rank() == 5;
		
		Field from = piece.field();
		capture(from, from.leftUp());
		capture(from, from.rightUp());
	}

	private void capture(Field from, Field to) {
		Piece piece = board.piece(to);
		if (piece.black())
			moves.addCapture(from, to, piece);
	}

}
