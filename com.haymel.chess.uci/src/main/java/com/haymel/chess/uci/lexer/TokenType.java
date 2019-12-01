/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.lexer;

import static com.haymel.util.exception.HaymelException.throwHE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.haymel.util.Require;

public enum TokenType {

	uci("uci"),
	debug("debug"),
	on("on"),
	off("off"),
	isready("isready"),
	setoption("setoption"),
	name("name"),
	nullmove("Nullmove"),
	value("value"),
	bool("true|false"),
	False("false"),
	selectivity("Selectivity"),
	style("Style"),
	clear("Clear"),
	hash("Hash"),
	nalimovPath("NalimovPath"),
	register("register"),
	later("later"),
	code("code"),
	ucinewgame("ucinewgame"),
	position("position"),
	startpos("startpos"), 
	fen("fen"),
	moves("moves"),
	go("go"),
	searchmoves("searchmoves"),
	ponder("ponder"),
	wtime("wtime"),
	btime("btime"),
	winc("winc"),
	binc("binc"),
	movestogo("movestogo"),
	depth("depth"),
	nodes("nodes"),
	mate("mate"),
	movetime("movetime"),
	infinite("infinite"),
	stop("stop"),
	ponderhit("ponderhit"),
	quit("quit"),
	
	number("\\d+"),
	word(".*"),
	eof("$^");
	
	private final String regex;
	
	TokenType(String regex) {
		this.regex = Require.nonNull(regex, "regex");
	}
	
	public String regex() {
		return regex;
	}
	
	public static TokenType tokenTypeOf(String word) {
		for(TokenType type: values()) {
			if (type.matches(word))
				return type;
		}
		
		return throwHE("word '%s' cannot be matched. This is probably an program bug!", word);
	}

	public boolean matches(String word) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(word);
		return matcher.matches();		
	}
	
};
	
	
	
	
