/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	19.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.moves.MoveType.validMoveType;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Moves {
	
	private final Move[] moves;
	private int index = 0;
	
	public Moves() {
		moves = new Move[200];
	}
	
	public Move[] moves() {
		return moves;
	}
	
	public void add(int from, int to, int type) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert validMoveType(type);
	
		moves[index++] = new Move(from, to, type);
	}

	public void add(Move move) {
		moves[index++] = move;
	}

	public int size() {
		return index;
	}
	
	@Override
	public String toString() {
		List<String> strings = movesStream().map(Move::toString).collect(toList());		
		return String.format("Moves(%s)", join(", ", strings));
	}
	
	private Stream<Move> movesStream() {
		ArrayList<Move> list = new ArrayList<Move>(index);
		for(int i = 0; i < index; i++)
			list.add(moves[i]);
		return list.stream();
	}

	public Move move(int index) {
		return moves[index];
	}
	
	public List<Move> findMoves(int from, int to) {
		nonNull(from, "from");
		nonNull(to, "to");
		assert from != to;
		
		Predicate<Move> match = move -> (move.from() == from) && (move.to() == to);
		return movesStream().filter(match).collect(toList());
	}

}
