/***************************************************
 * (c) Markus Heumel
 *
 * @date:	24.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.lexer.Lexer;
import com.haymel.chess.uci.lexer.Token;
import com.haymel.chess.uci.lexer.TokenType;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.moves.MovesImpl;

class MovesParser {

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
