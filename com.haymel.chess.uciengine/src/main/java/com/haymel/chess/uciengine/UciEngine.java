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

import com.haymel.chess.engine.Engine;
import com.haymel.chess.engine.game.Search;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.uci.moves.Moves;

public class UciEngine extends com.haymel.chess.uci.Engine {

	private Engine engine;
	
	public static void main(String[] args) throws InterruptedException {
		UciEngine engine = new UciEngine(System.in, System.out);
		engine.start();
		Thread.sleep(Long.MAX_VALUE);
	}
	
	public UciEngine(InputStream in, PrintStream out) {
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
		engine = new Engine();
		for (String move: moves.value()) {
			engine.move(move);
		}
	}
	
	@Override
	public void go(int wtimeInSeconds, int btimeInSeconds, int wincInSeconds, int bincInSeconds) {
		Search search = new Search(engine.game());
		Move move = search.execute(4);
		bestmove(move.from().toString() + move.to().toString());
	}
	
}
