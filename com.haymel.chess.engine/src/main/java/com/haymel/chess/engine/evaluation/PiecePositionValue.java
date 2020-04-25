/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	24.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public interface PiecePositionValue {

	PiecePositionValue noopPiecePositionValue = new PiecePositionValue() {};
	
	default int value(Piece piece) {
		assert piece != null;
		return value(piece.type(), piece.field());
	}

	default int value(int type, int field) {
		assert PieceType.pieceTypeValid(type);
		assert Field.valid(field);
		return 0;
	}
	
}
