/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	28.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class UndoMove {

	private Field enPassant;
	private int halfMoveClock;
	private boolean moved;
	
	private Piece captured;
}
