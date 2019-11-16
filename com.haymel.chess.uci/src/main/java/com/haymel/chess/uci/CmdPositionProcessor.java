/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	26.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;

public class CmdPositionProcessor {

	public void execute(String[] command, CommandHandler handler) {
		
		nonEmpty(command, "command");
		nonNull(handler, "handler");
		
	}
	
}
