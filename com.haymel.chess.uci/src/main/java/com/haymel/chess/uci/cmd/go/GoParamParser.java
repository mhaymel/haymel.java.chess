/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import static com.haymel.util.Require.nonNull;
import static java.lang.Integer.MAX_VALUE;

import com.haymel.chess.uci.cmd.lexer.Lexer;
import com.haymel.chess.uci.cmd.lexer.NumberToken;
import com.haymel.chess.uci.cmd.lexer.Token;
import com.haymel.chess.uci.cmd.lexer.TokenType;

class GoParamParser {
	
	private final Lexer lexer;
	private GoParam param;
	
	GoParamParser(Lexer lexer) {
		this.lexer = nonNull(lexer, "lexer");
	}
	
	GoParam execute() {
		param = new GoParam();

		while(lexer.hasNext()) {
			switch(lexer.next().type()) {
			case wtime:
				handleWtime(lexer.next());
				break;
				
			case btime:
				handleBtime(lexer.next());
				break;
				
			case winc:
				handleWinc(lexer.next());
				break;
				
			case binc:
				handleBinc(lexer.next());
				break;
				
			case movestogo:
				handleMovestogo(lexer.next());
				break;

			case ponder:
				param.setPonder();
				break;
				
			case movetime:
				handleMovetime(lexer.next());
				break;
				
			case depth:
				handleDepth(lexer.next());
				break;
				
			case nodes:
				handleNodes(lexer.next());
				break;
				
			case infinite:
				param.setInfinite();
				break;
				
			case searchmoves:
				handleSearchmoves();
				break;
				
			case mate:
				handleMate(lexer.next());
				break;
				
			case eof:
			default:
				break;
			}
		}
		
		return param;
	}

	private void handleWtime(Token token) {
		if (!isNumber(token))
			return;
		
		param.wtime(asInt(token));
	}

	private void handleBtime(Token token) {
		if (!isNumber(token))
			return;
		
		param.btime(asInt(token));
	}

	private void handleWinc(Token token) {
		if (!isNumber(token))
			return;
		
		param.winc(asInt(token));
	}

	private void handleBinc(Token token) {
		if (!isNumber(token))
			return;
		
		param.binc(asInt(token));
	}

	private void handleMovestogo(Token token) {
		if (!isNumber(token))
			return;
		
		param.movestogo(asInt(token));
	}
	
	private void handleMovetime(Token token) {
		if (!isNumber(token))
			return;
		
		param.movetime(asInt(token));
	}

	private void handleDepth(Token token) {
		if (!isNumber(token))
			return;
		
		param.depth(asInt(token));
	}

	private void handleNodes(Token next) {
		if (!isNumber(next))
			return;
		
		param.nodes(asLong(next));
	}

	private void handleSearchmoves() {
		param.searchmoves(new MovesParser(lexer).execute());
	}
	
	private void handleMate(Token token) {
		if (!isNumber(token))
			return;
		
		param.mate(asInt(token));
	}

	private boolean isNumber(Token token) {
		return token.type() == TokenType.number;
	}
	
	private int asInt(Token token) {		 
		return (int)Math.min(asLong(token), MAX_VALUE);
	}

	private long asLong(Token token) {		 
		return ((NumberToken)token).value();
	}
	
}
