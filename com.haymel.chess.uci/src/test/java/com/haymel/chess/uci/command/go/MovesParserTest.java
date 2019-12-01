/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uci.Moves;
import com.haymel.chess.uci.MovesImpl;
import com.haymel.chess.uci.lexer.Lexer;
import com.haymel.chess.uci.lexer.TokenType;
import com.haymel.util.exception.HaymelNullPointerException;

public class MovesParserTest {

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullThrowsExceptiom() {
		new MovesParser(null);
	}
	
	@Test
	public void emptyLexerResultsInEmptyMoves() {
		Moves moves = new MovesParser(new Lexer("")).execute();
		assertThat(moves.value().isEmpty(), is(true));
	}

	@Test
	public void parserParsesAsLongAsTokenOfWordCanBeRead() {
		Lexer lexer = new Lexer("e2e4 d5d7 uci a2a4");
		Moves moves = new MovesParser(lexer).execute();
		
		Moves expected = new MovesImpl().add("e2e4").add("d5d7");
		assertThat(moves, is(expected));
		
		assertThat(lexer.next().type(), is(TokenType.uci));
		moves = new MovesParser(lexer).execute();
		expected = new MovesImpl().add("a2a4");
		assertThat(moves, is(expected));
	}
	
}
