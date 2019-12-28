/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

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
	
	public void makeMove(Move move) {
		assert move != null;
		assert 
			activeColor == white && board.piece(move.from()).white() || 
			activeColor == black && board.piece(move.from()).black();
		
		switch(activeColor) {
		case black:
			makeBlackMove(move);
			assert activeColor == white;
			break;
		case white:
			makeWhiteMove(move);
			assert activeColor == black;
			break;
		default:
			assert false : "unknown activeColor " + activeColor;
			break;
		}
		
		assert halfMoveClock >= 0;
	}

	public void undoMove() {
		switch(activeColor) {
		case black:
			undoWhiteMove();
			break;
		case white:
			undoBlackMove();
			break;
		default:
			assert false : "unknown activeColor " + activeColor;
			break;
		}

		assert halfMoveClock >= 0;
	}
	
	private void makeWhiteMove(Move move) {
		assert activeColor == white; 
		assert board.piece(move.from()).white();
		
		switch(move.type()) {
		case normal:
			NormalWhiteMove.make(this, move);
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotionBishop:
			break;
		case capturePromotionKnight:
			break;
		case capturePromotionQueen:
			break;
		case capturePromotionRook:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case promotionBishop:
			break;
		case promotionKnight:
			break;
		case promotionQueen:
			break;
		case promotionRook:
			break;
		case queensideCastling:
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}
		
		assert activeColor == black; 
	}

	private void makeBlackMove(Move move) {
		assert activeColor == black; 
		assert board.piece(move.from()).black();
		
		switch(move.type()) {
		case normal:
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotionBishop:
			break;
		case capturePromotionKnight:
			break;
		case capturePromotionQueen:
			break;
		case capturePromotionRook:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case promotionBishop:
			break;
		case promotionKnight:
			break;
		case promotionQueen:
			break;
		case promotionRook:
			break;
		case queensideCastling:
			break;
		default:
			assert false : "unknown move type " + move.type();
			break;
		}

		assert activeColor == white; 
	}

	private void undoWhiteMove() {
		assert activeColor == black; 
		
		Undo undo = pop();
		
		switch(undo.move().type()) {
		case normal:
			NormalWhiteMove.undo(this, undo.move(), undo.moved());
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotionBishop:
			break;
		case capturePromotionKnight:
			break;
		case capturePromotionQueen:
			break;
		case capturePromotionRook:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case promotionBishop:
			break;
		case promotionKnight:
			break;
		case promotionQueen:
			break;
		case promotionRook:
			break;
		case queensideCastling:
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor == white; 
	}
	
	private void undoBlackMove() {
		assert activeColor == white; 
		
		Undo undo = pop();
		
		switch(undo.move().type()) {
		case normal:
			break;
		case pawnDoubleStep:
			break;
		case capture:
			break;
		case capturePromotionBishop:
			break;
		case capturePromotionKnight:
			break;
		case capturePromotionQueen:
			break;
		case capturePromotionRook:
			break;
		case enpassant:
			break;
		case kingsideCastling:
			break;
		case promotionBishop:
			break;
		case promotionKnight:
			break;
		case promotionQueen:
			break;
		case promotionRook:
			break;
		case queensideCastling:
			break;
		default:
			assert false : "unknown move type " + undo.move().type();
			break;
		}
		
		assert activeColor == black; 
	}

	public void push(Move move, boolean moved) {
		push(new Undo(move, moved, activeColor, enPassant, halfMoveClock, fullMoveNumber));
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
		board.place(piece);
	}

	private void push(Undo undo) {
		undos.add(undo);
	}

	public void enPassant(Field field) {
		enPassant = field;
	}

	public void incHalfMoveClock() {
		halfMoveClock++;
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

	public Field enPassant() {
		return enPassant;
	}
	
}
