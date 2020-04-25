/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	25.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.evaluation;

import static com.haymel.chess.engine.board.Field.up;

import com.haymel.chess.engine.board.Field;

class PieceSquareTableFromArray {

	private final int[] values;
	private final boolean switchSides;
	
	PieceSquareTableFromArray(int[] values) {
		this(values, false);
	}
	
	PieceSquareTableFromArray(int[] values, boolean switchSides) {
		assert values != null;
		assert values.length == 64;
		this.values = values;
		this.switchSides = switchSides;
	}
	
	int[] value() {
		int pst[] = new int[up*up];
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				pst[Field.field(x, y)] = values[index(x, y)];
			}
		}

		return pst;
	}

	private int index(int x, int y) {
		assert x >= 0 && x < 8;
		assert y >= 0 && y < 8;
		
		return switchSides 
			? (y*8 + x) 
			: ((7-y)*8 + x);
	}
	
}
