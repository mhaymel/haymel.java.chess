/***************************************************
 * (c) Markus Heumel
 *
 * @date:	27.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.parameter;

import static com.haymel.util.exception.HaymelException.throwHE;

public interface LongParam {

	static final LongParam undefined = new LongParam() {};
	
	default boolean defined() { return false; }
	default long value() { return throwHE("value is undefined"); }
	
}
