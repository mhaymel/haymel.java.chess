/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	19.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.c1;
import static com.haymel.chess.engine.board.Field.c8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.g1;
import static com.haymel.chess.engine.board.Field.g8;
import static com.haymel.chess.engine.moves.Castling.blackKingSide;
import static com.haymel.chess.engine.moves.Castling.blackQueenSide;
import static com.haymel.chess.engine.moves.Castling.noCastling;
import static com.haymel.chess.engine.moves.Castling.whiteKingSide;
import static com.haymel.chess.engine.moves.Castling.whiteQueenSide;
import static com.haymel.chess.engine.piece.Piece.free;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class Moves {
	
	private final ArrayList<Field> from;
	private final ArrayList<Field> to;
	private final ArrayList<Piece> capturedPiece;
	private final ArrayList<Castling> castling;
	
	public Moves() {
		from = new ArrayList<>();
		to = new ArrayList<>();
		capturedPiece = new ArrayList<>();
		castling = new ArrayList<>();
	}
	
	public void add(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		assert from != to;
		doAdd(from, to);
	}

	public void addCapture(Field from, Field to, Piece piece) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		assert from != to;
		assert !piece.free();
		assert piece.black() || piece.white();
		assert !piece.blackKing() && !piece.whiteKing();
		
		doAdd(from, to, piece, noCastling);
	}

	public void addWhiteKingSideCastling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		
		doAdd(e1, g1, free, whiteKingSide);
	}

	public void addWhiteQueenSideCastling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		
		doAdd(e1, c1, free, whiteQueenSide);
	}
	
	public void addBlackKingSideCastling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		
		doAdd(e8, g8, free, blackKingSide);
	}

	public void addBlackQueenSideCastling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		
		doAdd(e8, c8, free, blackQueenSide);
	}
	
	public int size() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		return from.size();
	}
	
	@Override
	public String toString() {
		List<Move> moves = new ArrayList<>();
		
		int size = size();
		for(int i = 0; i < size; i++)
			moves.add(move(i));
	
		List<String> strings = moves.stream().map(Move::toString).collect(toList());		
		return String.format("Moves(%s)", join(", ", strings));
	}
	
	public Move move(int index) {
		return new Move(from.get(index), to.get(index), !capturedPiece.get(index).free(), castling.get(index));
	}

	private void doAdd(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		assert from != to;
		doAdd(from, to, free, noCastling);
	}
	
	private void doAdd(Field from, Field to, Piece capturedPiece, Castling castling) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
		assert from != to;
			
		this.from.add(from);
		this.to.add(to);
		this.capturedPiece.add(capturedPiece);
		this.castling.add(castling);
		
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.castling.size(); 
	}
	
}
