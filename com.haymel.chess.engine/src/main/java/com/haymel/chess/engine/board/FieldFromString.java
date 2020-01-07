/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.board;

import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

public class FieldFromString {

	private final String fieldAsString;
	
	public FieldFromString(String fieldAsString) {
		this.fieldAsString = verify(fieldAsString);
	}

	public Field value() {
		int file = fieldAsString.charAt(0) - 'a';
		int rank = fieldAsString.charAt(1) - '1';
		return Field.field(file, rank);
	}
	
	private static String verify(String fieldAsString) {
		nonNull(fieldAsString, "fieldAsString");
		if (fieldAsString.length() != 2)
			throw new IllegalArgumentException(format("fieldAsString must have a length of 2 but is '%s'", fieldAsString));
			
		char c1 = fieldAsString.charAt(0);
		if (c1 < 'a' || c1 > 'h') 
			throw new IllegalArgumentException(format("first character must be 'a-h' but is '%s' in '%s'", c1, fieldAsString));
		
		char c2 = fieldAsString.charAt(1);
		if (c2 < '1' || c2 > '8') 
			throw new IllegalArgumentException(format("second character must be '1-8' but is '%s' in '%s'", c2, fieldAsString));

		return fieldAsString;
	}
}
