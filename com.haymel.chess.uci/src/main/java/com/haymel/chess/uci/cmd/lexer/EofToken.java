/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.uci.cmd.lexer;

import static com.haymel.util.exception.HaymelNullPointerException.throwNPE;

class EofToken implements Token {

	static final EofToken eof = new EofToken();
	
	@Override
	public TokenType type() {
		return TokenType.eof;
	}

	@Override
	public String string() {
		return throwNPE("eof does not have a value. This is probably a bug!", "");
	}

}
