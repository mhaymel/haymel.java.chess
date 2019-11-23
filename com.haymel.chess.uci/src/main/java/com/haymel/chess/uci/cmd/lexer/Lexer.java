/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static com.haymel.chess.uci.cmd.lexer.TokenType.tokenTypeOf;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelException.throwHE;
import static java.lang.String.join;

public class Lexer {

	private static final String[] empty = new String[] {};
	private final String[] line;
	private int index;
	
	public Lexer(String line) {
		nonNull(line, "line");
		this.line = extracted(line);
		this.index = 0;
	}
	
	Token next() {
		if (index > count())
			return attemptToReadAfterEof();
		
		if (index == count())
			return eof();
		
		return token(line[index++]);
	}

	private Token token(String string) {
		return new TokenCreator(tokenTypeOf(string), string).value();
	}

	private Token eof() {
		index++;
		return EofToken.eof;
	}

	private Token attemptToReadAfterEof() {
		return throwHE("Attempt to fetch token after eof in line '%s'. This is probably a bug!", join(" ", line));
	}
	
	private int count() {
		return line.length;
	}
		
	private static String[] extracted(String line) {
		String trimmed = line.trim();
		
		return  trimmed.isEmpty() ? empty : trimmed.split("\\s+");
	}
	
}
