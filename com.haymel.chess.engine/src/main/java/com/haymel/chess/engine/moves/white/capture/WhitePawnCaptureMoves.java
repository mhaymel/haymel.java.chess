/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightUp;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhitePawnCaptureMoves {	//TODO unit test
	
	private final Piece[] pieces;
	
	public WhitePawnCaptureMoves(Piece[] pieces) {	
		assert pieces != null;
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert epField == Field.removed || rank(epField) == 5;
		assert epField == Field.removed || pieces[Field.down(epField)].blackPawn();
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == PieceType.WhitePawn;
		
		switch(rank(piece.field())) {
		case 1:
		case 2:
		case 3:
		case 5:
			capture(piece, moves);
			break;
		case 4:
			enpassant(piece, epField, moves);
			break;
		case 6:
			capturePromotion(piece, moves);
			break;
		default:
			assert false;
		}
	}

	private void enpassant(Piece piece, int epField, Moves moves) {
		assert rank(piece.field()) == 4;
		
		int from = piece.field();
		
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

	private void capturePromotion(Piece piece, Moves moves) {
		assert rank(piece.field()) == 6;

		int from = piece.field();
		capturePromotion(from, leftUp(from), moves);
		capturePromotion(from, rightUp(from), moves);
	}

	private void capturePromotion(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.black())
			moves.addWhiteCapturePromotion(from, to, piece);
	}

	private void capture(Piece piece, Moves moves) {
		assert 
			rank(piece.field()) == 1 || 
			rank(piece.field()) == 2 || 
			rank(piece.field()) == 3 || 
			rank(piece.field()) == 5;
		
		int from = piece.field();
		capture(from, leftUp(from), moves);
		capture(from, rightUp(from), moves);
	}

	private void capture(int from, int to, Moves moves) {
		Piece piece = pieces[to];
		if (piece.black())
			moves.addCapture(from, to, piece);
	}

}
