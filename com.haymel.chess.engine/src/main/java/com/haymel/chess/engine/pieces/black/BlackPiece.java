/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.black;

import com.haymel.chess.engine.pieces.Piece;

public abstract class BlackPiece extends Piece {	

	@Override
	public final boolean white() {
		return false;
	}

	@Override
	public final boolean black() {
		return true;
	}

}
