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
	
	private final int[] moves;
	private int index = 0;
	
	public Moves() {
		moves = new int[200];
	}
	
	public int[] moves() {
		return moves;
	}
	
	public void add(int from, int to, int type) {
		assert valid(from);
		assert valid(to);
		assert from != to;
		assert validMoveType(type);
	
		moves[index++] = Move.newMove(from, to, type);
	}

	public void add(int move) {
		Move.validMove(move);
		
		moves[index++] = move;
	}

	public int size() {
		return index;
	}
	
	@Override
	public String toString() {
		List<String> strings = movesStream().map((move) -> Move.asString(move)).collect(toList());		
		return String.format("Moves(%s)", join(", ", strings));
	}
	
	private Stream<Integer> movesStream() {
		ArrayList<Integer> list = new ArrayList<>(index);
		for(int i = 0; i < index; i++)
			list.add(moves[i]);
		return list.stream();
	}

	public int move(int index) {
		return moves[index];
	}
	
	public void swap(int i, int j) {
		int tmp = moves[i];
		moves[i] = moves[j];
		moves[j] = tmp;
		
	}
	
	public List<Integer> findMoves(int from, int to) {
		nonNull(from, "from");
		nonNull(to, "to");
		assert from != to;
		
		Predicate<Integer> match = move -> (Move.from(move) == from) && (Move.to(move) == to);
		return movesStream().filter(match).collect(toList());
	}

}
