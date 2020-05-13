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
		assert verifiy();
		
		index--;
	}
	
	private boolean verifiy() {
		PositionCastlingRight r1 = stack[index-2];
		PositionCastlingRight r2 = stack[index-1];
		return 
			(r1.white().kingside() || !r2.white().kingside()) &&
			(r1.white().queenside() || !r2.white().queenside()) &&
			(r1.black().kingside() || !r2.black().kingside()) &&
			(r1.black().queenside() || !r2.black().queenside());
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
