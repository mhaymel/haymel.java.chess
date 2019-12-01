/***************************************************
 * (c) Markus Heumel
 *
 * @date:	23.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static java.lang.Long.MAX_VALUE;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.haymel.chess.uci.MovesImpl;
import com.haymel.chess.uci.lexer.Lexer;
import com.haymel.util.exception.HaymelNullPointerException;

public class GoParamParserTest {

	@Test(expected=HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new GoParamParser(null);
	}
	
	@Test
	public void emptyString() {
		GoParam param = param("");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void ponder() {
		GoParam param = param("ponder");
		assertThat(param.ponder(), is(true));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void wtime() {
		GoParam param = param("wtime 12345");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(12345));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void btime() {
		GoParam param = param("btime 12345");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(12345));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void winc() {
		GoParam param = param("winc 12345");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(12345));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void binc() {
		GoParam param = param("binc 12345");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(12345));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void movestogo() {
		GoParam param = param("movestogo 40");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(true));
		assertThat(param.movestogo().value(), is(40));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void movetime() {
		GoParam param = param("movetime 30000");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(true));
		assertThat(param.movetime().value(), is(30000));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void depth() {
		GoParam param = param("depth 17");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(true));
		assertThat(param.depth().value(), is(17));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void nodes() {
		GoParam param = param("nodes 1000");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(true));
		assertThat(param.nodes().value(), is(1000L));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void nodesMaxLong() {
		GoParam param = param(format("nodes %s", MAX_VALUE));
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(true));
		assertThat(param.nodes().value(), is(MAX_VALUE));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	@Test
	public void infinite() {
		GoParam param = param("infinite");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(true));
	}
	
	@Test
	public void mate() {
		GoParam param = param("mate 5");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(true));
		assertThat(param.mate().value(), is(5));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void searchmoves() {
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("d2d4");
				
		GoParam param = param("searchmoves e2e4 d2d4");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(true));
		assertThat(param.searchmoves().moves(), is(moves));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goWtime1() {
		GoParam param = param("wtime 100000 winc 2000 btime 300000 binc 4000");
		
		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(100000));
		
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(2000));

		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(300000));
		
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(4000));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goWtime2() {
		GoParam param = param("wtime 100000 btime 300000 winc 2000 binc 4000");
		
		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(100000));
		
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(2000));
		
		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(300000));
		
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(4000));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goWtime3() {
		GoParam param = param("wtime 100000 winc 2000 btime 300000 binc 4000 movestogo 40");
		
		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(100000));
		
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(2000));

		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(300000));
		
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(4000));

		assertThat(param.movestogo().defined(), is(true));
		assertThat(param.movestogo().value(), is(40));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goPonder1() {
		GoParam param = param("ponder wtime 100000 winc 2000 btime 300000 binc 4000");
		
		assertThat(param.ponder(), is(true));

		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(100000));
		
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(2000));

		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(300000));
		
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(4000));
		
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goPonder2() {
		GoParam param = param("ponder wtime 100000 btime 300000 winc 2000 binc 4000");
		
		assertThat(param.ponder(), is(true));

		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(100000));
		
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(2000));
		
		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(300000));
		
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(4000));
		
		assertThat(param.searchmoves().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void goInfiniteSearchmoves() {
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("d2d4");
				
		GoParam param = param("infinite searchmoves e2e4 d2d4");
		assertThat(param.ponder(), is(false));
		assertThat(param.searchmoves().defined(), is(true));
		assertThat(param.searchmoves().moves(), is(moves));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(true));
	}

	private GoParam param(String input) {
		Lexer lexer = new Lexer(input);
		GoParam param = new GoParamParser(lexer).execute();
		return param;
	}

}
