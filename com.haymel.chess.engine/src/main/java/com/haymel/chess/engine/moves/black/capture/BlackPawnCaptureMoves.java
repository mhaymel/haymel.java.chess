/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.a2;
import static com.haymel.chess.engine.board.Field.a4;
import static com.haymel.chess.engine.board.Field.b2;
import static com.haymel.chess.engine.board.Field.b4;
import static com.haymel.chess.engine.board.Field.c2;
import static com.haymel.chess.engine.board.Field.c4;
import static com.haymel.chess.engine.board.Field.d2;
import static com.haymel.chess.engine.board.Field.d4;
import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.board.Field.f2;
import static com.haymel.chess.engine.board.Field.f4;
import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.g2;
import static com.haymel.chess.engine.board.Field.g4;
import static com.haymel.chess.engine.board.Field.h2;
import static com.haymel.chess.engine.board.Field.h4;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;

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
		assert epField == removed || pieces[Field.up(epField)].type() == WhitePawn;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackPawn;
		
		switch(piece.field()) {
		case a2:
		case b2:
		case c2:
		case d2:
		case e2:
		case f2:
		case g2:
		case h2:
			capturePromotion(piece, moves);
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
		default:
			assert 
				Field.rank(piece.field()) == 2 || 
				Field.rank(piece.field()) == 4 || 
				Field.rank(piece.field()) == 5 || 
				Field.rank(piece.field()) == 6   : "field: " + fieldAsString(piece.field());

			capture(piece, moves);
			break;
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
		if (white(piece))
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
		if (white(piece))
			moves.addCapture(from, to, piece);
	}

	private static boolean white(Piece piece) {
		return piece != null && piece.white();
	}

}
