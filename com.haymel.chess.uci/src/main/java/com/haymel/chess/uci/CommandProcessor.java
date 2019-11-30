/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.cmd.lexer.TokenType.off;
import static com.haymel.chess.uci.cmd.lexer.TokenType.on;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.cmd.go.CmdGoProcessor;
import com.haymel.chess.uci.cmd.lexer.Lexer;
import com.haymel.chess.uci.cmd.lexer.Token;

public class CommandProcessor {

	private final Lexer lexer;
	private final Command handler;
	
	public CommandProcessor(String line, Command handler) {
		this.lexer = new Lexer(nonNull(line, "line"));
		this.handler = nonNull(handler, "handler");
	}
	
	public void execute() {
		switch(lexer.next().type()) {
		case uci:
			handler.uci();
			break;
			
		case debug:
			handleDebug();
			break;

		case isready:
			handler.isReady();
			break;

		case ucinewgame:
			handler.ucinewgame();
			break;
			
		case position:
			handlePosition();
			break;
			
		case go:
			handleGo();
			break;
			
		case stop:
			handler.stop();
			break;
			
		case ponderhit:
			handler.ponderhit();
			break;
			
		case quit:
			handler.quit();
			break;
			
		case eof:
			break;

		default:
			unknown();
		}
	}
	
	private void handleDebug() {
		Token token = lexer.next();
		if (token.type() == on)
			handler.debugOn();
		
		else if (token.type() == off)
			handler.debugOff();
			
		else
			unknown();
	}

	private void handlePosition() {
		new CmdPositionProcessor(lexer, handler).execute();
	}

	private void handleGo() {
		new CmdGoProcessor(lexer, handler).execute();
	}

	private void unknown() {
		handler.unknown(lexer.toString());
	}
	
}
