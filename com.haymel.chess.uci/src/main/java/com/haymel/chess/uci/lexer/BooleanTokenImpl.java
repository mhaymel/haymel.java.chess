/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.uci.lexer;

public class BooleanTokenImpl implements BooleanToken {

	private final boolean value;

	public BooleanTokenImpl(boolean value) {
		this.value = value;
	}
	
	@Override
	public TokenType type() {
		return TokenType.bool;
	}

	@Override
	public String string() {
		return String.valueOf(value);
	}

	@Override
	public boolean value() {
		return value;
	}

}
