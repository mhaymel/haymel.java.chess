/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.uci.MovesImpl;

public class GoParamTest {

	private GoParam param;
	
	@Before
	public void setup() {
		param = new GoParam();
	}
	
	@Test
	public void allValuesAreUndefinedForVirginObject() {
		assertThat(param.ponder(), is(false));
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
	public void setPonder() {
		param.setPonder();
		assertThat(param.ponder(), is(true));
		
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
	public void searchmoves() {
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("d7d5");
		param.searchmoves(moves);
		assertThat(param.searchmoves().defined(), is(true));
		assertThat(param.searchmoves().moves(), is(moves));
		
		assertThat(param.ponder(), is(false));
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
		param.wtime(10);
		assertThat(param.wtime().defined(), is(true));
		assertThat(param.wtime().value(), is(10));
		
		assertThat(param.ponder(), is(false));
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
		param.btime(10);
		assertThat(param.btime().defined(), is(true));
		assertThat(param.btime().value(), is(10));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
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
		param.winc(17);
		assertThat(param.winc().defined(), is(true));
		assertThat(param.winc().value(), is(17));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
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
		param.binc(17);
		assertThat(param.binc().defined(), is(true));
		assertThat(param.binc().value(), is(17));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void movestogo() {
		param.movestogo(13);
		assertThat(param.movestogo().defined(), is(true));
		assertThat(param.movestogo().value(), is(13));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void depth() {
		param.depth(13);
		assertThat(param.depth().defined(), is(true));
		assertThat(param.depth().value(), is(13));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void nodes() {
		param.nodes(13);
		assertThat(param.nodes().defined(), is(true));
		assertThat(param.nodes().value(), is(13L));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}

	@Test
	public void mate() {
		param.mate(13);
		assertThat(param.mate().defined(), is(true));
		assertThat(param.mate().value(), is(13));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void movetime() {
		param.movetime(13);
		assertThat(param.movetime().defined(), is(true));
		assertThat(param.movetime().value(), is(13));
		
		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.infinite(), is(false));
	}
	
	@Test
	public void infinite() {
		param.setInfinite();
		assertThat(param.infinite(), is(true));

		assertThat(param.ponder(), is(false));
		assertThat(param.wtime().defined(), is(false));
		assertThat(param.btime().defined(), is(false));
		assertThat(param.winc().defined(), is(false));
		assertThat(param.binc().defined(), is(false));
		assertThat(param.movestogo().defined(), is(false));
		assertThat(param.depth().defined(), is(false));
		assertThat(param.nodes().defined(), is(false));
		assertThat(param.mate().defined(), is(false));
		assertThat(param.movetime().defined(), is(false));
	}
	
}
