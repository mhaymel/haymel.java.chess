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

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackPawnCaptureMoves {
	
	private final Piece[] pieces;
	
	public BlackPawnCaptureMoves(Piece[] pieces) {
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

	private void enpassant(Piece piece, int epField, Moves moves) {
		assert Field.rank(piece.field()) == 3;
		
		int from = piece.field();
		int leftDown = Field.leftDown(from);
		if (leftDown == epField)
			moves.addEnpassant(from, leftDown, pieces[Field.up(epField)]);
		else
			capture(from, leftDown, moves);
		
		int rightDown = Field.rightDown(from);
		if (rightDown == epField)
			moves.addEnpassant(from, rightDown, pieces[Field.up(epField)]);
		else
			capture(from, rightDown, moves);
	}

	private void capturePromotion(Piece piece, Moves moves) {
		assert Field.rank(piece.field()) == 1;

		int from = piece.field();
		capturePromotion(from, Field.leftDown(from), moves);
		capturePromotion(from, Field.rightDown(from), moves);
	}
	
	private void capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.white())
			moves.addBlackCapturePromotion(from, to, piece);
	}

	private void capture(Piece piece, Moves moves) {
		assert 
			Field.rank(piece.field()) == 2 || 
			Field.rank(piece.field()) == 4 || 
			Field.rank(piece.field()) == 5 || 
			Field.rank(piece.field()) == 6;
		
		int from = piece.field();
		capture(from, Field.leftDown(from), moves);
		capture(from, Field.rightDown(from), moves);
	}

	private void capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.white())
			moves.addCapture(from, to, piece);
	}

}
