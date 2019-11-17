/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.CommandHandler.debug;
import static com.haymel.chess.uci.CommandHandler.go;
import static com.haymel.chess.uci.CommandHandler.isready;
import static com.haymel.chess.uci.CommandHandler.ponderhit;
import static com.haymel.chess.uci.CommandHandler.position;
import static com.haymel.chess.uci.CommandHandler.quit;
import static com.haymel.chess.uci.CommandHandler.stop;
import static com.haymel.chess.uci.CommandHandler.uci;
import static com.haymel.chess.uci.CommandHandler.ucinewgame;
import static com.haymel.util.Require.greaterEqualZero;
import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.lessThan;
import static com.haymel.util.Require.nonNull;

public class Parser {

	private static final String[] empty = new String[] {};
	private final String[] line;
	
	public Parser(String line) {
		nonNull(line, "line");
		this.line = extracted(line);
	}

	public boolean empty() {
		return count() == 0;
	}
	
	public int count() {
		return line.length;
	}
	
	public String first() {
		greaterThanZero(count(), "count()");
		return value(0);
	}

	public String value(int index) {
		greaterEqualZero(index, "index");
		lessThan(index, count(), "index");
		return line[index];
	}

	public String[] values() {
		return line;
	}
	
	public boolean isCmdUci() {
		return isCmd(uci);
	}
	
	public boolean isCmdDebug() {
		return isCmd(debug);
	}
	
	public boolean isCmdIsready() {
		return isCmd(isready);
	}
	
	public boolean isCmdUcinewgame() {
		return isCmd(ucinewgame);
	}
	
	public boolean isCmdPosition() {
		return isCmd(position);
	}

	public boolean isCmdGo() {
		return isCmd(go);
	}
	
	public boolean isCmdStop() {
		return isCmd(stop);
	}
	
	public boolean isCmdPonderhit() {
		return isCmd(ponderhit);
	}

	public boolean isCmdQuit() {
		return isCmd(quit);
	}

	private static String[] extracted(String line) {
		String trimmed = line.trim();
		
		return  trimmed.isEmpty() ? empty : trimmed.split("\\s+");
	}
	
	private boolean isCmd(String cmd) {
		return cmd.equals(command());
	}

	private String command() {
		return first().toLowerCase();
	}
	
}
