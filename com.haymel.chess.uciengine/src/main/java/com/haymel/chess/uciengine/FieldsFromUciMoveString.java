/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.util.Optional;

import com.haymel.chess.engine.board.FieldFromString;

final class FieldsFromUciMoveString {

	private final String move;
	
	public FieldsFromUciMoveString(String move) {
		nonNull(move, "move");
		
		if (move.length() != 4 && move.length() != 5)
			throw new IllegalArgumentException(format("move must have a lenght of 4 but is '%s'", move));

		this.move = move;
	}
	
	public int from() {
		String from = move.substring(0, 2);
		return new FieldFromString(from).value();
	}
	
	public int to() {
		String to = move.substring(2, 4);
		return new FieldFromString(to).value();
	}
	
	public boolean isPromotion() {
		return move.length() == 5;
	}
	
	public Promotion promotion() {
		if (!isPromotion())
			throw new IllegalStateException(format("move '%s' is not a promotion!", move));
		
		char c = move.charAt(4);
		Optional<Promotion> promotion = Promotion.promotion(c);
		if (promotion.isEmpty())
			throw new IllegalStateException(format("move '%s' contains wrong character '%s' for promotion!", move, c));
		
		return promotion.get();
	}
	
}
