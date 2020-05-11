/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.05.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.castling;

public class CastlingRightHistory {

	private final PositionCastlingRight[] stack = new PositionCastlingRight[1000];
	private int index = 1;
	
	public CastlingRightHistory() {
		reset();
	}
	
	public void reset() {
		index = 1;
		for(int i = 0; i < stack.length; i++)
			stack[i] = new PositionCastlingRight();
	}
	
	public void pop() {
		assert index > 1;
		index--;
	}
	
	public void push() {
		PositionCastlingRight last = stack[index-1];
		PositionCastlingRight current = stack[index];
		current.rights(last);

		index++;
	}
	
	public PositionCastlingRight castlingRight() {
		assert index > 0;
		return stack[index-1];
	}
	
}
