/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.nonNull;

public class CommandProcessor {

	private final Parser parser;
	private final CommandHandler handler;
	
	public CommandProcessor(String line, CommandHandler handler) {
		this.parser = new Parser(nonNull(line, "line"));
		this.handler = nonNull(handler, "handler");
	}
	
	public void execute() {
		if (parser.empty())
			return;

		if (parser.isCmdUci())
			handler.uci();

		else if (parser.isCmdDebug())
			handleDebug();

		else if (parser.isCmdIsready())
			handler.isReady();
	
		else if (parser.isCmdUcinewgame())
			handler.ucinewgame();
		
		else if (parser.isCmdPosition())
			handlePosition();
		
		else
			unknown();
	}
	
	private void handleDebug() {
		if (firstParamIs("on"))
			handler.debugOn();
		
		else if (firstParamIs("off"))
			handler.debugOff();
			
		else
			unknown();
	}

	private void handlePosition() {
		new CmdPositionProcessor(parser, handler).execute();
	}

	private void unknown() {
		handler.unknown(parser.values());
	}
	
	private boolean firstParamIs(String expected) {
		return parser.count() > 1 && expected.equals(parser.value(1));
	}

}
