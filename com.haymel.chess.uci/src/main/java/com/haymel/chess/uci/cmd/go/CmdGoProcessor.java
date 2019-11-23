/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.CommandHandler;
import com.haymel.chess.uci.cmd.lexer.Lexer;

public class CmdGoProcessor {

	private final Lexer lexer;
	private final CommandHandler handler;
	
	public CmdGoProcessor(Lexer lexer, CommandHandler handler) {
		this.lexer = nonNull(lexer, "lexer");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
	}


}
