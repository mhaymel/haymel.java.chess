/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.removed;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.pieces.Piece;

public class Generator {

	private final Board board;
	private final Set<Field> attacked;
	private final List<Move> moves;
	
	public Generator(Board board, Set<Field> attacked) {
		assert board != null;
		assert attacked != null;
		
		this.board = board;
		this.attacked = attacked;
		
		moves = new ArrayList<Move>(80);
	}
	
}
