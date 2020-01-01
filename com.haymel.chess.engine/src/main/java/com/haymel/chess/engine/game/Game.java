/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.piece.Piece;

public final class Game {	//TODO unit test

	private final List<Undo> undos = new ArrayList<>();
	private final PieceList whitePieces = new PieceList();
	private final PieceList blackPieces = new PieceList();
	private final Board board = new Board();
	private ActiveColor activeColor;
	private Field enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	
	public Game() {
		this.activeColor = white;
		this.enPassant = Field.removed;
		this.halfMoveClock = 0;
		this.fullMoveNumber = 1;
	}

	public void push(Move move) {
		push(move, true);
	}

	public void push(Move move, boolean moved) {
		push(new Undo(move, moved, activeColor, enPassant, halfMoveClock, fullMoveNumber));
		resetEnPassant();
	}
	
	public Undo pop() {
		Undo undo = undos.remove(undos.size() - 1);
		
		activeColor = undo.activeColor();
		enPassant = undo.enPassant();
		halfMoveClock = undo.halfMoveClock();
		fullMoveNumber = undo.fullMoveNumber();
		
		return undo;
	}

	public ActiveColor activeColor() {
		return activeColor;
	}

	public Piece piece(Field field) {
		return board.piece(field);
	}

	public void clear(Field field) {
		board.clear(field);
	}

	public void place(Piece piece) {
		assert piece != null;
		assert piece.black() || piece.white();
		assert 
			piece.black() && blackPieces.contains(piece) || 
			piece.white() && whitePieces.contains(piece);
		
		board.place(piece);
	}

	private void push(Undo undo) {
		undos.add(undo);
	}

	public void resetEnPassant() {
		enPassant(removed);
	}

	public void enPassant(Field field) {
		assert field != null;
		assert field == removed || board.piece(field).free();
		assert 
			field == removed ||
			activeColor == white && field.rank() == a3.rank() && board.piece(field.up()).whitePawn()||
			activeColor == black && field.rank() == a6.rank() && board.piece(field.down()).blackPawn();
		
		enPassant = field;
	}

	public Field enPassant() {
		return enPassant;
	}
	
	public void incHalfMoveClock() {
		halfMoveClock++;
	}

	public void resetHalfMoveClock() {
		halfMoveClock(0);
	}
	
	public void halfMoveClock(int value) {
		assert value >= 0;
		
		halfMoveClock = value;
	}

	public void resetFullMoveNumber() {
		fullMoveNumber = 1;
	}
	
	public void activeColorWhite() {
		activeColor = white;
	}
	
	public void activeColorBlack() {
		activeColor = black;
	}

	public int halfMoveClock() {
		return halfMoveClock;
	}

	public int fullMoveNumber() {
		return fullMoveNumber;
	}

	public void addBlack(Piece piece) {
		assert piece != null;
		assert piece.black();
		assert !blackPieces.contains(piece);

		blackPieces.add(piece);
	}

	public void removeBlack(Piece piece) {
		assert piece != null;
		assert piece.black();
		assert !piece.blackKing();
		assert blackPieces.contains(piece);
		
		blackPieces.remove(piece);
	}
	
	public boolean containsBlackPiece(Piece piece) {
		assert piece != null;
		assert piece.black();
		return blackPieces.contains(piece);
	}

	public boolean containsWhitePiece(Piece piece) {
		assert piece != null;
		assert piece.white();
		return whitePieces.contains(piece);
	}
	
	public boolean assertVerify() {
		assert board.assertVerify();
		assert halfMoveClock >= 0;
		assert fullMoveNumber > 0;
		
		int size = whitePieces.size();
		for(int i = 0; i < size; i++) {
			Piece piece = whitePieces.piece(i);
			assert piece.white();
			assert board.piece(piece.field()) == piece;
		}

		size = blackPieces.size();
		for(int i = 0; i < size; i++) {
			Piece piece = blackPieces.piece(i);
			assert piece.black();
			assert board.piece(piece.field()) == piece;
		}
		
		Field fy = Field.a1;
		for(int y = 0; y < 8; y++) {
			Field fx = fy;
			for(int x = 0; x < 8; x++) {
				Piece p = board.piece(fx);
				assert !p.border();
				if (p.black()) 
					assert blackPieces.contains(p);
				else if (p.white())
					assert whitePieces.contains(p);
				fx = fx.right();
			}
			fy = fy.up();
			
		}
		return true;
	}

	public void addWhite(Piece piece) {
		assert piece != null;
		assert piece.white();
		whitePieces.add(piece);
	}

	public Board board() {
		return board;
	}

	public PieceList whitePieces() {
		return whitePieces;
	}

}
