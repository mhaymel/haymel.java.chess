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
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class Moves {
	
	private final ArrayList<Move> moves;
	
	public Moves() {
		moves = new ArrayList<>();
	}
	
	public void add(Field from, Field to) {
		assert from != to;
		moves.add(new Move(from, to));
	}

	public void addCapture(Field from, Field to, Piece piece) {
		assert from != to;
		assert !piece.free();
		assert piece.black() || piece.white();

		moves.add(new Move(from, to, capture));
	}

	public void addWhiteKingSideCastling() {
		moves.add(new Move(e1, g1, kingsideCastling));
	}

	public void addWhiteQueenSideCastling() {
		moves.add(new Move(e1, c1, queensideCastling));
	}
	
	public void addBlackKingSideCastling() {
		moves.add(new Move(e8, g8, kingsideCastling));
	}

	public void addBlackQueenSideCastling() {
		moves.add(new Move(e8, c8, queensideCastling));
	}
	
	public int size() {
		return moves.size();
	}
	
	@Override
	public String toString() {
		List<String> strings = moves.stream().map(Move::toString).collect(toList());		
		return String.format("Moves(%s)", join(", ", strings));
	}
	
	public Move move(int index) {
		return moves.get(index);
	}

}
