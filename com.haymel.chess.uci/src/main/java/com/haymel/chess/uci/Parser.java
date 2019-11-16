/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.greaterEqualZero;
import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.lessThan;
import static com.haymel.util.Require.nonNull;

public class Parser {

	private static final String[] empty = new String[] {};
	private final String[] line;
	
	public Parser(String line) {
		nonNull(line, "line");
		this.line = extracted(line);
	}

	public boolean empty() {
		return count() == 0;
	}
	
	public int count() {
		return line.length;
	}
	
	public String first() {
		greaterThanZero(count(), "count()");
		return value(0);
	}

	public String value(int index) {
		greaterEqualZero(index, "index");
		lessThan(index, count(), "index");
		return line[index];
	}

	public String[] values() {
		return line;
	}
	
	private static String[] extracted(String line) {
		String trimmed = line.trim();
		
		return  trimmed.isEmpty() ? empty : trimmed.split("\\s+");
	}
	
}
