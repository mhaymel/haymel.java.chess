/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static java.lang.Integer.MAX_VALUE;

public final class Field {

	static final int up = 12;
	private static final Field[] fields = createFields();

	public static final Field a1 = fields[0].rightUp().rightUp();
	public static final Field a2 = a1.up();
	public static final Field a3 = a2.up();
	public static final Field a4 = a3.up();
	public static final Field a5 = a4.up();
	public static final Field a6 = a5.up();
	public static final Field a7 = a6.up();
	public static final Field a8 = a7.up();
	
	public static final Field b1 = a1.right();
	public static final Field b2 = b1.up();
	public static final Field b3 = b2.up();
	public static final Field b4 = b3.up();
	public static final Field b5 = b4.up();
	public static final Field b6 = b5.up();
	public static final Field b7 = b6.up();
	public static final Field b8 = b7.up();
	
	public static final Field c1 = b1.right();
	public static final Field c2 = c1.up();
	public static final Field c3 = c2.up();
	public static final Field c4 = c3.up();
	public static final Field c5 = c4.up();
	public static final Field c6 = c5.up();
	public static final Field c7 = c6.up();
	public static final Field c8 = c7.up();

	public static final Field d1 = c1.right();
	public static final Field d2 = d1.up();
	public static final Field d3 = d2.up();
	public static final Field d4 = d3.up();
	public static final Field d5 = d4.up();
	public static final Field d6 = d5.up();
	public static final Field d7 = d6.up();
	public static final Field d8 = d7.up();
	
	public static final Field e1 = d1.right();
	public static final Field e2 = e1.up();
	public static final Field e3 = e2.up();
	public static final Field e4 = e3.up();
	public static final Field e5 = e4.up();
	public static final Field e6 = e5.up();
	public static final Field e7 = e6.up();
	public static final Field e8 = e7.up();

	public static final Field f1 = e1.right();
	public static final Field f2 = f1.up();
	public static final Field f3 = f2.up();
	public static final Field f4 = f3.up();
	public static final Field f5 = f4.up();
	public static final Field f6 = f5.up();
	public static final Field f7 = f6.up();
	public static final Field f8 = f7.up();

	public static final Field g1 = f1.right();
	public static final Field g2 = g1.up();
	public static final Field g3 = g2.up();
	public static final Field g4 = g3.up();
	public static final Field g5 = g4.up();
	public static final Field g6 = g5.up();
	public static final Field g7 = g6.up();
	public static final Field g8 = g7.up();
	
	public static final Field h1 = g1.right();
	public static final Field h2 = h1.up();
	public static final Field h3 = h2.up();
	public static final Field h4 = h3.up();
	public static final Field h5 = h4.up();
	public static final Field h6 = h5.up();
	public static final Field h7 = h6.up();
	public static final Field h8 = h7.up();

	public static final Field removed = fields[fields.length - 1];
	
	private final int position;
	
	private Field(int position) {
		this.position = position;
	}
	
	int position() {
		return position;
	}
	
	public Field up() {
		return fields[position + up];
	}
	
	public Field down() {
		return fields[position - up];
	}
	
	public Field left() {
		return fields[position - 1];
	}
	
	public Field right() {
		return fields[position + 1];
	}

	public Field rightUp() {
		return right().up();
	}

	public Field rightDown() {
		return right().down();
	}
	
	public Field leftUp() {
		return left().up();
	}
	
	public Field leftDown() {
		return left().down();
	}

	@Override
	public String toString() {
		return Character.toString('a' + file()) + (rank()+1);
	}
	
	/**
	 * @return zero based rank (row)
	 */
	public int rank() {		//TODO unit test
		return position / up - 2;
	}

	/**
	 * @return zero base file (column)
	 */
	public int file() {					//TODO unit test
		return (position % up) - 2;
	}
	
	private static Field[] createFields() {
		Field[] fields = new Field[up*up + 1];
		
		for(int i = 0; i < fields.length - 1; i++) 
			fields[i] = new Field(i);
		
		fields[fields.length - 1] = new Field(MAX_VALUE);
		
		return fields;
	}
	
}
