package com.haymel.chess.uci.cmd.go;

import com.haymel.chess.uci.Parser;
import com.haymel.util.Require;

final class GoParamParser {
	
	private final Parser parser;
	private GoParam param;
	private int nextToken;
	
	GoParamParser(Parser parser) {
		this.parser = Require.nonNull(parser, "parser");
	}
	
	GoParam execute() {
		nextToken = 1;
		param = new GoParam();
		
//		while(!finished()) {
//			if (parser.isWtime(nextToken))
//		}
		
		return param;
	}
	
	private boolean finished() {
		return nextToken >= parser.count();
	}

}
