/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static com.haymel.util.Require.nonEmpty;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.io.PrintStream;

import com.haymel.util.Require;

public class ResultImpl implements Result {	

	private final PrintStream out;
	
	public ResultImpl(PrintStream out) {
		this.out = nonNull(out, "out");
	}

	@Override
	public void idname(String name) {
		nonEmpty(name, "name");
		out(format("id name %s", name));
	}

	@Override
	public void idauthor(String author) {
		nonEmpty(author, "author");
		out(format("id author %s", author));
	}

	@Override
	public void uciok() {
		out("uciok");
	}

	@Override
	public void readyok() {
		out("readyok");
	}

	@Override
	public void bestmove(String move) {
		nonEmpty(move, "move");
		out(format("bestmove %s", move));
	}

	@Override
	public void bestmove(String move, String ponderMove) {
		nonEmpty(move, "move");
		nonEmpty(ponderMove, "ponderMove");
		out(format("bestmove %s ponder %s", move, ponderMove));
	}

	@Override
	public void copyprotectionChecking() {
		out("copyprotection checking");
	}

	@Override
	public void copyprotectionOk() {
		out("copyprotection ok");
	}
	
	@Override
	public void copyprotectionError() {
		out("copyprotection error");
	}

	@Override
	public void registrationChecking() {
		out("registration checking");
	}

	@Override
	public void registrationOk() {
		out("registration ok");
	}

	@Override
	public void registrationError() {
		out("registration error");
	}

	@Override
	public void info(Infos infos) {
		Require.nonNull(infos, "infos");
		out(new InfosUciString(infos).value());
	}
	
	private void out(String result) {
		out.println(result);
	}
	
	
}
