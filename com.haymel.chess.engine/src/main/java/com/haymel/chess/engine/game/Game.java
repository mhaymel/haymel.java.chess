/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;

public final class Game {	//TODO unit test

	private final PieceList whitePieces = new PieceList();
	private final PieceList blackPieces = new PieceList();
	private final Board board = new Board();
	private ActiveColor activeColor;
	private Field enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
}
