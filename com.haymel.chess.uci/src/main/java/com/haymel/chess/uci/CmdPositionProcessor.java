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
import java.util.Collections;
import java.util.List;

public class CmdPositionProcessor {
														//  position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1		
	private static final int MIN_NUMBER_OF_PARAMS_FOR_FEN =  1       +1  +1                                          +1+1  +1+1+1;      
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
		
		else if (isFen() && hasEnoughParamsForFEN())
			handleFen();
		
		else
			unknown();
	}

	private boolean hasEnoughParamsForFEN() {
		return parser.count() >= MIN_NUMBER_OF_PARAMS_FOR_FEN;
	}

	private boolean isStartPos() {
		return is(1, startpos);
	}

	private void handleStartPos() {
		handler.position(parseMove(2));
	}
	
	private boolean isFen() {
		return is(1, fen);
	}
	
	private void handleFen() {
		handler.position(parseFen(), parseMove(MIN_NUMBER_OF_PARAMS_FOR_FEN));
	}

	private String parseFen() {
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i < MIN_NUMBER_OF_PARAMS_FOR_FEN; i++)
			sb.append(parser.value(i)).append(' ');
		
		return sb.toString().trim();
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

	private List<String> parseMove(int index) {
		if (index == parser.count())
			return Collections.emptyList();

		return parseMoves(index + 1);
	}
	
	private List<String> parseMoves(int index) {
		List<String> moves = new ArrayList<String>();
		
		for(int i = index; i < parser.count(); i++) 
			moves.add(param(i));
				
		return moves;
	}

}
