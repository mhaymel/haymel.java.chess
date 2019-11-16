/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.lessThan;
import static java.util.Objects.requireNonNull;

public class Parser {

	private final String[] line;
	
	public Parser(String line) {
		requireNonNull(line);
		this.line = line.trim().split("\\s+");
	}
	
	public int count() {
		return line.length;
	}
	
	public String first() {
		greaterThanZero(count(), "count()");
		return value(0);
		
	}

	public String value(int index) {
		lessThan(index, count(), "index");
		return line[index];
	}

	public String[] values() {
		return line;
	}
	
}
