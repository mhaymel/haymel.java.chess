/***************************************************
 * (c) Markus Heumel
 *
 * @date:	28.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;
import static java.lang.String.join;

import com.haymel.chess.uci.Moves;

class Info {

	private final String key;
	private final String value;
	
	Info(String key, Moves moves) {
		this(key,  join(" ",  verify(moves).value()));
	}
	
	Info(String key, long value) {
		this(key,  String.valueOf(value));
	}

	Info(String key, int value) {
		this(key,  String.valueOf(value));
	}

	Info(String key, String value) {
		this.key = nonEmpty(key, "key");
		this.value = nonEmpty(value, "value");
	}
	
	Info(String key) {
		this.key = nonEmpty(key, "key");
		this.value = "";
	}
	
	public String key() {
		return key;
	}
	
	public String value() {
		return value;
	}

	private static Moves verify(Moves moves) {
		nonNull(moves, "moves");
		if (moves.value().isEmpty())
			throwIAE("moves must not be empty");
		
		return moves;
	}

	
}
