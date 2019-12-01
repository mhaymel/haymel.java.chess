/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.Command;
import com.haymel.chess.uci.lexer.Lexer;

public class CmdGoProcessor {

	private final Lexer lexer;
	private final Command handler;
	
	public CmdGoProcessor(Lexer lexer, Command handler) {
		this.lexer = nonNull(lexer, "lexer");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
		GoParam param = new GoParamParser(lexer).execute();
		
		if (param.movetime().defined())
			handler.goMovetime(param.movetime().value());
		
		else if (param.depth().defined())
			handler.goDepth(param.depth().value());
		
		else if (param.nodes().defined())
			handler.goNodes(param.nodes().value());
		
		else if (param.infinite() && param.searchmoves().defined())
			handler.goInfinite(param.searchmoves().moves());
		
		else if (param.infinite())
			handler.goInfinite();
			
		else if (param.mate().defined())
			handler.goMate(param.mate().value());
		
		else if (wtimeAndBtimeDefined(param) && param.ponder())
			handleGoPonder(param);
		
		else if (wtimeAndBtimeDefined(param) && param.movestogo().defined())
			handleGoMovesToGo(param);
		
		else if (wtimeAndBtimeDefined(param))
			handleGo(param);
		
		else
			handler.unknown(lexer.toString());
	}

	private void handleGoMovesToGo(GoParam param) {
		handler.go(wtime(param), btime(param), winc(param), binc(param), movestogo(param));
	}

	private void handleGo(GoParam param) {
		handler.go(wtime(param), btime(param), winc(param), binc(param));
	}

	private static int wtime(GoParam param) {
		return param.wtime().value();
	}

	private static int btime(GoParam param) {
		return param.btime().value();
	}

	private static int winc(GoParam param) {
		return param.winc().value();
	}

	private static int binc(GoParam param) {
		return param.binc().value();
	}

	private void handleGoPonder(GoParam param) {
		handler.goPonder(wtime(param), btime(param), winc(param), binc(param));
	}
	
	private static boolean wtimeAndBtimeDefined(GoParam param) {
		return param.wtime().defined() && param.btime().defined();
	}

	private int movestogo(GoParam param) {
		return param.movestogo().value();
	}

}
