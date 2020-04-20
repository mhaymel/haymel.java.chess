/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.a5;
import static com.haymel.chess.engine.board.Field.a7;
import static com.haymel.chess.engine.board.Field.b5;
import static com.haymel.chess.engine.board.Field.b7;
import static com.haymel.chess.engine.board.Field.c5;
import static com.haymel.chess.engine.board.Field.c7;
import static com.haymel.chess.engine.board.Field.d5;
import static com.haymel.chess.engine.board.Field.d7;
import static com.haymel.chess.engine.board.Field.down;
import static com.haymel.chess.engine.board.Field.e5;
import static com.haymel.chess.engine.board.Field.e7;
import static com.haymel.chess.engine.board.Field.f5;
import static com.haymel.chess.engine.board.Field.f7;
import static com.haymel.chess.engine.board.Field.g5;
import static com.haymel.chess.engine.board.Field.g7;
import static com.haymel.chess.engine.board.Field.h5;
import static com.haymel.chess.engine.board.Field.h7;
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
		
		switch(piece.field()) {
		case a5:
		case b5:
		case c5:
		case d5:
		case e5:
		case f5:
		case g5:
		case h5:
			enpassant(piece, epField, moves);
			break;
		case a7:
		case b7:
		case c7:
		case d7:
		case e7:
		case f7:
		case g7:
		case h7:
			capturePromotion(piece, moves);
			break;
		default:
			assert 
				rank(piece.field()) == 1 || rank(piece.field()) == 2 || 
				rank(piece.field()) == 3 || rank(piece.field()) == 5;
			
			capture(piece, moves);
			break;
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
		if (black(piece))
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
		if (black(piece))
			moves.addCapture(from, to, piece);
	}

	private static boolean black(Piece piece) {
		return piece != null && piece.black();
	}

}
