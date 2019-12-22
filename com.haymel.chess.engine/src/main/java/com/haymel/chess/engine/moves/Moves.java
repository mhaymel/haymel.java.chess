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

	public Moves() {
		from = new ArrayList<>();
		to = new ArrayList<>();
		capturedPiece = new ArrayList<>();
	}
	
	public void add(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert from != to;
		doAdd(from, to);
	}

	public void addCapture(Field from, Field to, Piece piece) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert from != to;
		assert !piece.free();
		assert piece.black() || piece.white();
		assert !piece.blackKing() && !piece.whiteKing();
		
		doAdd(from, to, piece);
	}

	public void addCasteling(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert from != to;
		assert 
			(from == e1 && (to == g1) || to == c1) ||
			(from == e8 && (to == g8) || to == c8);
		
		doAdd(from, to);
	}
	
	public int size() {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
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
		return new Move(from.get(index), to.get(index), !capturedPiece.get(index).free());
	}

	private void doAdd(Field from, Field to) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert from != to;
		doAdd(from, to, free);
	}
	
	private void doAdd(Field from, Field to, Piece capturedPiece) {
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
		assert from != to;
			
		this.from.add(from);
		this.to.add(to);
		this.capturedPiece.add(capturedPiece);
		
		assert this.from.size() == this.to.size(); 
		assert this.to.size() == this.capturedPiece.size(); 
	}
	
}
