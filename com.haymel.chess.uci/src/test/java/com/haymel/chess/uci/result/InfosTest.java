/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.uci.moves.MovesImpl;

public class InfosTest {

	private static final String INT_MAX_STRING = String.valueOf(Integer.MAX_VALUE);
	private static final String LONG_MAX_STRING = String.valueOf(Long.MAX_VALUE);
	private Infos infos;
	
	@Before
	public void setup() {
		infos = new Infos();
	}
	
	@Test
	public void depth() {
		infos.depth(17);
		assertEqual(first(), "depth", "17");
	}

	@Test
	public void seldepth() {
		infos.seldepth(13);
		assertEqual(first(), "seldepth", "13");
	}

	@Test
	public void time() {
		infos.time(Integer.MAX_VALUE);
		assertEqual(first(), "time", INT_MAX_STRING);
	}
	
	@Test
	public void nodes() {
		infos.nodes(Long.MAX_VALUE);
		assertEqual(first(), "nodes", LONG_MAX_STRING);
	}
	
	@Test
	public void pv() {
		infos.pv(new MovesImpl().add("e2e4").add("d7d5"));
		assertEqual(first(), "pv", "e2e4 d7d5");
	}

	@Test
	public void multipv() {
		infos.multipv(13);
		assertEqual(first(), "multipv", "13");
	}

	@Test
	public void scorecp() {
		infos.scorecp(13);
		assertEqual(first(), "score cp", "13");
	}

	@Test
	public void scoremate() {
		infos.scoremate(13);
		assertEqual(first(), "score mate", "13");
	}

	@Test
	public void scorecplowerbound() {
		infos.scorecplowerbound(31);
		assertEqual(first(), "score cp", "31");
		assertEqual(second(), "lowerbound", "");
	}

	@Test
	public void scorecpupperbound() {
		infos.scorecpupperbound(31);
		assertEqual(first(), "score cp", "31");
		assertEqual(second(), "upperbound", "");
	}
	
	@Test
	public void currmove() {
		infos.currmove("e2e4");
		assertEqual(first(), "currmove", "e2e4");
	}

	@Test
	public void currmovenumber() {
		infos.currmovenumber(13);
		assertEqual(first(), "currmovenumber", "13");
	}

	@Test
	public void hashfull() {
		infos.hashfull(13);
		assertEqual(first(), "hashfull", "13");
	}
	
	@Test
	public void nps() {
		infos.nps(Long.MAX_VALUE);
		assertEqual(first(), "nps", LONG_MAX_STRING);
	}

	@Test
	public void tbhits() {
		infos.tbhits(Long.MAX_VALUE);
		assertEqual(first(), "tbhits", LONG_MAX_STRING);
	}
	
	@Test
	public void sbhits() {
		infos.sbhits(Long.MAX_VALUE);
		assertEqual(first(), "sbhits", LONG_MAX_STRING);
	}
	
	@Test
	public void cpuload() {
		infos.cpuload(13);
		assertEqual(first(), "cpuload", "13");
	}
	
	@Test
	public void string() {
		infos.string("hello, world");
		assertEqual(first(), "string", "hello, world");
	}

	@Test
	public void refutation() {
		infos.refutation(new MovesImpl().add("e2e4").add("d7d5"));
		assertEqual(first(), "refutation", "e2e4 d7d5");
	}

	@Test
	public void currline() {
		infos.currline(new MovesImpl().add("e2e4").add("d7d5"));
		assertEqual(first(), "currline", "e2e4 d7d5");
	}

	private void assertEqual(Info info, String key, String value) {
		assertThat(info.key(), is(key));
		assertThat(info.value(), is(value));
	}

	private Info first() {
		assertThat(infos.value().size(), is(greaterThan(0)));
		
		return infos.value().iterator().next();
	}
	
	private Info second() {
		assertThat(infos.value().size(), is(greaterThan(1)));
		
		Iterator<Info> i = infos.value().iterator();
		i.next();
		return i.next();
	}

}
