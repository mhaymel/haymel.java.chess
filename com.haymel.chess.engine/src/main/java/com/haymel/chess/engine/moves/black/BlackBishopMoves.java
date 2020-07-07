/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackBishopMoves {
	
	private final Piece[] board;
	
	public BlackBishopMoves(Piece[] board) {
		assert board != null;
		this.board = board;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert board[piece.field()] == piece;
		assert piece.type() == BlackBishop;

		int from = piece.field();
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = board[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.leftUp(to);
			piece = board[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void leftDown(int from, Moves moves) {
		int to = Field.leftDown(from);
		Piece piece = board[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.leftDown(to);
			piece = board[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightUp(int from, Moves moves) {
		int to = Field.rightUp(from);
		Piece piece = board[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.rightUp(to);
			piece = board[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightDown(int from, Moves moves) {
		int to = Field.rightDown(from);
		Piece piece = board[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.rightDown(to);
			piece = board[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}
	
}
