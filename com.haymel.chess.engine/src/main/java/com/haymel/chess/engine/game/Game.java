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
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class Game {	//TODO unit test

	private final PieceList whitePieces = new PieceList();
	private final PieceList blackPieces = new PieceList();
	private final Board board = new Board();
	private ActiveColor activeColor;
	private Field enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
	public void makeMove(Move move) {
		assert move != null;
		assert board.piece(move.from()).black() || board.piece(move.from()).white(); 
		assert 
			activeColor == ActiveColor.white && board.piece(move.from()).white() || 
			activeColor == ActiveColor.black && board.piece(move.from()).black();
		
		Piece piece = board.piece(move.from());
		board.clear(move.from());
		piece.field(move.to());
		board.place(piece);
		piece.setMoved(true);
	}
	
}
