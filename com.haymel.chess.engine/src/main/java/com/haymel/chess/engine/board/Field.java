/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static java.lang.String.format;

public final class Field {

	static final int up = 12;

	private static final int right = 1;
	private static final int rightUp = right + up;

	public static final int a1 = 0 + rightUp + rightUp;
	public static final int a2 = up + a1;
	public static final int a3 = up + a2;
	public static final int a4 = up + a3;
	public static final int a5 = up + a4;
	public static final int a6 = up + a5;
	public static final int a7 = up + a6;
	public static final int a8 = up + a7;
	
	public static final int b1 = right + a1;
	public static final int b2 = up + b1;
	public static final int b3 = up + b2;
	public static final int b4 = up + b3;
	public static final int b5 = up + b4;
	public static final int b6 = up + b5;
	public static final int b7 = up + b6;
	public static final int b8 = up + b7;
	
	public static final int c1 = right + b1;
	public static final int c2 = up + c1;
	public static final int c3 = up + c2;
	public static final int c4 = up + c3;
	public static final int c5 = up + c4;
	public static final int c6 = up + c5;
	public static final int c7 = up + c6;
	public static final int c8 = up + c7;

	public static final int d1 = right + c1;
	public static final int d2 = up + d1;
	public static final int d3 = up + d2;
	public static final int d4 = up + d3;
	public static final int d5 = up + d4;
	public static final int d6 = up + d5;
	public static final int d7 = up + d6;
	public static final int d8 = up + d7;
	
	public static final int e1 = right + d1;
	public static final int e2 = up + e1;
	public static final int e3 = up + e2;
	public static final int e4 = up + e3;
	public static final int e5 = up + e4;
	public static final int e6 = up + e5;
	public static final int e7 = up + e6;
	public static final int e8 = up + e7;

	public static final int f1 = right + e1;
	public static final int f2 = up + f1;
	public static final int f3 = up + f2;
	public static final int f4 = up + f3;
	public static final int f5 = up + f4;
	public static final int f6 = up + f5;
	public static final int f7 = up + f6;
	public static final int f8 = up + f7;

	public static final int g1 = right + f1;
	public static final int g2 = up + g1;
	public static final int g3 = up + g2;
	public static final int g4 = up + g3;
	public static final int g5 = up + g4;
	public static final int g6 = up + g5;
	public static final int g7 = up + g6;
	public static final int g8 = up + g7;
	
	public static final int h1 = right + g1;
	public static final int h2 = up + h1;
	public static final int h3 = up + h2;
	public static final int h4 = up + h3;
	public static final int h5 = up + h4;
	public static final int h6 = up + h5;
	public static final int h7 = up + h6;
	public static final int h8 = up + h7;

	public static final int removed = up*up + 1 - 1;
	
	public static int up(int field) {
		return field + up;
	}
	
	public static int down(int field) {
		return field - up;
	}
	
	public static int left(int field) {
		return field - 1;
	}
	
	public static int right(int field) {
		return field + 1;
	}
	
	public static int rightUp(int field) {
		return field + 1 + up;
	}
	
	public static int rightDown(int field) {
		return field + 1 - up;
	}
	
	public static int leftUp(int field) {
		return field - 1 + up;
	}
	
	public static int leftDown(int field) {
		return field - 1 - up;
	}
	
	public static int leftLeftUp(int field) {
		return field - 1 - 1 + up;
	}
	
	public static int leftLeftDown(int field) {
		return field - 1 - 1 - up;
	}
	
	public static int rightRightUp(int field) {
		return field + 1 + 1 + up;
	}
	
	public static int rightRightDown(int field) {
		return field + 1 + 1 - up;
	}

	public static int leftUpUp(int field) {
		return field - 1 + up + up;
	}
	
	public static int leftDownDown(int field) {
		return field - 1 - up - up;
	}
	
	public static int rightUpUp(int field) {
		return field + 1 + up + up;
	}

	public static int rightDownDown(int field) {
		return field + 1 - up - up;
	}

	public static String fieldAsString(int field) {
		return Character.toString('a' + file(field)) + (rank(field)+1);
	}
	
	/**
	 * @return zero based rank (row)
	 */
	public static int rank(int field) {		//TODO unit test
		assert valid(field);
		return field / up - 2;
	}

	/**
	 * @return zero base file (column)
	 */
	public static int file(int field) {					//TODO unit test
		return (field % up) - 2;
	}
	

	public static int field(int file, int rank) {
		if (file < 0 || file > 7 )
			throw new IllegalArgumentException(format("file must be between 0 and 7 but is %s", file));

		if (rank < 0 || rank > 7 )
			throw new IllegalArgumentException(format("rank must be between 0 and 7 but is %s", rank));

		
		return (rank + 2)*up + file + 2;
	}

	public static boolean valid(int field) {
		return field >= 0 && field < up*up+1;
	}

}
