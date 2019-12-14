/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.pieces.white;

import com.haymel.chess.engine.pieces.Piece;

public abstract class WhitePiece extends Piece {	

	@Override
	public final boolean white() {
		return true;
	}

	@Override
	public final boolean black() {
		return false;
	}

}
