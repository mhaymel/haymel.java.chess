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
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.white;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackQueenMoves {

	private final Piece[] pieces;
	
	public BlackQueenMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.pieces = pieces;
	}
	
	public void generate(Piece piece, Moves moves) {
		assert piece != null;
		assert moves != null;
		assert piece.field() != removed;
		assert pieces[piece.field()] == piece;
		assert piece.type() == BlackQueen;

		int from = piece.field();
		up(from, moves);
		down(from, moves);
		left(from, moves);
		right(from, moves);
		leftUp(from, moves);
		leftDown(from, moves);
		rightUp(from, moves);
		rightDown(from, moves);
	}

	private void up(int from, Moves moves) {
		int to = Field.up(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.up(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void down(int from, Moves moves) {
		int to = Field.down(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.down(to);
			piece = pieces[to];		
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void left(int from, Moves moves) {
		int to = Field.left(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.left(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void right(int from, Moves moves) {
		int to = Field.right(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.right(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void leftUp(int from, Moves moves) {
		int to = Field.leftUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void leftDown(int from, Moves moves) {
		int to = Field.leftDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.leftDown(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightUp(int from, Moves moves) {
		int to = Field.rightUp(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.rightUp(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}

	private void rightDown(int from, Moves moves) {
		int to = Field.rightDown(from);
		Piece piece = pieces[to];
		while(piece == null) {
			moves.add(from, to, normal);
			to = Field.rightDown(to);
			piece = pieces[to];
		}
		
		assert piece.type() != WhiteKing;

		if (white(piece.type())) 
			moves.add(from, to, capture);
	}
	
}
