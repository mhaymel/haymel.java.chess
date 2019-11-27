/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.uci.cmd.lexer;

class NumberTokenImpl implements NumberToken { //TODO unit test

	private final long value;

	public NumberTokenImpl(long value) {
		this.value = value;
	}
	
	@Override
	public TokenType type() {
		return TokenType.number;
	}

	@Override
	public String string() {
		return String.valueOf(value);
	}

	@Override
	public long value() {
		return value;
	}

}
