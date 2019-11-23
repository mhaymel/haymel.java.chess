package com.haymel.chess.uci.cmd.go;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.cmd.lexer.Lexer;

final class GoParamParser {
	
	private final Lexer lexer;
	private GoParam param;
	private int nextToken;
	
	GoParamParser(Lexer lexer) {
		this.lexer = nonNull(lexer, "lexer");
	}
	
	GoParam execute() {
		nextToken = 1;
		param = new GoParam();
		
//		while(!finished()) {
//			if (parser.isWtime(nextToken))
//		}
		
		return param;
	}
	
}
