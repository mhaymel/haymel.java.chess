/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.CommandHandler.fen;
import static com.haymel.chess.uci.CommandHandler.startpos;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;

import java.util.ArrayList;
import java.util.List;

public class CmdPositionProcessor {

	private final Parser parser;
	private final CommandHandler handler;
	
	public CmdPositionProcessor(Parser parser, CommandHandler handler) {
		this.parser = nonNull(parser, "parser");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
		if (!parser.isCmdPosition())
			throwIAE("command <position> expected but was %s", parser.first());
		
		if (isStartPos())
			handleStartPos();
		
		else if (isFen())
			handleFen();
		
		else
			handler.unknown(parser.values());
	}

	private boolean isStartPos() {
		return is(1, startpos);
	}

	private void handleStartPos() {
		if (is(2, CommandHandler.moves)) 
			handler.positionStart(parseMoves(3));
			
		unknown();
	}
	
	private boolean isFen() {
		return is(1, fen);
	}
	
	private void handleFen() {
	}

	private String param(int index) {
		return parser.value(index).toLowerCase();
	}
	
	private boolean is(int index, String token) {
		return param(index).equals(token);
	}
	
	private void unknown() {
		handler.unknown(parser.values());
	}

	private List<String> parseMoves(int index) {
		List<String> moves = new ArrayList<String>();
		
		for(int i = index; i < parser.count(); i++) 
			moves.add(param(i));
				
		return moves;
	}

}
