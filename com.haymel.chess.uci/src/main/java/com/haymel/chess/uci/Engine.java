/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import java.io.InputStream;
import java.io.PrintStream;

import com.haymel.chess.uci.command.Command;
import com.haymel.chess.uci.command.CommandWorker;
import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.result.Infos;
import com.haymel.chess.uci.result.Result;
import com.haymel.chess.uci.result.ResultImpl;

public class Engine implements Command, Result {

	private final CommandWorker commandWorker;
	private final Result result;

	public Engine() {
		this(System.in, System.out);
	}
	
	public Engine(InputStream in, PrintStream out) {
		this.commandWorker = new CommandWorker(in, this);
		this.result = new ResultImpl(out);
	}
	
	public void start() {
		commandWorker.start();
	}
	
	@Override
	public void stop() {
		commandWorker.stop();
	}
	
	@Override
	public void idname(String name) {
		result.idname(name);
	}

	public void idauthor(String author) {
		result.idauthor(author);
	}

	public void uciok() {
		result.uciok();
	}

	public void readyok() {
		result.readyok();
	}

	public void bestmove(String move) {
		result.bestmove(move);
	}

	public void bestmove(String move, String ponderMove) {
		result.bestmove(move, ponderMove);
	}

	public void copyprotectionChecking() {
		result.copyprotectionChecking();
	}

	public void copyprotectionOk() {
		result.copyprotectionOk();
	}

	public void copyprotectionError() {
		result.copyprotectionError();
	}

	public void registrationChecking() {
		result.registrationChecking();
	}

	public void registrationOk() {
		result.registrationOk();
	}

	public void registrationError() {
		result.registrationError();
	}

	public void info(Infos infos) {
		result.info(infos);
	}

	@Override
	public void uci() {
	}

	@Override
	public void debugOn() {
	}

	@Override
	public void debugOff() {
	}

	@Override
	public void isReady() {
	}

	@Override
	public void setoption(String id) {
	}

	@Override
	public void setoption(String id, String value) {
	}

	@Override
	public void registerLater() {
	}

	@Override
	public void register(String name, String code) {
	}

	@Override
	public void ucinewgame() {
	}

	@Override
	public void positionStart(Moves moves) {
	}

	@Override
	public void positionFen(String fen, Moves moves) {
	}

	@Override
	public void go(int wtimeInMilliSeconds, int btimeInMilliSeconds, int wincInMilliSeconds, int bincInMilliSeconds) {
	}

	@Override
	public void go(int wtimeInMilliSeconds, int btimeInMilliSeconds, int wincInMilliSeconds, int bincInMilliSeconds, int movestogo) { 
	}

	@Override
	public void goPonder(int wtimeInMilliSeconds, int btimeInMilliSeconds, int wincInMilliSeconds, int bincInMilliSeconds) {
	}
	
	@Override
	public void goMovetime(int timeInMilliSeconds) {
	}

	@Override
	public void goDepth(int depth) {
	}

	@Override
	public void goNodes(long count) {
	}

	@Override
	public void goInfinite(Moves moves) {
	}

	@Override
	public void goInfinite() {
	}

	@Override
	public void goMate(int moves) {
	}

	@Override
	public void ponderhit() {
	}

	@Override
	public void quit() {
		System.exit(0);
	}

	@Override
	public void unknown(String command) {
		log("unknown: " + command);
	}

	private static void log(String msg) {
		System.err.println("ERR: " + msg);
	}

}
