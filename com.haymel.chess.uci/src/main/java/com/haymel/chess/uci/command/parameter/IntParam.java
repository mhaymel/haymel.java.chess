/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.parameter;

import static com.haymel.util.exception.HaymelException.throwHE;

public interface IntParam {

	static final IntParam undefined = new IntParam() {};
	
	default boolean defined() { return false; }
	default int value() { return throwHE("value is undefined"); }
	
}
