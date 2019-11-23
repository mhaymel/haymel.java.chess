/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.MovesImpl.emptyMoves;
import static com.haymel.util.Require.nonNull;

import com.haymel.chess.uci.cmd.lexer.Lexer;
import com.haymel.chess.uci.cmd.lexer.Token;
import com.haymel.chess.uci.cmd.lexer.TokenType;

public class CmdPositionProcessor {
														// rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR    w    KQkq   -   0   1		
	private static final int MIN_NUMBER_OF_PARAMS_FOR_FEN = 1                                            +1     +1   +1  +1  +1;      
	private final Lexer lexer;
	private final CommandHandler handler;
	
	public CmdPositionProcessor(Lexer lexer, CommandHandler handler) {
		this.lexer = nonNull(lexer, "lexer");
		this.handler = nonNull(handler, "handler");
	}

	public void execute() {
		Token token = lexer.next();
		
		switch (token.type()) {
		case startpos:
			handleStartPos();
			break;

		case fen:
			handleFen();
			break;

		default:
			unknown();
			break;
		}
	}

	private void handleStartPos() {
		handler.positionStart(parseMoves());
	}
	
	private void handleFen() {
		if (lexer.remainingTokens() > MIN_NUMBER_OF_PARAMS_FOR_FEN)
			handler.positionFen(parseFen(), parseMoves());
	}

	private String parseFen() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < MIN_NUMBER_OF_PARAMS_FOR_FEN; i++)
			sb.append(lexer.next().string()).append(' ');
		
		return sb.toString().trim();
	}

	private void unknown() {
		handler.unknown(lexer.toString());
	}

	private Moves parseMoves() {
		if (lexer.remainingTokens() <= 2)
			return emptyMoves;
		
		MovesImpl moves = new MovesImpl();
		lexer.next();
		Token token = lexer.next();
		while(token.type() != TokenType.eof) { 
			moves.add(token.string());
			token = lexer.next();
		}
		
		return moves;
	}

}
