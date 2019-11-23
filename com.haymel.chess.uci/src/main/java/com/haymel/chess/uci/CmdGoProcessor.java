/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

public class CmdGoProcessor {

	private final Parser parser;
	private final CommandHandler handler;
	
	public CmdGoProcessor(Parser parser, CommandHandler handler) {
		this.parser = nonNull(parser, "parser");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
		if (!parser.isCmdGo())
			throwIAE("command <go> expected but was %s", parser.first());
		
	}


}
