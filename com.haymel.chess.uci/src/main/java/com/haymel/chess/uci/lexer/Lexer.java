/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.lexer;

import static com.haymel.chess.uci.lexer.TokenType.tokenTypeOf;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelException.throwHE;
import static java.lang.String.format;
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
	
	public Token next() {
		if (index > count())
			return attemptToReadAfterEof();
		
		if (index == count())
			return eof();
		
		return token(line[index++]);
	}

	public void pushback() {
		if (index == 0)
			throwHE("Attempt to pushback a token without having read one before. This is probably a bug in %s", this);
		
		index--;
	}
		
	private Token token(String string) {
		return new TokenCreator(tokenTypeOf(string), string).value();
	}

	private Token eof() {
		index++;
		return EofToken.eof;
	}

	private Token attemptToReadAfterEof() {
		return throwHE("Attempt to fetch token after eof. This is probably a bug in %s", this);
	}
	
	private int count() {
		return line.length;
	}
		
	private static String[] extracted(String line) {
		String trimmed = line.trim();
		
		return trimmed.isEmpty() ? empty : trimmed.split("\\s+");
	}
	
	@Override
	public String toString() {
		return format("%s(index=%s, '%s')", getClass().getSimpleName(), index, join(" ", line));
	}
	
	public int remainingTokens() {
		return count() - index + 1 /*eof*/ ;
	}
	
	public boolean hasNext() {
		return remainingTokens() > 0;
	}
	
}
