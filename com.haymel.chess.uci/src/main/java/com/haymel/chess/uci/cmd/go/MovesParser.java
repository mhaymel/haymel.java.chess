/***************************************************
 * (c) Markus Heumel
 *
 * @date:	24.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.Moves;
import com.haymel.chess.uci.MovesImpl;
import com.haymel.chess.uci.cmd.lexer.Lexer;
import com.haymel.chess.uci.cmd.lexer.Token;
import com.haymel.chess.uci.cmd.lexer.TokenType;

class MovesParser {	//TODO unit test

	private final Lexer lexer;
	
	MovesParser(Lexer lexer) {
		this.lexer = nonNull(lexer, "lexer");
	}
	
	Moves execute() {
		MovesImpl moves = new MovesImpl();
		
		while(lexer.hasNext()) {
			Token token = lexer.next();
			if (token.type() != TokenType.word) {
				lexer.pushback();
				return moves;
			}
			
			moves.add(token.string());
		}
		
		return moves;
	}
	
}
