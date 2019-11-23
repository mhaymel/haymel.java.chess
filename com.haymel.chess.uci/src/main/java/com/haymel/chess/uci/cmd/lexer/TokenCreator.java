/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.lexer;

import static com.haymel.util.Require.nonNull;

import com.haymel.util.Require;

class TokenCreator {

	private final TokenType type;
	private final String value;
	
	TokenCreator(TokenType type, String value) {
		this.type = nonNull(type, "type");
		this.value = Require.nonEmpty(value, "value");
	}
	
	Token value() {
		switch(type) {
		case bool:
			return boolToken();
		case number:
			return numberToken();
		default:
			return new TokenImpl(type, value);
		}
	}

	private Token numberToken() {
		return new NumberTokenImpl(Long.parseLong(value));
	}

	private Token boolToken() {
		return new BooleanTokenImpl(Boolean.parseBoolean(value));
	}
	
}
