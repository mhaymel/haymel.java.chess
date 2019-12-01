/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.uci.cmd.lexer;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;

class TokenImpl implements Token {

	private final TokenType type;
	private final String value;

	public TokenImpl(TokenType type, String value) {
		this.type = nonNull(type, "type");
		this.value = verify(value);
	}
	
	@Override
	public TokenType type() {
		return type;
	}

	@Override
	public String string() {
		return value;
	}

	private static String verify(String value) {
		nonNull(value, "value");
		return nonEmpty(value.trim(), "value");
	}

}
