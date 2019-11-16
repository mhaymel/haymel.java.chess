/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.nonNull;

public class CmdPositionProcessor {

	private final Parser parser;
	private final CommandHandler handler;
	
	public CmdPositionProcessor(Parser parser, CommandHandler handler) {
		this.parser = nonNull(parser, "parser");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
	}
	
}
