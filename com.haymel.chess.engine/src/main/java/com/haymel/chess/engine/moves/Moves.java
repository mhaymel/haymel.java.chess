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
import static com.haymel.chess.engine.moves.Casteling.blackKingSide;
import static com.haymel.chess.engine.moves.Casteling.blackQueenSide;
import static com.haymel.chess.engine.moves.Casteling.noCasteling;
import static com.haymel.chess.engine.moves.Casteling.whiteKingSide;
import static com.haymel.chess.engine.moves.Casteling.whiteQueenSide;
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
	private final ArrayList<Casteling> casteling;
	
	public Moves() {
		from = new ArrayList<>();
		to = new ArrayList<>();
		capturedPiece = new ArrayList<>();
		casteling = new ArrayList<>();
	}
	
	public void add(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		assert from != to;
		doAdd(from, to);
	}

	public void addCapture(Field from, Field to, Piece piece) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		assert from != to;
		assert !piece.free();
		assert piece.black() || piece.white();
		assert !piece.blackKing() && !piece.whiteKing();
		
		doAdd(from, to, piece, noCasteling);
	}

	public void addWhiteKingSideCasteling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		
		doAdd(e1, g1, free, whiteKingSide);
	}

	public void addWhiteQueenSideCasteling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		
		doAdd(e1, c1, free, whiteQueenSide);
	}
	
	public void addBlackKingSideCasteling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		
		doAdd(e8, g8, free, blackKingSide);
	}

	public void addBlackQueenSideCasteling() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		
		doAdd(e8, c8, free, blackQueenSide);
	}
	
	public int size() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
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
	
	private Move move(int index) {
		return new Move(from.get(index), to.get(index), !capturedPiece.get(index).free(), casteling.get(index));
	}

	private void doAdd(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		assert from != to;
		doAdd(from, to, free, noCasteling);
	}
	
	private void doAdd(Field from, Field to, Piece capturedPiece, Casteling casteling) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
		assert from != to;
			
		this.from.add(from);
		this.to.add(to);
		this.capturedPiece.add(capturedPiece);
		this.casteling.add(casteling);
		
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert this.capturedPiece.size() == this.casteling.size(); 
	}
	
}
