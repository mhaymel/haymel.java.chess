/***************************************************
 * (c) Markus Heumel
 *
 * @date:	07.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.util.Require.nonNull;

import java.io.InputStream;
import java.io.PrintStream;

import com.haymel.chess.uci.moves.Moves;

public class Engine extends com.haymel.chess.uci.Engine {

	public static void main(String[] args) throws InterruptedException {
		Engine engine = new Engine(System.in, System.out);
		engine.start();
		Thread.sleep(Long.MAX_VALUE);
	}
	
	private int plys;
	
	public Engine(InputStream in, PrintStream out) {
		super(nonNull(in, "in"), nonNull(out, "out"));
	}
	
	@Override
	public void uci() {
		idauthor("Markus Heumel");
		idname("haymel");
		uciok();
	}
	
	@Override
	public void isReady() {
		readyok();
	}
	
	@Override
	public void positionStart(Moves moves) {
		plys = moves.value().size();
	}
	
	@Override
	public void go(int wtimeInSeconds, int btimeInSeconds, int wincInSeconds, int bincInSeconds) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(plys) {
		case 0:
			bestmove("b1c3");
			break;
		case 1:
			bestmove("b8c6");
			break;
		case 2:
			bestmove("a1b1");
			break;
		case 3:
			bestmove("a8b8");
			break;
		case 4:
			bestmove("b1a1");
			break;
		case 5:
			bestmove("b8a8");
			break;
		}
	}
	
}
